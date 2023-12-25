import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import rl.MapBasedPolicy;
import rl.methods.dp.PolicyIteration;
import rl.methods.dp.ValueIteration;
import rl.tasks.grid2D.Grid2DModel;
import rl.tasks.grid2D.Grid2DPolicyRenderer;
import rl.tasks.grid2D.Grid2DValueRenderer;

public class TestPolicyIteration {

    /**
     * TODO-3 : Implement the test function according to the documentation below
     *       Hint: check the test function in TestValaueIteration class, this will be very similar
     * <p>
     * Creates a Grid2D model by using the GridFactory.createTestrid() method.
     * <p>
     * Performs a Policy Iteration over the grid
     */
    @Test
    void testPolicyIterationOnGrid2D() {
        Grid2DModel grid2D = GridFactory.createTestGrid();

        PolicyIteration pi = new PolicyIteration(new MapBasedPolicy());
        pi.setPolicyRenderer(new Grid2DPolicyRenderer());
        pi.setValueRenderer(new Grid2DValueRenderer());

        pi.perform(grid2D, 10000);
    }
}
