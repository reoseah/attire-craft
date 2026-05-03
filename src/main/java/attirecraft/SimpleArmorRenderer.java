package attirecraft;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public record SimpleArmorRenderer(HumanoidModel<HumanoidRenderState> model,
                                  Identifier texture) implements ArmorRenderer {
    @Override
    public void render(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, ItemStack stack, HumanoidRenderState humanoidRenderState, EquipmentSlot slot, int light, HumanoidModel<HumanoidRenderState> contextModel) {
        var renderType = contextModel.renderType(this.texture);
        submitNodeCollector.submitModel(this.model, humanoidRenderState, poseStack, renderType, light, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF, null, 0, null);
    }
}
