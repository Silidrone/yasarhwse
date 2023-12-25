package rl.tasks.grid2D;

import rl.base.RLState;

/**
 * Represents a state of the Grid2D environment
 */
public class Grid2DState implements RLState {
    private static final double DEFAULT_REWARD = -1; // Reward for stepping any state is -1
    int row;                                         // unless it is a terminal state
    int col;
    boolean obstacle; // Is this state obstacle(unreachable)?
    boolean terminal; // Is this state terminal?

    double stateReward; // The reward for stepping into this  state

    @Override
    public int hashCode() {
        return (row+"-"+ col).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Grid2DState))
            return false;
        Grid2DState gobj = (Grid2DState) obj;
        return (row== gobj.row )&& (col== gobj.col);
    }

    public Grid2DState(int r, int c) {
        row = r;
        col = c;
        obstacle = false;
        terminal = false;
        stateReward = DEFAULT_REWARD;
    }

    @Override
    public String toString() {
        return "["+row+"-"+col+"] " ;
    }

    // Getters & setters
    public void setTerminal(boolean terminal, double reward) {
        this.terminal = terminal;
        this.stateReward= reward;
    }

    public void setObstacle(boolean abstacle) {
        this.obstacle = abstacle;
        stateReward =0;
        terminal = true;
    }

    public boolean isTerminal() {
        return terminal;
    }

    public double stateReward() {
        return stateReward;
    }

    public boolean isObstacle() {
        return obstacle;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

}
