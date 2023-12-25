import org.junit.jupiter.api.Test;
import rl.MapBasedPolicy;
import rl.methods.dp.ValueIteration;
import rl.tasks.grid2D.Grid2DModel;
import rl.tasks.grid2D.Grid2DPolicyRenderer;
import rl.tasks.grid2D.Grid2DValueRenderer;

public class TestValueIteration {

    /**
     * Creates a Grid2D model by using the GridFactory.createTestrid() method.
     *
     * Performs a Value Iteration over the grid
     */
    @Test
    void testValueIterationOnGrid2D()
    {
        Grid2DModel grid2D = GridFactory.createTestGrid();

        ValueIteration vi = new ValueIteration(new MapBasedPolicy());
        vi.setPolicyRenderer(new Grid2DPolicyRenderer());
        vi.setValueRenderer(new Grid2DValueRenderer());

        vi.perform(grid2D,10000);
    }
}
