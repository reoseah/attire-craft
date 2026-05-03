package attirecraft.mixin;

import attirecraft.AttireCraft;
import attirecraft.NestedEquipmentTooltipComponent;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Optional;

@Mixin(Item.class)
public class ItemMixin {
    @Inject(at = @At("HEAD"), method = "overrideStackedOnOther", cancellable = true)
    public void overrideStackedOnOther(ItemStack self, Slot slot, ClickAction clickAction, Player player, CallbackInfoReturnable<Boolean> cir) {
        var other = slot.getItem();
        var nestableOther = other.get(AttireCraft.NESTABLE);
        if (clickAction == ClickAction.PRIMARY && nestableOther != null) {
            if (!self.is(nestableOther.containers())) {
                BundleItem.playInsertFailSound(player);
                cir.setReturnValue(false);
                return;
            }
            var nestedItems = self.get(AttireCraft.NESTED_EQUIPMENT);
            if (nestedItems != null && nestedItems.stream().anyMatch(nested -> nested.is(nestableOther.exclusiveSet()))) {
                BundleItem.playInsertFailSound(player);
                cir.setReturnValue(false);
                return;
            }
            var nestedNestedItems = other.get(AttireCraft.NESTED_EQUIPMENT);
            if (nestedNestedItems != null && nestedNestedItems.stream()
                    .map(otherNested -> otherNested.get(AttireCraft.NESTABLE))
                    .anyMatch(otherNestable -> otherNestable == null || !self.is(otherNestable.containers()))
            ) {
                BundleItem.playInsertFailSound(player);
                cir.setReturnValue(false);
                return;
            }

            var otherSafe = slot.safeTake(1, 1, player);
            if (otherSafe.isEmpty()) {
                BundleItem.playInsertFailSound(player);
                cir.setReturnValue(false);
                return;
            }
            otherSafe.remove(AttireCraft.NESTED_EQUIPMENT);

            var newEntry = ItemStackTemplate.fromNonEmptyStack(otherSafe);
            var newNestedItems = ImmutableList.copyOf(
                    Iterables.concat(
                            nestedItems != null ? nestedItems : List.of(),
                            List.of(newEntry),
                            nestedNestedItems != null ? nestedNestedItems : List.of()
                    )
            );
            self.set(AttireCraft.NESTED_EQUIPMENT, newNestedItems);

            broadcastChangesOnContainerMenu(player);
            BundleItem.playInsertSound(player);
            cir.setReturnValue(true);
            return;
        } else if (clickAction == ClickAction.SECONDARY && other.isEmpty()) {
            var nestedItems = self.get(AttireCraft.NESTED_EQUIPMENT);
            if (nestedItems == null || nestedItems.isEmpty()) {
                return;
            }
            var removedEntry = nestedItems.getLast();
            var inserted = slot.safeInsert(removedEntry.create()).isEmpty();
            if (!inserted) {
                cir.setReturnValue(false);
                return;
            }

            var newNestedItems = List.copyOf(nestedItems.subList(0, nestedItems.size() - 1));
            if (newNestedItems.isEmpty()) {
                self.remove(AttireCraft.NESTED_EQUIPMENT);
            } else {
                self.set(AttireCraft.NESTED_EQUIPMENT, newNestedItems);
            }

            broadcastChangesOnContainerMenu(player);
            BundleItem.playRemoveOneSound(player);
            cir.setReturnValue(true);
            return;
        }
    }

    @Inject(at = @At("HEAD"), method = "overrideOtherStackedOnMe", cancellable = true)
    public void overrideOtherStackedOnMe(ItemStack self, ItemStack other, Slot slot, ClickAction clickAction, Player player, SlotAccess carriedItem, CallbackInfoReturnable<Boolean> cir) {
        var nestableOther = other.get(AttireCraft.NESTABLE);
        if (clickAction == ClickAction.PRIMARY && nestableOther != null && slot.allowModification(player)) {
            if (!self.is(nestableOther.containers())) {
                BundleItem.playInsertFailSound(player);
                cir.setReturnValue(false);
                return;
            }
            var nestedItems = self.get(AttireCraft.NESTED_EQUIPMENT);
            if (nestedItems != null && nestedItems.stream().anyMatch(nested -> nested.is(nestableOther.exclusiveSet()))) {
                BundleItem.playInsertFailSound(player);
                cir.setReturnValue(false);
                return;
            }
            var nestedNestedItems = other.get(AttireCraft.NESTED_EQUIPMENT);
            if (nestedNestedItems != null && nestedNestedItems.stream()
                    .map(otherNested -> otherNested.get(AttireCraft.NESTABLE))
                    .anyMatch(otherNestable -> otherNestable == null || !self.is(otherNestable.containers()))
            ) {
                BundleItem.playInsertFailSound(player);
                cir.setReturnValue(false);
                return;
            }

            other.remove(AttireCraft.NESTED_EQUIPMENT);

            var newEntry = ItemStackTemplate.fromNonEmptyStack(other);
            var newNestedItems = ImmutableList.copyOf(
                    Iterables.concat(
                            nestedItems != null ? nestedItems : List.of(),
                            List.of(newEntry),
                            nestedNestedItems != null ? nestedNestedItems : List.of()
                    )
            );
            self.set(AttireCraft.NESTED_EQUIPMENT, newNestedItems);

            other.split(other.count());

            broadcastChangesOnContainerMenu(player);
            BundleItem.playInsertSound(player);
            cir.setReturnValue(true);
            return;
        }
    }

    @Inject(at = @At("HEAD"), method = "getTooltipImage", cancellable = true)
    public void getTooltipImage(ItemStack itemStack, CallbackInfoReturnable<Optional<TooltipComponent>> cir) {
        var nestedItems = itemStack.get(AttireCraft.NESTED_EQUIPMENT);
        if (nestedItems != null && !nestedItems.isEmpty()) {
            cir.setReturnValue(Optional.of(new NestedEquipmentTooltipComponent(nestedItems)));
            return;
        }
    }

    @Unique
    private static void broadcastChangesOnContainerMenu(final Player player) {
        var containerMenu = player.containerMenu;
        if (containerMenu != null) {
            containerMenu.slotsChanged(player.getInventory());
        }
    }
}
