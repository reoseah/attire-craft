package attirecraft;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.HolderSetCodec;
import net.minecraft.world.item.Item;

public record Nestable(HolderSet<Item> exclusiveSet, HolderSet<Item> containers) {
    public static final Codec<Nestable> CODEC = RecordCodecBuilder.create(i -> i
            .group(
                    HolderSetCodec.create(Registries.ITEM, Item.CODEC, false)
                            .fieldOf("exclusive_set")
                            .forGetter(Nestable::exclusiveSet),
                    HolderSetCodec.create(Registries.ITEM, Item.CODEC, false)
                            .fieldOf("containers")
                            .forGetter(Nestable::containers)
            )
            .apply(i, Nestable::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, Nestable> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.holderSet(Registries.ITEM),
            Nestable::exclusiveSet,
            ByteBufCodecs.holderSet(Registries.ITEM),
            Nestable::containers,
            Nestable::new);
}
