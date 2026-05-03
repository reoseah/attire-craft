package attirecraft;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.ClientTooltipComponentCallback;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStackTemplate;

import java.util.EnumSet;
import java.util.List;

@Environment(EnvType.CLIENT)
public class AttireCraftClient {
    public static final ModelLayerLocation SHIRT_MODEL = new ModelLayerLocation(AttireCraft.modId("shirt"), "main");
    public static final ModelLayerLocation NECK_TIE_MODEL = new ModelLayerLocation(AttireCraft.modId("neck_tie"), "main");
    public static final ModelLayerLocation BLAZER_MODEL = new ModelLayerLocation(AttireCraft.modId("blazer"), "main");
    public static final ModelLayerLocation TROUSERS_MODEL = new ModelLayerLocation(AttireCraft.modId("trousers"), "main");

    public static final Identifier WHITE_SHIRT = AttireCraft.modId("attire/equipment/shirt_white");
    public static final Identifier ORANGE_SHIRT = AttireCraft.modId("attire/equipment/shirt_orange");
    public static final Identifier MAGENTA_SHIRT = AttireCraft.modId("attire/equipment/shirt_magenta");
    public static final Identifier LIGHT_BLUE_SHIRT = AttireCraft.modId("attire/equipment/shirt_light_blue");
    public static final Identifier YELLOW_SHIRT = AttireCraft.modId("attire/equipment/shirt_yellow");
    public static final Identifier LIME_SHIRT = AttireCraft.modId("attire/equipment/shirt_lime");
    public static final Identifier PINK_SHIRT = AttireCraft.modId("attire/equipment/shirt_pink");
    public static final Identifier GRAY_SHIRT = AttireCraft.modId("attire/equipment/shirt_gray");
    public static final Identifier LIGHT_GRAY_SHIRT = AttireCraft.modId("attire/equipment/shirt_light_gray");
    public static final Identifier CYAN_SHIRT = AttireCraft.modId("attire/equipment/shirt_cyan");
    public static final Identifier PURPLE_SHIRT = AttireCraft.modId("attire/equipment/shirt_purple");
    public static final Identifier BLUE_SHIRT = AttireCraft.modId("attire/equipment/shirt_blue");
    public static final Identifier BROWN_SHIRT = AttireCraft.modId("attire/equipment/shirt_brown");
    public static final Identifier GREEN_SHIRT = AttireCraft.modId("attire/equipment/shirt_green");
    public static final Identifier RED_SHIRT = AttireCraft.modId("attire/equipment/shirt_red");
    public static final Identifier BLACK_SHIRT = AttireCraft.modId("attire/equipment/shirt_black");

    public static final Identifier TRADER_LLAMA_NECK_TIE = AttireCraft.modId("textures/entity/equipment/trader_llama_neck_tie.png");

    public static final Identifier WHITE_BLAZER = AttireCraft.modId("attire/equipment/blazer_white");
    public static final Identifier ORANGE_BLAZER = AttireCraft.modId("attire/equipment/blazer_orange");
    public static final Identifier MAGENTA_BLAZER = AttireCraft.modId("attire/equipment/blazer_magenta");
    public static final Identifier LIGHT_BLUE_BLAZER = AttireCraft.modId("attire/equipment/blazer_light_blue");
    public static final Identifier YELLOW_BLAZER = AttireCraft.modId("attire/equipment/blazer_yellow");
    public static final Identifier LIME_BLAZER = AttireCraft.modId("attire/equipment/blazer_lime");
    public static final Identifier PINK_BLAZER = AttireCraft.modId("attire/equipment/blazer_pink");
    public static final Identifier GRAY_BLAZER = AttireCraft.modId("attire/equipment/blazer_gray");
    public static final Identifier LIGHT_GRAY_BLAZER = AttireCraft.modId("attire/equipment/blazer_light_gray");
    public static final Identifier CYAN_BLAZER = AttireCraft.modId("attire/equipment/blazer_cyan");
    public static final Identifier PURPLE_BLAZER = AttireCraft.modId("attire/equipment/blazer_purple");
    public static final Identifier BLUE_BLAZER = AttireCraft.modId("attire/equipment/blazer_blue");
    public static final Identifier BROWN_BLAZER = AttireCraft.modId("attire/equipment/blazer_brown");
    public static final Identifier GREEN_BLAZER = AttireCraft.modId("attire/equipment/blazer_green");
    public static final Identifier RED_BLAZER = AttireCraft.modId("attire/equipment/blazer_red");
    public static final Identifier BLACK_BLAZER = AttireCraft.modId("attire/equipment/blazer_black");

