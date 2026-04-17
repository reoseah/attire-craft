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

public record NestingProperties(HolderSet<Item> allowed) {
    public static final Codec<NestingProperties> CODEC = RecordCodecBuilder.create(i -> i
            .group(
                    HolderSetCodec.create(Registries.ITEM, Item.CODEC, false)
                            .fieldOf("allowed")
                            .forGetter(NestingProperties::allowed)
            )
            .apply(i, NestingProperties::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, NestingProperties> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.holderSet(Registries.ITEM),
            NestingProperties::allowed,
            NestingProperties::new);
}
