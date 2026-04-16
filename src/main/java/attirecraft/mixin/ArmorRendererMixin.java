package attirecraft.mixin;

import attirecraft.TemplateAwareArmorRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ArmorRenderer.class)
public interface ArmorRendererMixin extends TemplateAwareArmorRenderer {
    @Shadow
    void render(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, ItemStack stack, HumanoidRenderState humanoidRenderState, EquipmentSlot slot, int light, HumanoidModel<HumanoidRenderState> contextModel);

    @Override
    default void attirecraft$render(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, ItemStackTemplate template, HumanoidRenderState humanoidRenderState, EquipmentSlot slot, int light, HumanoidModel<HumanoidRenderState> contextModel) {
        var stack = template.create();
        this.render(poseStack, submitNodeCollector, stack, humanoidRenderState, slot, light, contextModel);
    }
}