    public static final Identifier WHITE_TROUSERS = AttireCraft.modId("attire/equipment/trousers_white");
    public static final Identifier ORANGE_TROUSERS = AttireCraft.modId("attire/equipment/trousers_orange");
    public static final Identifier MAGENTA_TROUSERS = AttireCraft.modId("attire/equipment/trousers_magenta");
    public static final Identifier LIGHT_BLUE_TROUSERS = AttireCraft.modId("attire/equipment/trousers_light_blue");
    public static final Identifier YELLOW_TROUSERS = AttireCraft.modId("attire/equipment/trousers_yellow");
    public static final Identifier LIME_TROUSERS = AttireCraft.modId("attire/equipment/trousers_lime");
    public static final Identifier PINK_TROUSERS = AttireCraft.modId("attire/equipment/trousers_pink");
    public static final Identifier GRAY_TROUSERS = AttireCraft.modId("attire/equipment/trousers_gray");
    public static final Identifier LIGHT_GRAY_TROUSERS = AttireCraft.modId("attire/equipment/trousers_light_gray");
    public static final Identifier CYAN_TROUSERS = AttireCraft.modId("attire/equipment/trousers_cyan");
    public static final Identifier PURPLE_TROUSERS = AttireCraft.modId("attire/equipment/trousers_purple");
    public static final Identifier BLUE_TROUSERS = AttireCraft.modId("attire/equipment/trousers_blue");
    public static final Identifier BROWN_TROUSERS = AttireCraft.modId("attire/equipment/trousers_brown");
    public static final Identifier GREEN_TROUSERS = AttireCraft.modId("attire/equipment/trousers_green");
    public static final Identifier RED_TROUSERS = AttireCraft.modId("attire/equipment/trousers_red");
    public static final Identifier BLACK_TROUSERS = AttireCraft.modId("attire/equipment/trousers_black");

