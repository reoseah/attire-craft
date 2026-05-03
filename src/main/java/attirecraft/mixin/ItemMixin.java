package attirecraft.mixin;

import attirecraft.AttireCraft;
import attirecraft.NestedEquipmentTooltipComponent;
import net.minecraft.world.entity.SlotAccess;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin(Item.class)
public class ItemMixin {
    @Inject(at = @At("HEAD"), method = "overrideStackedOnOther", cancellable = true)
    public void overrideStackedOnOther(ItemStack self, Slot slot, ClickAction clickAction, Player player, CallbackInfoReturnable<Boolean> cir) {
        var nestingProperties = self.get(AttireCraft.NESTING_PROPERTIES);
        if (nestingProperties != null) {
            var result = nestingProperties.handleOverrideStackedOnOther(self, slot, clickAction, player);
            if (result.isPresent()) {
                cir.setReturnValue(result.get());
            }
        }
    }


    @Inject(at = @At("HEAD"), method = "overrideOtherStackedOnMe", cancellable = true)
    public void overrideOtherStackedOnMe(ItemStack self, ItemStack other, Slot slot, ClickAction clickAction, Player player, SlotAccess carriedItem, CallbackInfoReturnable<Boolean> cir) {

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
