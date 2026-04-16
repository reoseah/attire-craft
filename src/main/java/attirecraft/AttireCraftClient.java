package attirecraft;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.resources.Identifier;

@Environment(EnvType.CLIENT)
public class AttireCraftClient {
    public static final ModelLayerLocation SHIRT_MODEL = new ModelLayerLocation(AttireCraft.modId("shirt"), "main");
    public static final ModelLayerLocation BLAZER_MODEL = new ModelLayerLocation(AttireCraft.modId("blazer"), "main");

    public static final Identifier LIGHT_BLUE_SHIRT = AttireCraft.modId("textures/entity/equipment/shirt/light_blue.png");
    public static final Identifier GRAY_BLAZER = AttireCraft.modId("textures/entity/equipment/blazer/gray.png");

    public static void initialize() {
        ModelLayerRegistry.registerModelLayer(SHIRT_MODEL, AttireCraftClient::createShirtLayerDefinition);
        ModelLayerRegistry.registerModelLayer(BLAZER_MODEL, AttireCraftClient::createBlazerLayerDefinition);

        ArmorRenderer.register(ctx -> new NestingArmorRenderer(new HumanoidModel<>(ctx.bakeLayer(SHIRT_MODEL)), LIGHT_BLUE_SHIRT), AttireCraft.ModItems.LIGHT_BLUE_SHIRT);
        ArmorRenderer.register(ctx -> new NestingArmorRenderer(new HumanoidModel<>(ctx.bakeLayer(BLAZER_MODEL)), GRAY_BLAZER), AttireCraft.ModItems.GRAY_BLAZER);
    }

    public static LayerDefinition createShirtLayerDefinition() {
        var mesh = new MeshDefinition();
        var root = mesh.getRoot();
        var deformation = new CubeDeformation(0.5F);

        var head = root.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0, 0, 0));
        head.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("body",
                CubeListBuilder.create()
                        .texOffs(0, 0).addBox(-4, 0, -2, 8, 12, 4, deformation)
                        .texOffs(0, 16).addBox(-3, 0, -2, 6, 12, 4, deformation.extend(0.5F + 0.008F)),
                PartPose.offset(0, 0, 0));
        root.addOrReplaceChild("right_arm",
                CubeListBuilder.create().texOffs(24, 0).addBox(-3, -2, -2, 4, 12, 4, deformation),
                PartPose.offset(-5, 2, 0));
        root.addOrReplaceChild("left_arm",
                CubeListBuilder.create().texOffs(24, 0).mirror().addBox(-1, -2, -2, 4, 12, 4, deformation),
                PartPose.offset(5, 2, 0));
        root.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);

        return LayerDefinition.create(mesh, 48, 32);
    }

    public static LayerDefinition createBlazerLayerDefinition() {
        var mesh = new MeshDefinition();
        var root = mesh.getRoot();
        var deformation = new CubeDeformation(1F - 0.008F);

        var head = root.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0, 0, 0));
        head.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);

        root.addOrReplaceChild("body",
                CubeListBuilder.create().texOffs(0, 0).addBox(-4, 0, -2, 8, 12, 4, deformation),
                PartPose.offset(0, 0, 0));
        root.addOrReplaceChild("right_arm",
                CubeListBuilder.create().texOffs(24, 0).addBox(-3, -2, -2, 4, 12, 4, new CubeDeformation(0.758F)),
                PartPose.offset(-5, 2, 0));
        root.addOrReplaceChild("left_arm",
                CubeListBuilder.create().texOffs(24, 0).mirror().addBox(-1, -2, -2, 4, 12, 4, new CubeDeformation(0.758F)),
                PartPose.offset(5, 2, 0));
        root.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);

        return LayerDefinition.create(mesh, 48, 16);
    }
}
