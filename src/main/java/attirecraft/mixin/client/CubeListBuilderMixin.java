package attirecraft.mixin.client;

import attirecraft.ModCubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDefinition;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.core.Direction;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;
import java.util.Set;

@Mixin(CubeListBuilder.class)
public class CubeListBuilderMixin implements ModCubeListBuilder {
    @Shadow
    private @Final List<CubeDefinition> cubes;
    @Shadow
    private int xTexOffs;
    @Shadow
    private int yTexOffs;
    @Shadow
    private boolean mirror;

    @Override
    public CubeListBuilder attirecraft$addBox(float x0, float y0, float z0, float w, float h, float d, CubeDeformation g, Set<Direction> visibleSides) {
        this.cubes.add(new CubeDefinition(null, this.xTexOffs, this.yTexOffs, x0, y0, z0, w, h, d, g, this.mirror, 1.0F, 1.0F, visibleSides));
        return (CubeListBuilder) (Object) this;
    }
}
