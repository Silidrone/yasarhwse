package rl.tasks.grid2D;

import org.apache.commons.math3.util.Pair;
import rl.SimpleTransition;
import rl.base.EnvironmentModel;
import rl.base.RLAction;
import rl.base.RLState;
import rl.base.RLTransition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO-1 : Implement missing methods as documented
 *  Read the README document in this folder before
 *  Environment Model of Grid2DWorld
 */
public class Grid2DModel implements EnvironmentModel {

    int rowCount;   // # of Rows
    int colCount;   // # of Columns
    Grid2DState[][] states; // States

    public Grid2DModel(int rowCount, int colCount) {
        this.rowCount = rowCount;
        this.colCount = colCount;

        buildStates();
    }

    /**
     * Creates states as empty cells according to specified row and column count
     */
    private void buildStates() {
        states = new Grid2DState[rowCount][colCount];

        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                states[r][c] = new Grid2DState(r, c);
            }
        }
    }

    /**
     * Makes a given state terminal state with a given reward
     *
     * @param r
     * @param c
     * @param reward
     */
    public void setTerminal(int r, int c, double reward) {
        states[r][c].setTerminal(true, reward);
    }

    /**
     * Makes a given state obstacle (unreachable)
     *
     * @param r
     * @param c
     */
    public void setObstacle(int r, int c) {
        states[r][c].setObstacle(true);
    }

    /**
     * Returns all states as a list
     *
     * @return
     */
    @Override
    public List<RLState> getStates() {
        return Arrays.stream(states).flatMap(Arrays::stream).collect(Collectors.toList());
    }

    /**
     * TODO-1.1 : Implement this function. See the function rightTransition(..)
     * Returns all possible transitions when the agent takes left-action in a given state
     *
     * @param state
     * @return
     */


    /**
     * TODO-1.2 : Implement this function. See the function rightTransition(..)
     * Returns all possible transitions when the agent takes down-action in a given state
     *
     * @param state
     * @return
     */

    /**
     * TODO-1.3 : Implement this function. See the function rightTransition(..)
     * Returns all possible transitions when the agent takes up-action in a given state
     *
     * @param state
     * @return
     */
    /**
     * Returns all possible transitions when the agent takes a given action in a given state
     *
     * @param state
     * @param action
     * @return
     */
    @Override
    public List<RLTransition> getTransitions(RLState state, RLAction action) {
        List<RLTransition> transitions = new ArrayList<>();
        List<RLAction> directions = getActions(state);
        directions = directions.stream().filter(direction -> !direction.equals(GridDirection.getOpposite((GridDirection) action))).toList();
        Grid2DState gridState = (Grid2DState) state;

        for(RLAction direction : directions) {
            double prob = direction.equals(action) ? 0.8 : 0.1;
            Grid2DState nextState = getState(((GridDirection) direction).apply(gridState));
            if(nextState == null || nextState.isObstacle()) {
                transitions.add(new SimpleTransition(state, prob, gridState.stateReward));
            } else {
                transitions.add(new SimpleTransition(nextState, prob, nextState.stateReward));
            }
        }

        return transitions;
    }

    /**
     * TODO-1.4 : Implement This function
     * Returns all possible actions the agent can take in a given state
     * See the README file to understand what are the actions in a particular state
     *
     * @param state
     * @return
     */
    @Override
    public List<RLAction> getActions(RLState state) {
        return List.of(new GridDirection[]{GridDirection.Left, GridDirection.Right, GridDirection.Up, GridDirection.Down});
    }

    /**
     * Returns if the given state is terminal
     *
     * @param state
     * @return
     */
    @Override
    public boolean isTerminal(RLState state) {
        return ((Grid2DState) state).isTerminal();
    }


    /**
     * Returns the state for a given row and column of the grid
     *
     * @param r
     * @param c
     * @return
     */
    public Grid2DState getState(int r, int c) {
        return (c >= 0 && r >= 0 && c < colCount && r < rowCount) ? states[r][c] : null;
    }

    public Grid2DState getState(Pair<Integer, Integer> coordinates) {
        return getState(coordinates.getFirst(), coordinates.getSecond());
    }
}
