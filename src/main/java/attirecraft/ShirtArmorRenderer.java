package attirecraft;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;

public class ShirtArmorRenderer implements ArmorRenderer {
    public static final Identifier LIGHT_BLUE = AttireCraft.modId("textures/entity/equipment/shirt/light_blue.png");

    private final Identifier texture;
    private final HumanoidModel<HumanoidRenderState> model;

    public ShirtArmorRenderer(EntityRendererProvider.Context context, Identifier texture) {
        this.model = new HumanoidModel<>(context.bakeLayer(AttireCraft.LIGHT_BLUE_SHIRT));
        this.texture = texture;
    }

    @Override
    public void render(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, ItemStack stack, HumanoidRenderState humanoidRenderState, EquipmentSlot slot, int light, HumanoidModel<HumanoidRenderState> contextModel) {
        var renderType = contextModel.renderType(this.texture);
        submitNodeCollector.submitModel(this.model, humanoidRenderState, poseStack, renderType, light, OverlayTexture.NO_OVERLAY, 0xFFFFFFFF, null, 0, null);
    }

    public static LayerDefinition createLayerDefinition() {
        var mesh = new MeshDefinition();
        var root = mesh.getRoot();

        var head = root.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0, 0, 0));
        head.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        var deformation = new CubeDeformation(0.5F);
        root.addOrReplaceChild("body",
                CubeListBuilder.create()
                        .texOffs(0, 0).addBox(-4, 0, -2, 8, 12, 4, deformation)
                        .texOffs(0, 16).addBox(-3, -0.25F, -2, 6, 12, 4, deformation.extend(0.5F)),
                PartPose.offset(0, 0, 0));
        root.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(24, 0).addBox(-3, -2, -2, 4, 12, 4, deformation), PartPose.offset(-5, 2, 0));
        root.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(24, 0).mirror().addBox(-1, -2, -2, 4, 12, 4, deformation), PartPose.offset(5, 2, 0));
        root.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);

        return LayerDefinition.create(mesh, 48, 32);
    }

}
