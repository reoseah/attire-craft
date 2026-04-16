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

public record NestableEquipment(HolderSet<Item> allowed) {
    public static final Codec<NestableEquipment> CODEC = RecordCodecBuilder.create((i) -> i
            .group(
                    HolderSetCodec.create(Registries.ITEM, Item.CODEC, false)
                            .fieldOf("allowed")
                            .forGetter(NestableEquipment::allowed)
            )
            .apply(i, NestableEquipment::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, NestableEquipment> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.holderSet(Registries.ITEM),
            NestableEquipment::allowed,
            NestableEquipment::new);
}
