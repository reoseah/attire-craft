package attirecraft;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.HolderSetCodec;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ClickAction;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.BundleItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;

import java.util.List;
import java.util.Optional;

public record NestingProperties(HolderSet<Item> allowedInside) {
    public static final Codec<NestingProperties> CODEC = RecordCodecBuilder.create(i -> i
            .group(
                    HolderSetCodec.create(Registries.ITEM, Item.CODEC, false)
                            .fieldOf("allowed_inside")
                            .forGetter(NestingProperties::allowedInside)
            )
            .apply(i, NestingProperties::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, NestingProperties> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.holderSet(Registries.ITEM),
            NestingProperties::allowedInside,
            NestingProperties::new);

    public Optional<Boolean> handleOverrideStackedOnOther(ItemStack self, Slot slot, ClickAction clickAction, Player player) {
        var other = slot.getItem();
        if (clickAction == ClickAction.PRIMARY && !other.isEmpty()) {
            if (!other.is(this.allowedInside())) {
                BundleItem.playInsertFailSound(player);
                return Optional.of(false);
            }
            // TODO: check that item doesn't conflict with other items already nested, e.g. not placing multiple copies of same thing
            var otherSafe = slot.safeTake(1, 1, player);
            if (otherSafe.isEmpty()) {
                BundleItem.playInsertFailSound(player);
                return Optional.of(false);
            }
            var newEntry = ItemStackTemplate.fromNonEmptyStack(otherSafe);
            var newNestedItems = self.has(AttireCraft.NESTED_EQUIPMENT)
                    ? ImmutableList.copyOf(Iterables.concat(List.of(newEntry), self.get(AttireCraft.NESTED_EQUIPMENT)))
                    : List.of(newEntry);
            self.set(AttireCraft.NESTED_EQUIPMENT, newNestedItems);

            broadcastChangesOnContainerMenu(player);
            BundleItem.playInsertSound(player);
            return Optional.of(true);
        } else if (clickAction == ClickAction.SECONDARY && other.isEmpty()) {
            var nestedItems = self.get(AttireCraft.NESTED_EQUIPMENT);
            if (nestedItems == null || nestedItems.isEmpty()) {
                return Optional.empty();
            }
            var removedEntry = nestedItems.getFirst();
            var inserted = slot.safeInsert(removedEntry.create()).isEmpty();
            if (!inserted) {
                return Optional.of(false);
            }

            var newNestedItems = List.copyOf(nestedItems.subList(1, nestedItems.size()));
            if (newNestedItems.isEmpty()) {
                self.remove(AttireCraft.NESTED_EQUIPMENT);
            } else {
                self.set(AttireCraft.NESTED_EQUIPMENT, newNestedItems);
            }
            broadcastChangesOnContainerMenu(player);
            BundleItem.playRemoveOneSound(player);
            return Optional.of(true);
        }

        return Optional.empty();
    }

    private static void broadcastChangesOnContainerMenu(Player player) {
        var containerMenu = player.containerMenu;
        if (containerMenu != null) {
            containerMenu.slotsChanged(player.getInventory());
        }
    }
}