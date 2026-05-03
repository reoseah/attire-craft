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

    public static final DataComponentType<Nestable> NESTABLE = DataComponentType.<Nestable>builder().persistent(Nestable.CODEC).networkSynchronized(Nestable.STREAM_CODEC).build();
    public static final DataComponentType<List<ItemStackTemplate>> NESTED_EQUIPMENT = DataComponentType.<List<ItemStackTemplate>>builder().persistent(ItemStackTemplate.CODEC.listOf(1, 64)).networkSynchronized(ItemStackTemplate.STREAM_CODEC.apply(ByteBufCodecs.list(64))).build();

    public static void initialize() {
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, modId("item_group"), CREATIVE_MODE_TAB);
        Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, modId("nestable"), NESTABLE);
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

            group.accept(ModItems.TRADER_LLAMA_NECK_TIE);

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

            group.accept(ModItems.WHITE_TROUSERS);
            group.accept(ModItems.ORANGE_TROUSERS);
            group.accept(ModItems.MAGENTA_TROUSERS);
            group.accept(ModItems.LIGHT_BLUE_TROUSERS);
            group.accept(ModItems.YELLOW_TROUSERS);
            group.accept(ModItems.LIME_TROUSERS);
            group.accept(ModItems.PINK_TROUSERS);
            group.accept(ModItems.GRAY_TROUSERS);
            group.accept(ModItems.LIGHT_GRAY_TROUSERS);
            group.accept(ModItems.CYAN_TROUSERS);
            group.accept(ModItems.PURPLE_TROUSERS);
            group.accept(ModItems.BLUE_TROUSERS);
            group.accept(ModItems.BROWN_TROUSERS);
            group.accept(ModItems.GREEN_TROUSERS);
            group.accept(ModItems.RED_TROUSERS);
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

        public static final TagKey<Item> SHIRTS = create("shirts");
        public static final TagKey<Item> SHIRTS_NESTABLE = create("nestable/shirts");

        public static final TagKey<Item> NECK_TIES = create("neck_ties");
        public static final TagKey<Item> NECK_TIES_NESTABLE = create("nestable/neck_ties");

        private static TagKey<Item> create(String name) {
            return TagKey.create(Registries.ITEM, modId(name));
        }
    }

    public static class ModItems {
        private static final HolderGetter<Item> LOOKUP = BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.ITEM);

        public static final Nestable SHIRT = new Nestable(
                LOOKUP.getOrThrow(ModItemTags.SHIRTS),
                LOOKUP.getOrThrow(ModItemTags.SHIRTS_NESTABLE)
        );
        public static final Item WHITE_SHIRT = register("white_shirt", chestArmor().component(NESTABLE, SHIRT));
        public static final Item ORANGE_SHIRT = register("orange_shirt", chestArmor().component(NESTABLE, SHIRT));
        public static final Item MAGENTA_SHIRT = register("magenta_shirt", chestArmor().component(NESTABLE, SHIRT));
        public static final Item LIGHT_BLUE_SHIRT = register("light_blue_shirt", chestArmor().component(NESTABLE, SHIRT));
        public static final Item YELLOW_SHIRT = register("yellow_shirt", chestArmor().component(NESTABLE, SHIRT));
        public static final Item LIME_SHIRT = register("lime_shirt", chestArmor().component(NESTABLE, SHIRT));
        public static final Item PINK_SHIRT = register("pink_shirt", chestArmor().component(NESTABLE, SHIRT));
        public static final Item GRAY_SHIRT = register("gray_shirt", chestArmor().component(NESTABLE, SHIRT));
        public static final Item LIGHT_GRAY_SHIRT = register("light_gray_shirt", chestArmor().component(NESTABLE, SHIRT));
        public static final Item CYAN_SHIRT = register("cyan_shirt", chestArmor().component(NESTABLE, SHIRT));
        public static final Item PURPLE_SHIRT = register("purple_shirt", chestArmor().component(NESTABLE, SHIRT));
        public static final Item BLUE_SHIRT = register("blue_shirt", chestArmor().component(NESTABLE, SHIRT));
        public static final Item BROWN_SHIRT = register("brown_shirt", chestArmor().component(NESTABLE, SHIRT));
        public static final Item GREEN_SHIRT = register("green_shirt", chestArmor().component(NESTABLE, SHIRT));
        public static final Item RED_SHIRT = register("red_shirt", chestArmor().component(NESTABLE, SHIRT));
        public static final Item BLACK_SHIRT = register("black_shirt", chestArmor().component(NESTABLE, SHIRT));

        public static final Nestable NECK_TIE = new Nestable(
                LOOKUP.getOrThrow(ModItemTags.NECK_TIES),
                LOOKUP.getOrThrow(ModItemTags.NECK_TIES_NESTABLE)
        );
        public static final Item TRADER_LLAMA_NECK_TIE = register("trader_llama_neck_tie", chestArmor().component(NESTABLE, NECK_TIE));

        public static final Item WHITE_BLAZER = register("white_blazer", chestArmor());
        public static final Item ORANGE_BLAZER = register("orange_blazer", chestArmor());
        public static final Item MAGENTA_BLAZER = register("magenta_blazer", chestArmor());
        public static final Item LIGHT_BLUE_BLAZER = register("light_blue_blazer", chestArmor());
        public static final Item YELLOW_BLAZER = register("yellow_blazer", chestArmor());
        public static final Item LIME_BLAZER = register("lime_blazer", chestArmor());
        public static final Item PINK_BLAZER = register("pink_blazer", chestArmor());
        public static final Item GRAY_BLAZER = register("gray_blazer", chestArmor());
        public static final Item LIGHT_GRAY_BLAZER = register("light_gray_blazer", chestArmor());
        public static final Item CYAN_BLAZER = register("cyan_blazer", chestArmor());
        public static final Item PURPLE_BLAZER = register("purple_blazer", chestArmor());
        public static final Item BLUE_BLAZER = register("blue_blazer", chestArmor());
        public static final Item BROWN_BLAZER = register("brown_blazer", chestArmor());
        public static final Item GREEN_BLAZER = register("green_blazer", chestArmor());
        public static final Item RED_BLAZER = register("red_blazer", chestArmor());
        public static final Item BLACK_BLAZER = register("black_blazer", chestArmor());

        public static final Item WHITE_TROUSERS = register("white_trousers", legArmor());
        public static final Item ORANGE_TROUSERS = register("orange_trousers", legArmor());
        public static final Item MAGENTA_TROUSERS = register("magenta_trousers", legArmor());
        public static final Item LIGHT_BLUE_TROUSERS = register("light_blue_trousers", legArmor());
        public static final Item YELLOW_TROUSERS = register("yellow_trousers", legArmor());
        public static final Item LIME_TROUSERS = register("lime_trousers", legArmor());
        public static final Item PINK_TROUSERS = register("pink_trousers", legArmor());
        public static final Item GRAY_TROUSERS = register("gray_trousers", legArmor());
        public static final Item LIGHT_GRAY_TROUSERS = register("light_gray_trousers", legArmor());
        public static final Item CYAN_TROUSERS = register("cyan_trousers", legArmor());
        public static final Item PURPLE_TROUSERS = register("purple_trousers", legArmor());
        public static final Item BLUE_TROUSERS = register("blue_trousers", legArmor());
        public static final Item BROWN_TROUSERS = register("brown_trousers", legArmor());
        public static final Item GREEN_TROUSERS = register("green_trousers", legArmor());
        public static final Item RED_TROUSERS = register("red_trousers", legArmor());
        public static final Item BLACK_TROUSERS = register("black_trousers", legArmor());

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

        private static Item.Properties legArmor() {
            return new Item.Properties().stacksTo(1).component(DataComponents.EQUIPPABLE, Equippable.builder(EquipmentSlot.LEGS).build());
        }
    }
}