    public static void initialize() {
        ModelLayerRegistry.registerModelLayer(SHIRT_MODEL, AttireCraftClient::createShirtLayerDefinition);
        ModelLayerRegistry.registerModelLayer(NECK_TIE_MODEL, AttireCraftClient::createNeckTieLayerDefinition);
        ModelLayerRegistry.registerModelLayer(BLAZER_MODEL, AttireCraftClient::createBlazerLayerDefinition);
        ModelLayerRegistry.registerModelLayer(TROUSERS_MODEL, AttireCraftClient::createPantsLayerDefinition);

        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, SHIRT_MODEL, WHITE_SHIRT), AttireCraft.ModItems.WHITE_SHIRT);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, SHIRT_MODEL, ORANGE_SHIRT), AttireCraft.ModItems.ORANGE_SHIRT);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, SHIRT_MODEL, MAGENTA_SHIRT), AttireCraft.ModItems.MAGENTA_SHIRT);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, SHIRT_MODEL, LIGHT_BLUE_SHIRT), AttireCraft.ModItems.LIGHT_BLUE_SHIRT);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, SHIRT_MODEL, YELLOW_SHIRT), AttireCraft.ModItems.YELLOW_SHIRT);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, SHIRT_MODEL, LIME_SHIRT), AttireCraft.ModItems.LIME_SHIRT);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, SHIRT_MODEL, PINK_SHIRT), AttireCraft.ModItems.PINK_SHIRT);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, SHIRT_MODEL, GRAY_SHIRT), AttireCraft.ModItems.GRAY_SHIRT);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, SHIRT_MODEL, LIGHT_GRAY_SHIRT), AttireCraft.ModItems.LIGHT_GRAY_SHIRT);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, SHIRT_MODEL, CYAN_SHIRT), AttireCraft.ModItems.CYAN_SHIRT);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, SHIRT_MODEL, PURPLE_SHIRT), AttireCraft.ModItems.PURPLE_SHIRT);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, SHIRT_MODEL, BLUE_SHIRT), AttireCraft.ModItems.BLUE_SHIRT);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, SHIRT_MODEL, BROWN_SHIRT), AttireCraft.ModItems.BROWN_SHIRT);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, SHIRT_MODEL, GREEN_SHIRT), AttireCraft.ModItems.GREEN_SHIRT);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, SHIRT_MODEL, RED_SHIRT), AttireCraft.ModItems.RED_SHIRT);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, SHIRT_MODEL, BLACK_SHIRT), AttireCraft.ModItems.BLACK_SHIRT);

        ArmorRenderer.register(ctx -> new SimpleArmorRenderer(new HumanoidModel<>(ctx.bakeLayer(NECK_TIE_MODEL)), TRADER_LLAMA_NECK_TIE), AttireCraft.ModItems.TRADER_LLAMA_NECK_TIE);

        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, BLAZER_MODEL, WHITE_BLAZER), AttireCraft.ModItems.WHITE_BLAZER);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, BLAZER_MODEL, ORANGE_BLAZER), AttireCraft.ModItems.ORANGE_BLAZER);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, BLAZER_MODEL, MAGENTA_BLAZER), AttireCraft.ModItems.MAGENTA_BLAZER);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, BLAZER_MODEL, LIGHT_BLUE_BLAZER), AttireCraft.ModItems.LIGHT_BLUE_BLAZER);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, BLAZER_MODEL, YELLOW_BLAZER), AttireCraft.ModItems.YELLOW_BLAZER);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, BLAZER_MODEL, LIME_BLAZER), AttireCraft.ModItems.LIME_BLAZER);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, BLAZER_MODEL, PINK_BLAZER), AttireCraft.ModItems.PINK_BLAZER);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, BLAZER_MODEL, GRAY_BLAZER), AttireCraft.ModItems.GRAY_BLAZER);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, BLAZER_MODEL, LIGHT_GRAY_BLAZER), AttireCraft.ModItems.LIGHT_GRAY_BLAZER);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, BLAZER_MODEL, CYAN_BLAZER), AttireCraft.ModItems.CYAN_BLAZER);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, BLAZER_MODEL, PURPLE_BLAZER), AttireCraft.ModItems.PURPLE_BLAZER);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, BLAZER_MODEL, BLUE_BLAZER), AttireCraft.ModItems.BLUE_BLAZER);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, BLAZER_MODEL, BROWN_BLAZER), AttireCraft.ModItems.BROWN_BLAZER);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, BLAZER_MODEL, GREEN_BLAZER), AttireCraft.ModItems.GREEN_BLAZER);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, BLAZER_MODEL, RED_BLAZER), AttireCraft.ModItems.RED_BLAZER);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, BLAZER_MODEL, BLACK_BLAZER), AttireCraft.ModItems.BLACK_BLAZER);

        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, TROUSERS_MODEL, WHITE_TROUSERS), AttireCraft.ModItems.WHITE_TROUSERS);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, TROUSERS_MODEL, ORANGE_TROUSERS), AttireCraft.ModItems.ORANGE_TROUSERS);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, TROUSERS_MODEL, MAGENTA_TROUSERS), AttireCraft.ModItems.MAGENTA_TROUSERS);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, TROUSERS_MODEL, LIGHT_BLUE_TROUSERS), AttireCraft.ModItems.LIGHT_BLUE_TROUSERS);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, TROUSERS_MODEL, YELLOW_TROUSERS), AttireCraft.ModItems.YELLOW_TROUSERS);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, TROUSERS_MODEL, LIME_TROUSERS), AttireCraft.ModItems.LIME_TROUSERS);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, TROUSERS_MODEL, PINK_TROUSERS), AttireCraft.ModItems.PINK_TROUSERS);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, TROUSERS_MODEL, GRAY_TROUSERS), AttireCraft.ModItems.GRAY_TROUSERS);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, TROUSERS_MODEL, LIGHT_GRAY_TROUSERS), AttireCraft.ModItems.LIGHT_GRAY_TROUSERS);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, TROUSERS_MODEL, CYAN_TROUSERS), AttireCraft.ModItems.CYAN_TROUSERS);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, TROUSERS_MODEL, PURPLE_TROUSERS), AttireCraft.ModItems.PURPLE_TROUSERS);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, TROUSERS_MODEL, BLUE_TROUSERS), AttireCraft.ModItems.BLUE_TROUSERS);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, TROUSERS_MODEL, BROWN_TROUSERS), AttireCraft.ModItems.BROWN_TROUSERS);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, TROUSERS_MODEL, GREEN_TROUSERS), AttireCraft.ModItems.GREEN_TROUSERS);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, TROUSERS_MODEL, RED_TROUSERS), AttireCraft.ModItems.RED_TROUSERS);
        ArmorRenderer.register(ctx -> AtlasArmorRenderer.withArmorTrimAtlas(ctx, TROUSERS_MODEL, BLACK_TROUSERS), AttireCraft.ModItems.BLACK_TROUSERS);

        ClientTooltipComponentCallback.EVENT.register(component -> component instanceof NestedEquipmentTooltipComponent(
                List<ItemStackTemplate> equipment
        ) ? new NestedEquipmentTooltipComponent.Clientside((equipment)) : null);
    }

    public static LayerDefinition createShirtLayerDefinition() {
        var mesh = new MeshDefinition();
        var root = mesh.getRoot();
        var deformation = new CubeDeformation(0.5F);

        var head = root.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0, 0, 0));
        head.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4, 0, -2, 8, 12, 4, deformation).texOffs(0, 16).addBox(-3, 0, -2, 6, 12, 4, deformation.extend(0.5F + 0.008F)), PartPose.offset(0, 0, 0));
        root.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(24, 0).addBox(-3, -2, -2, 4, 12, 4, deformation), PartPose.offset(-5, 2, 0));
        root.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(24, 0).mirror().addBox(-1, -2, -2, 4, 12, 4, deformation), PartPose.offset(5, 2, 0));
        root.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);

        return LayerDefinition.create(mesh, 48, 32);
    }

    public static LayerDefinition createBlazerLayerDefinition() {
        var mesh = new MeshDefinition();
        var root = mesh.getRoot();
        var deformation = new CubeDeformation(1F - 0.008F);
        var handsDeformation = new CubeDeformation(0.758F);

        var head = root.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0, 0, 0));
        head.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);

        root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4, 0, -2, 8, 12, 4, deformation), PartPose.offset(0, 0, 0));
        root.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(24, 0).addBox(-3, -2, -2, 4, 12, 4, handsDeformation), PartPose.offset(-5, 2, 0));
        root.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(24, 0).mirror().addBox(-1, -2, -2, 4, 12, 4, handsDeformation), PartPose.offset(5, 2, 0));
        root.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);

        return LayerDefinition.create(mesh, 48, 16);
    }

    public static LayerDefinition createNeckTieLayerDefinition() {
        var mesh = new MeshDefinition();
        var root = mesh.getRoot();
        var deformation = new CubeDeformation(1F - 0.008F);

        var head = root.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.offset(0, 0, 0));
        head.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);

        root.addOrReplaceChild("body", CubeListBuilder.create()
                        .texOffs(-4, -4)
                        .attirecraft$addBox(-4, 0, -2, 8, 8, 4, deformation, EnumSet.of(Direction.NORTH)),
                PartPose.offset(0, 0, 0));
        root.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("right_leg", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("left_leg", CubeListBuilder.create(), PartPose.ZERO);

        return LayerDefinition.create(mesh, 16, 16);
    }


    public static LayerDefinition createPantsLayerDefinition() {
        var mesh = new MeshDefinition();
        var root = mesh.getRoot();
        var beltDeformation = new CubeDeformation(0.508F);
        var legsDeformation = new CubeDeformation(0.508F);

        var head = root.addOrReplaceChild("head", CubeListBuilder.create(), PartPose.ZERO);
        head.addOrReplaceChild("hat", CubeListBuilder.create(), PartPose.ZERO);

        root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4, 0, -2, 8, 12, 4, beltDeformation), PartPose.offset(0.0F, 0.0F, 0.0F));
        root.addOrReplaceChild("right_arm", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("left_arm", CubeListBuilder.create(), PartPose.ZERO);
        root.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(24, 0).addBox(-2, 0, -2, 4, 12, 4, legsDeformation), PartPose.offset(-1.9F, 12, 0));
        root.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(24, 0).mirror().addBox(-2, 0, -2, 4, 12, 4, legsDeformation), PartPose.offset(1.9F, 12, 0));

        return LayerDefinition.create(mesh, 48, 16);
    }
}
