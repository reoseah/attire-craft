package attirecraft;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.impl.client.rendering.ArmorRendererRegistryImpl;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public record NestedEquipmentSimpleRenderer(HumanoidModel<HumanoidRenderState> model,
                                            Identifier texture) implements ArmorRenderer {
    @Override
    public void render(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, ItemStack stack, HumanoidRenderState humanoidRenderState, EquipmentSlot slot, int light, HumanoidModel<HumanoidRenderState> contextModel) {
        var nested = stack.get(AttireCraft.NESTED_EQUIPMENT);
        if (nested != null) {
            for (var nestedItem : nested) {
                var nestedRenderer = ArmorRendererRegistryImpl.get(nestedItem.item().value());
                if (nestedRenderer instanceof TemplateAwareArmorRenderer templateRenderer) {
                    templateRenderer.renderTemplate(poseStack, submitNodeCollector, nestedItem, humanoidRenderState, slot, light, contextModel);
                } else if (nestedRenderer != null) {
                    var nestedStack = nestedItem.create();
                    nestedRenderer.render(poseStack, submitNodeCollector, nestedStack, humanoidRenderState, slot, light, contextModel);
                }
            }
        }

        var renderType = contextModel.renderType(this.texture);
        submitNodeCollector.submitModel(this.model, humanoidRenderState, poseStack, renderType, light, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF, null, 0, null);
    }
}
