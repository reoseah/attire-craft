package attirecraft;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
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
import net.minecraft.world.item.ItemStackTemplate;

@Environment(EnvType.CLIENT)
public record AtlasArmorRenderer(HumanoidModel<HumanoidRenderState> model,
                                 Identifier texture,
                                 TextureAtlas atlas) implements ArmorRenderer, TemplateAwareArmorRenderer {
    public static AtlasArmorRenderer withArmorTrimAtlas(EntityRendererProvider.Context ctx, ModelLayerLocation modelLocation, Identifier texture) {
        return new AtlasArmorRenderer(new HumanoidModel<>(ctx.bakeLayer(modelLocation)), texture, ctx.getAtlas(AtlasIds.ARMOR_TRIMS));
    }

    @Override
    public void render(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, ItemStack stack, HumanoidRenderState state, EquipmentSlot slot, int lightCoords, HumanoidModel<HumanoidRenderState> contextModel) {
        var renderType = Sheets.armorTrimsSheet(false);
        var sprite = this.atlas.getSprite(this.texture);
        submitNodeCollector
                .submitModel(model, state, poseStack, renderType, lightCoords, OverlayTexture.NO_OVERLAY, -1, sprite, 0, null);
    }

    @Override
    public void renderTemplate(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, ItemStackTemplate template, HumanoidRenderState state, EquipmentSlot slot, int lightCoords, HumanoidModel<HumanoidRenderState> contextModel) {
        var renderType = Sheets.armorTrimsSheet(false);
        var sprite = this.atlas.getSprite(this.texture);
        submitNodeCollector
                .submitModel(model, state, poseStack, renderType, lightCoords, OverlayTexture.NO_OVERLAY, -1, sprite, 0, null);
    }
}
