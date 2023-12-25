package rl.tasks.grid2D;

import org.apache.commons.math3.util.Pair;
import rl.base.RLAction;

/**
 * 4 Directions in 2-Dimension
 */
public enum GridDirection implements RLAction {
    Up("U",-1,0),Down("D",1,0),Left("L",0,-1),Right("R",0,1);

    GridDirection(String shortName, int rowDelta, int colDelta) {
        this.shortName = shortName;
        this.rowDelta = rowDelta;
        this.colDelta = colDelta;
    }

    String shortName;
    int rowDelta;
    int colDelta;

    String shortName(){
        return shortName;
    }

    public int getRowDelta() {
        return rowDelta;
    }

    public int getColDelta() {
        return colDelta;
    }

    public Pair<Integer, Integer> apply(Grid2DState state) {
        return new Pair<>(state.getRow() + rowDelta, state.getCol() + colDelta);
    }

    public static GridDirection getOpposite(GridDirection direction) {
        if(direction == Up) return Down;
        if(direction == Down) return Up;
        if(direction == Left) return Right;
        if(direction == Right) return Left;
        return null;
    }
}
