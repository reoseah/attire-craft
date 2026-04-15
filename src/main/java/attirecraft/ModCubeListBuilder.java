package attirecraft;

import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.core.Direction;

import java.util.Set;

public interface ModCubeListBuilder {
    default CubeListBuilder attirecraft$addBox(float x0, float y0, float z0, float w, float h, float d, CubeDeformation g, Set<Direction> visibleSides) {
        throw new AssertionError("Implemented with a mixin");
    }
}
