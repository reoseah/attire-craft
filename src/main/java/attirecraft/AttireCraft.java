package attirecraft;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.equipment.Equippable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.function.Function;

public class AttireCraft {
    public static final String MOD_ID = "attirecraft";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static final ResourceKey<CreativeModeTab> CREATIVE_MODE_TAB_KEY = ResourceKey.create(Registries.CREATIVE_MODE_TAB, modId("item_group"));
    public static final CreativeModeTab CREATIVE_MODE_TAB = FabricCreativeModeTab.builder().icon(() -> new ItemStack(ModItems.LIGHT_BLUE_SHIRT)).title(Component.translatable("itemGroup.attirecraft")).build();

    public static final DataComponentType<NestingProperties> NESTING_PROPERTIES = DataComponentType.<NestingProperties>builder().persistent(NestingProperties.CODEC).networkSynchronized(NestingProperties.STREAM_CODEC).build();
    public static final DataComponentType<List<ItemStackTemplate>> NESTED_EQUIPMENT = DataComponentType.<List<ItemStackTemplate>>builder().persistent(ItemStackTemplate.CODEC.listOf(1, 64)).networkSynchronized(ItemStackTemplate.STREAM_CODEC.apply(ByteBufCodecs.list(64))).build();

    public static void initialize() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, modId("item_group"), CREATIVE_MODE_TAB);
        Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, modId("nesting_properties"), NESTING_PROPERTIES);
        Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, modId("nested_equipment"), NESTED_EQUIPMENT);

        ModItems.initialize();

        CreativeModeTabEvents.modifyOutputEvent(CREATIVE_MODE_TAB_KEY).register(group -> {
            group.accept(ModItems.WHITE_SHIRT);
            group.accept(ModItems.ORANGE_SHIRT);
            group.accept(ModItems.MAGENTA_SHIRT);
            group.accept(ModItems.LIGHT_BLUE_SHIRT);
            group.accept(ModItems.YELLOW_SHIRT);
            group.accept(ModItems.LIME_SHIRT);
            group.accept(ModItems.PINK_SHIRT);
            group.accept(ModItems.GRAY_SHIRT);
            group.accept(ModItems.LIGHT_GRAY_SHIRT);
            group.accept(ModItems.CYAN_SHIRT);
            group.accept(ModItems.PURPLE_SHIRT);
            group.accept(ModItems.BLUE_SHIRT);
            group.accept(ModItems.BROWN_SHIRT);
            group.accept(ModItems.GREEN_SHIRT);
            group.accept(ModItems.RED_SHIRT);
            group.accept(ModItems.BLACK_SHIRT);

            group.accept(ModItems.WHITE_BLAZER);
            group.accept(ModItems.ORANGE_BLAZER);
            group.accept(ModItems.MAGENTA_BLAZER);
            group.accept(ModItems.LIGHT_BLUE_BLAZER);
            group.accept(ModItems.YELLOW_BLAZER);
            group.accept(ModItems.LIME_BLAZER);
            group.accept(ModItems.PINK_BLAZER);
            group.accept(ModItems.GRAY_BLAZER);
            group.accept(ModItems.LIGHT_GRAY_BLAZER);
            group.accept(ModItems.CYAN_BLAZER);
            group.accept(ModItems.PURPLE_BLAZER);
            group.accept(ModItems.BLUE_BLAZER);
            group.accept(ModItems.BROWN_BLAZER);
            group.accept(ModItems.GREEN_BLAZER);
            group.accept(ModItems.RED_BLAZER);
            group.accept(ModItems.BLACK_BLAZER);

            group.accept(ModItems.GRAY_TROUSERS);
            group.accept(ModItems.BLACK_TROUSERS);
        });
    }

    public static Identifier modId(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }

    public static <T> ResourceKey<T> modKey(ResourceKey<? extends Registry<T>> registryKey, String location) {
        return ResourceKey.create(registryKey, modId(location));
    }

    public static class ModItemTags {
        public static final TagKey<Item> CHEST_OUTERWEAR_NESTABLE = create("nestable/chest_outerwear");
        public static final TagKey<Item> LEGS_OUTERWEAR_NESTABLE = create("nestable/legs_outerwear");

        private static TagKey<Item> create(String name) {
            return TagKey.create(Registries.ITEM, modId(name));
        }
    }

    public static class ModItems {
        private static final HolderGetter<Item> LOOKUP = BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.ITEM);

        public static final Item WHITE_SHIRT = register("white_shirt", chestArmor());
        public static final Item ORANGE_SHIRT = register("orange_shirt", chestArmor());
        public static final Item MAGENTA_SHIRT = register("magenta_shirt", chestArmor());
        public static final Item LIGHT_BLUE_SHIRT = register("light_blue_shirt", chestArmor());
        public static final Item YELLOW_SHIRT = register("yellow_shirt", chestArmor());
        public static final Item LIME_SHIRT = register("lime_shirt", chestArmor());
        public static final Item PINK_SHIRT = register("pink_shirt", chestArmor());
        public static final Item GRAY_SHIRT = register("gray_shirt", chestArmor());
        public static final Item LIGHT_GRAY_SHIRT = register("light_gray_shirt", chestArmor());
        public static final Item CYAN_SHIRT = register("cyan_shirt", chestArmor());
        public static final Item PURPLE_SHIRT = register("purple_shirt", chestArmor());
        public static final Item BLUE_SHIRT = register("blue_shirt", chestArmor());
        public static final Item BROWN_SHIRT = register("brown_shirt", chestArmor());
        public static final Item GREEN_SHIRT = register("green_shirt", chestArmor());
        public static final Item RED_SHIRT = register("red_shirt", chestArmor());
        public static final Item BLACK_SHIRT = register("black_shirt", chestArmor());

        public static final Item WHITE_BLAZER = register("white_blazer", chestArmor().component(AttireCraft.NESTING_PROPERTIES, new NestingProperties(LOOKUP.getOrThrow(ModItemTags.CHEST_OUTERWEAR_NESTABLE))));
        public static final Item ORANGE_BLAZER = register("orange_blazer", chestArmor().component(AttireCraft.NESTING_PROPERTIES, new NestingProperties(LOOKUP.getOrThrow(ModItemTags.CHEST_OUTERWEAR_NESTABLE))));
        public static final Item MAGENTA_BLAZER = register("magenta_blazer", chestArmor().component(AttireCraft.NESTING_PROPERTIES, new NestingProperties(LOOKUP.getOrThrow(ModItemTags.CHEST_OUTERWEAR_NESTABLE))));
        public static final Item LIGHT_BLUE_BLAZER = register("light_blue_blazer", chestArmor().component(AttireCraft.NESTING_PROPERTIES, new NestingProperties(LOOKUP.getOrThrow(ModItemTags.CHEST_OUTERWEAR_NESTABLE))));
        public static final Item YELLOW_BLAZER = register("yellow_blazer", chestArmor().component(AttireCraft.NESTING_PROPERTIES, new NestingProperties(LOOKUP.getOrThrow(ModItemTags.CHEST_OUTERWEAR_NESTABLE))));
        public static final Item LIME_BLAZER = register("lime_blazer", chestArmor().component(AttireCraft.NESTING_PROPERTIES, new NestingProperties(LOOKUP.getOrThrow(ModItemTags.CHEST_OUTERWEAR_NESTABLE))));
        public static final Item PINK_BLAZER = register("pink_blazer", chestArmor().component(AttireCraft.NESTING_PROPERTIES, new NestingProperties(LOOKUP.getOrThrow(ModItemTags.CHEST_OUTERWEAR_NESTABLE))));
        public static final Item GRAY_BLAZER = register("gray_blazer", chestArmor().component(AttireCraft.NESTING_PROPERTIES, new NestingProperties(LOOKUP.getOrThrow(ModItemTags.CHEST_OUTERWEAR_NESTABLE))));
        public static final Item LIGHT_GRAY_BLAZER = register("light_gray_blazer", chestArmor().component(AttireCraft.NESTING_PROPERTIES, new NestingProperties(LOOKUP.getOrThrow(ModItemTags.CHEST_OUTERWEAR_NESTABLE))));
        public static final Item CYAN_BLAZER = register("cyan_blazer", chestArmor().component(AttireCraft.NESTING_PROPERTIES, new NestingProperties(LOOKUP.getOrThrow(ModItemTags.CHEST_OUTERWEAR_NESTABLE))));
        public static final Item PURPLE_BLAZER = register("purple_blazer", chestArmor().component(AttireCraft.NESTING_PROPERTIES, new NestingProperties(LOOKUP.getOrThrow(ModItemTags.CHEST_OUTERWEAR_NESTABLE))));
        public static final Item BLUE_BLAZER = register("blue_blazer", chestArmor().component(AttireCraft.NESTING_PROPERTIES, new NestingProperties(LOOKUP.getOrThrow(ModItemTags.CHEST_OUTERWEAR_NESTABLE))));
        public static final Item BROWN_BLAZER = register("brown_blazer", chestArmor().component(AttireCraft.NESTING_PROPERTIES, new NestingProperties(LOOKUP.getOrThrow(ModItemTags.CHEST_OUTERWEAR_NESTABLE))));
        public static final Item GREEN_BLAZER = register("green_blazer", chestArmor().component(AttireCraft.NESTING_PROPERTIES, new NestingProperties(LOOKUP.getOrThrow(ModItemTags.CHEST_OUTERWEAR_NESTABLE))));
        public static final Item RED_BLAZER = register("red_blazer", chestArmor().component(AttireCraft.NESTING_PROPERTIES, new NestingProperties(LOOKUP.getOrThrow(ModItemTags.CHEST_OUTERWEAR_NESTABLE))));
        public static final Item BLACK_BLAZER = register("black_blazer", chestArmor().component(AttireCraft.NESTING_PROPERTIES, new NestingProperties(LOOKUP.getOrThrow(ModItemTags.CHEST_OUTERWEAR_NESTABLE))));

        public static final Item GRAY_TROUSERS = register("gray_trousers", new Item.Properties().stacksTo(1).component(DataComponents.EQUIPPABLE, Equippable.builder(EquipmentSlot.LEGS).build()).component(AttireCraft.NESTING_PROPERTIES, new NestingProperties(LOOKUP.getOrThrow(ModItemTags.LEGS_OUTERWEAR_NESTABLE))));
        public static final Item BLACK_TROUSERS = register("black_trousers", new Item.Properties().stacksTo(1).component(DataComponents.EQUIPPABLE, Equippable.builder(EquipmentSlot.LEGS).build()).component(AttireCraft.NESTING_PROPERTIES, new NestingProperties(LOOKUP.getOrThrow(ModItemTags.LEGS_OUTERWEAR_NESTABLE))));

        private static void initialize() {
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

        private static Item.Properties chestArmor() {
            return new Item.Properties().stacksTo(1).component(DataComponents.EQUIPPABLE, Equippable.builder(EquipmentSlot.CHEST).build());
        }
    }
}