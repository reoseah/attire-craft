package attirecraft;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStackTemplate;

/// Modified [net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer] that accepts [ItemStackTemplate]
/// for optimization purposes (I don't know how impactful it'll be).
public interface TemplateAwareArmorRenderer {
    /// [ItemStackTemplate] version of [net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer#render].
    void renderTemplate(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, ItemStackTemplate template, HumanoidRenderState humanoidRenderState, EquipmentSlot slot, int light, HumanoidModel<HumanoidRenderState> contextModel);
}
