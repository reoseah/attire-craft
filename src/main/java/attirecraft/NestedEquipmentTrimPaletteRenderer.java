package attirecraft;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.impl.client.rendering.ArmorRendererRegistryImpl;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.data.AtlasIds;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public record NestedEquipmentTrimPaletteRenderer(HumanoidModel<HumanoidRenderState> model,
                                                 Identifier texture, TextureAtlas atlas) implements ArmorRenderer {
    public NestedEquipmentTrimPaletteRenderer(EntityRendererProvider.Context ctx, ModelLayerLocation modelLocation, Identifier texture) {
        this(new HumanoidModel<>(ctx.bakeLayer(modelLocation)), texture, ctx.getAtlas(AtlasIds.ARMOR_TRIMS));
    }

    @Override
    public void render(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, ItemStack stack, HumanoidRenderState state, EquipmentSlot slot, int lightCoords, HumanoidModel<HumanoidRenderState> contextModel) {
        var nestedEquipment = stack.get(AttireCraft.NESTED_EQUIPMENT);
        if (nestedEquipment != null) {
            for (var nestedItem : nestedEquipment) {
                var renderer = ArmorRendererRegistryImpl.get(nestedItem.item().value());
                if (renderer instanceof TemplateAwareArmorRenderer templateRenderer) {
                    templateRenderer.renderTemplate(poseStack, submitNodeCollector, nestedItem, state, slot, lightCoords, contextModel);
                } else if (renderer != null) {
                    renderer.render(poseStack, submitNodeCollector, nestedItem.create(), state, slot, lightCoords, contextModel);
                }
            }
        }

        var renderType = Sheets.armorTrimsSheet(false);
        var sprite = this.atlas.getSprite(this.texture);
        submitNodeCollector
                .submitModel(model, state, poseStack, renderType, lightCoords, OverlayTexture.NO_OVERLAY, -1, sprite, 0, null);
    }
}
