package rl.tasks.grid2D;

import rl.base.EnvironmentModel;
import rl.base.RLState;
import rl.base.RLValueRenderer;

import java.util.HashMap;
import java.util.Map;

/**
 * Renderer for the Value Function of Grid2D Environment
 */
public class Grid2DValueRenderer implements RLValueRenderer {
    @Override
    public void renderValues(EnvironmentModel model, HashMap<RLState, Double> v) {
        Grid2DModel grid2D = (Grid2DModel) model;

        for (int r = 0; r < grid2D.rowCount; r++) {
            for (int c = 0; c < grid2D.colCount; c++) {
                RLState state = grid2D.getState(r,c);
                System.out.printf("% 6.5f\t",v.get(state));
            }
            System.out.println();
        }
    }

    @Override
    public void renderValues(Map<RLState, Double> v) {

    }
}
