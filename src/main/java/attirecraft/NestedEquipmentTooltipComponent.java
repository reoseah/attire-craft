package attirecraft;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;

import java.util.List;

public record NestedEquipmentTooltipComponent(
        List<ItemStackTemplate> equipment) implements TooltipComponent {
    @Environment(EnvType.CLIENT)
    public record Clientside(List<ItemStack> stacks, List<Component> names, int width,
                             int height) implements ClientTooltipComponent {
        public Clientside(List<ItemStackTemplate> templates) {
            var stacks = templates.stream().map(ItemStackTemplate::create).toList();
            var names = stacks.stream()
                    .<Component>map(stack -> MutableComponent.create(stack.getHoverName().getContents()).withStyle(ChatFormatting.GRAY))
                    .toList();
            var width = 20 + 1 + stacks.stream()
                    .map(ItemStack::getDisplayName)
                    .mapToInt(name -> Minecraft.getInstance().font.width(name))
                    .max().orElse(0) + 1;
            var height = 18 * stacks.size();

            this(stacks, names, width, height);
        }

        @Override
        public int getHeight(Font font) {
            return this.height;
        }

        @Override
        public int getWidth(Font font) {
            return this.width;
        }

        @Override
        public void extractImage(Font font, int x, int y, int w, int h, GuiGraphicsExtractor graphics) {
            for (int i = 0; i < this.stacks.size(); i++) {
                var stack = this.stacks.get(i);
                graphics.item(stack, x + 1, y + 1 + i * 20);
            }
        }

        @Override
        public void extractText(GuiGraphicsExtractor graphics, Font font, int x, int y) {
            for (int i = 0; i < this.stacks.size(); i++) {
                var name = this.names.get(i);
                graphics.text(Minecraft.getInstance().font, name, x + 20 + 1, y + 1 + 20 * i + 4, 0xFF_FFFFFF);
            }
        }
    }
}
