package attirecraft.mixin;

import attirecraft.AttireCraft;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
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

@Mixin(Item.class)
public class ItemMixin {
    @Inject(at = @At("HEAD"), method = "overrideStackedOnOther", cancellable = true)
    public void overrideStackedOnOther(ItemStack self, Slot slot, ClickAction clickAction, Player player, CallbackInfoReturnable<Boolean> cir) {
        var nestable = self.get(AttireCraft.NESTABLE_EQUIPMENT);
        if (nestable != null) {
            var other = slot.getItem();
            if (other.is(nestable.allowed())) {
                if (clickAction == ClickAction.PRIMARY) {
                    // TODO: check that item doesn't conflict with other items already nested, e.g. not placing multiple copies of same thing
                    var nested = ImmutableList.copyOf(Iterables.concat(
                            List.of(ItemStackTemplate.fromNonEmptyStack(other.split(1))),
                            self.getOrDefault(AttireCraft.NESTED_EQUIPMENT, List.of())
                    ));

                    self.set(AttireCraft.NESTED_EQUIPMENT, nested);

                    BundleItem.playInsertSound(player);
                    broadcastChangesOnContainerMenu(player);
                    cir.setReturnValue(true);
                    return;
                }
            } else {
                BundleItem.playInsertFailSound(player);
                cir.setReturnValue(false);
                return;
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "overrideOtherStackedOnMe", cancellable = true)
    public void overrideOtherStackedOnMe(ItemStack self, ItemStack other, Slot slot, ClickAction clickAction, Player player, SlotAccess carriedItem, CallbackInfoReturnable<Boolean> cir) {

    }

    @Unique
    private static void broadcastChangesOnContainerMenu(final Player player) {
        var containerMenu = player.containerMenu;
        if (containerMenu != null) {
            containerMenu.slotsChanged(player.getInventory());
        }
    }
}
