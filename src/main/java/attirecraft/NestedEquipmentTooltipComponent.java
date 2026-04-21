package attirecraft;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStackTemplate;

import java.util.List;

public record NestedEquipmentTooltipComponent(
        List<ItemStackTemplate> equipment) implements TooltipComponent, ClientTooltipComponent {
    @Override
    public int getHeight(Font font) {
        return this.equipment.size() * 18;
    }

    @Override
    public int getWidth(Font font) {
        return 20 + 1 + this.equipment.stream()
                .map(stack -> stack.create().getDisplayName())
                .mapToInt(name -> Minecraft.getInstance().font.width(name))
                .max().orElse(0) + 1;
    }

    @Override
    public void extractImage(Font font, int x, int y, int w, int h, GuiGraphicsExtractor graphics) {
        for (int i = 0; i < this.equipment.size(); i++) {
            var stack = this.equipment.get(i).create(); // TODO: cache maybe?
            graphics.item(stack, x + 1, y + 1 + i * 20);
        }
    }

    @Override
    public void extractText(GuiGraphicsExtractor graphics, Font font, int x, int y) {
        for (int i = 0; i < this.equipment.size(); i++) {
            var stack = this.equipment.get(i).create(); // TODO: cache maybe?
            var name = MutableComponent.create(stack.getHoverName().getContents()).withStyle(ChatFormatting.GRAY);
            graphics.text(Minecraft.getInstance().font, name, x + 20 + 1, y + 1 + 20 * i + 4, 0xFF_FFFFFF);
        }
    }
}
