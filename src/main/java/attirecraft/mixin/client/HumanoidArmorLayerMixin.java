package attirecraft.mixin.client;


import attirecraft.AttireCraft;
import attirecraft.TemplateAwareArmorRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.fabric.impl.client.rendering.ArmorRendererRegistryImpl;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = HumanoidArmorLayer.class, priority = 700)
public abstract class HumanoidArmorLayerMixin<S extends HumanoidRenderState, M extends HumanoidModel<S>, A extends HumanoidModel<S>> extends RenderLayer<S, M> {
    public HumanoidArmorLayerMixin(RenderLayerParent<S, M> renderLayerParent) {
        super(renderLayerParent);
    }

    @Inject(method = "renderArmorPiece", at = @At("HEAD"))
    private void renderArmor(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, ItemStack itemStack, EquipmentSlot slot, int lightCoords, S state, CallbackInfo ci) {
        var nested = itemStack.get(AttireCraft.NESTED_EQUIPMENT);
        if (nested != null) {
            var contextModel = (HumanoidModel<HumanoidRenderState>) getParentModel();
            for (var nestedItem : nested) {
                var renderer = ArmorRendererRegistryImpl.get(nestedItem.item().value());
                if (renderer instanceof TemplateAwareArmorRenderer templateRenderer) {
                    templateRenderer.renderTemplate(poseStack, submitNodeCollector, nestedItem, state, slot, lightCoords, contextModel);
                } else if (renderer != null) {
                    var nestedStack = nestedItem.create();
                    renderer.render(poseStack, submitNodeCollector, nestedStack, state, slot, lightCoords, contextModel);
                }
            }
        }
    }
}
