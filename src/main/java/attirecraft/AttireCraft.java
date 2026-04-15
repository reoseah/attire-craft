package attirecraft;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.equipment.Equippable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

public class AttireCraft {
    public static final String MOD_ID = "attirecraft";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final ModelLayerLocation LIGHT_BLUE_SHIRT = new ModelLayerLocation(modId("light_blue_shirt"), "main");

    public static final ResourceKey<CreativeModeTab> CREATIVE_MODE_TAB_KEY = ResourceKey.create(BuiltInRegistries.CREATIVE_MODE_TAB.key(), Identifier.fromNamespaceAndPath(MOD_ID, "item_group"));

    public static final CreativeModeTab CREATIVE_MODE_TAB = FabricCreativeModeTab.builder() //
            .icon(() -> new ItemStack(ModItems.LIGHT_BLUE_SHIRT)) //
            .title(Component.translatable("itemGroup.attirecraft")) //
            .build();

    public static void initialize() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, modId("item_group"), CREATIVE_MODE_TAB);

        ModItems.initialize();
    }

    @Environment(EnvType.CLIENT)
    public static void initializeClient() {
        ModItems.initializeClient();
        ModelLayerRegistry.registerModelLayer(LIGHT_BLUE_SHIRT, ShirtArmorRenderer::createLayerDefinition);
    }

    public static Identifier modId(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    public static <T> ResourceKey<T> modKey(ResourceKey<? extends Registry<T>> registryKey, String location) {
        return ResourceKey.create(registryKey, modId(location));
    }

    public static class ModItems {
        public static final Item LIGHT_BLUE_SHIRT = register("light_blue_shirt", new Item.Properties()
                .stacksTo(1)
                .component(DataComponents.EQUIPPABLE, Equippable.builder(EquipmentSlot.CHEST).build()) //
        );
        public static final Item GRAY_BLAZER = register("gray_blazer", new Item.Properties()
                .stacksTo(1)
                .component(DataComponents.EQUIPPABLE, Equippable.builder(EquipmentSlot.CHEST).build()) //
        );

        private static void initialize() {
            CreativeModeTabEvents.modifyOutputEvent(CREATIVE_MODE_TAB_KEY) //
                    .register(group -> {
                        group.accept(LIGHT_BLUE_SHIRT);
                        group.accept(GRAY_BLAZER);
                    });
        }

        @Environment(EnvType.CLIENT)
        private static void initializeClient() {
            ArmorRenderer.register(ctx -> new ShirtArmorRenderer(ctx, ShirtArmorRenderer.LIGHT_BLUE), LIGHT_BLUE_SHIRT);
        }

        private static Item register(String name) {
            return register(name, Item::new);
        }

        private static Item register(String name, Function<Item.Properties, Item> constructor) {
            return register(name, constructor, new Item.Properties());
        }

        private static Item register(String name, Item.Properties properties) {
            return register(name, Item::new, properties);
        }

        private static Item register(String name, Function<Item.Properties, Item> constructor, Item.Properties properties) {
            var id = modId(name);
            properties.setId(ResourceKey.create(Registries.ITEM, id));
            return Registry.register(BuiltInRegistries.ITEM, id, constructor.apply(properties));
        }
    }
}