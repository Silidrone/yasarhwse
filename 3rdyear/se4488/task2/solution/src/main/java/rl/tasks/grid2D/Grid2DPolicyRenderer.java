package rl.tasks.grid2D;

import rl.base.*;

/**
 * Renderer for the Policy of Grid2D Environment
 */
public class Grid2DPolicyRenderer implements RLPolicyRenderer {
    @Override
    public void renderPolicy(EnvironmentModel model, RLPolicy pi) {
        Grid2DModel grid = (Grid2DModel) model;
        DeterministicPolicy dp = (DeterministicPolicy) pi;

        for (int r = 0; r < grid.rowCount; r++) {
            for (int c = 0; c < grid.colCount; c++) {
                Grid2DState state = grid.getState(r,c);
                if (state.isObstacle())
                    System.out.print("@     ");
                else if (state.isTerminal())
                {
                    if (state.stateReward()>0)
                        System.out.print("+     ");
                    else System.out.print("-     ");
                }
                else if (dp.containsActionFor(state))
                    System.out.print(dp.getAction(state)+ "     ");
                else System.out.print("N     ");
            }
            System.out.println();
        }
    }

    @Override
    public void renderPolicy(RLPolicy pi) {
        DeterministicPolicy dp = (DeterministicPolicy) pi;
        for (RLState state:dp.getStates())
            System.out.println(state + " : " + dp.getAction(state).name());
    }
}
