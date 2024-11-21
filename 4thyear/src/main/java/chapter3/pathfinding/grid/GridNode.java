package chapter3.pathfinding.grid;

public class GridNode {
    final int row;
    final int col;

    boolean enabled;

    public GridNode(int row, int col) {
        this.row = row;
        this.col = col;
        enabled = true;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof GridNode))
            return false;
        GridNode node = (GridNode) obj;
        return node.col== col && node.row==row;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(row)*Integer.hashCode(col);
    }

    @Override
    public String toString() {
        return "["+row+"-"+ col+"]";
    }
}
