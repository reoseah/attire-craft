package attirecraft;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStackTemplate;

public interface TemplateAwareArmorRenderer {
    /// [ItemStackTemplate] version of [net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer#render].
    default void attirecraft$render(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, ItemStackTemplate template, HumanoidRenderState humanoidRenderState, EquipmentSlot slot, int light, HumanoidModel<HumanoidRenderState> contextModel) {
        throw new AssertionError("Implemented with a mixin");
    }
}
