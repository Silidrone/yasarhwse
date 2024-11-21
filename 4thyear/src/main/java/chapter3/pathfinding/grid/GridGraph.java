package chapter3.pathfinding.grid;

import chapter3.pathfinding.Connection;
import chapter3.pathfinding.Graph;
import chapter3.pathfinding.SimpleConnection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GridGraph<T extends GridNode> implements Graph<T> {

    final static int[][] deltaNeighbors ={ {1,0}, {0,1}, {-1,0},{0,-1}};
    private static final int ROW = 0;
    private static final int COL = 1;

    GridNode[][] nodes;
    HashMap<T,List<Connection<T>>> fromMap;
    private final double edgeCost;

    public static GridGraph<GridNode> gridOf(int row, int col,double edgeCost)
    {
        return new GridGraph<>(row,col,edgeCost);
    }

    public GridGraph(T[][] nodes, double edgeWeight)
    {
        this.edgeCost = edgeWeight;
        this.nodes = nodes;
        fromMap = new HashMap<>();
        buildConnections();
    }

    private GridGraph(int row, int col,double edgeWeight)
    {
        assert row>0 && col >0;
        this.edgeCost = edgeWeight;
        buildGraph(row,col);
    }

    private void buildGraph(int row, int col) {
        fromMap = new HashMap<>();
        buildNodes(row,col);
        buildConnections();
    }

    private void buildNodes(int row, int col) {
        nodes = new GridNode[row][col];
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                nodes[r][c] = new GridNode(r,c);
            }
        }
    }

    private void buildConnections() {
        for (int r = 0; r < nodes.length; r++) {
            for (int c = 0; c < nodes[r].length; c++) {
                if (!nodes[r][c].isEnabled())
                    continue;
                buildConnectionsFrom(r,c);
            }
        }
    }

    private void buildConnectionsFrom(int r, int c) {
        T node = (T) nodes[r][c];
        fromMap.put(node,new ArrayList<>());

        for (int d = 0; d < deltaNeighbors.length; d++) {
            int nr = r+deltaNeighbors[d][ROW];
            int nc = c+deltaNeighbors[d][COL];
            if (isInside(nr, nc) && nodes[nr][nc].enabled) // TODO is this enough?
            {
                Connection<T> con = new SimpleConnection<>(node, (T) nodes[nr][nc], edgeCost);
                fromMap.get(node).add(con);
            }
        }


    }

    private boolean isInside(int r, int c) {
        return r<nodes.length && r>=0 && c<nodes[0].length && c>=0;
    }



    @Override
    public List<Connection<T>> connectionsOf(GridNode from) {
        return fromMap.get(from);
    }


    public T upOf(T node) {
        if (isInside(node.row-1,node.col))
            return getNode(node.row-1, node.col);
        return null;
    }

    public T downOf(T node) {
        if (isInside(node.row+1,node.col))
            return getNode(node.row+1, node.col);
        return null;
    }
    public T leftOf(T node) {
        if (isInside(node.row,node.col-1))
            return getNode(node.row, node.col-1);
        return null;
    }

    public T rightOf(T node) {
        if (isInside(node.row,node.col+1))
            return getNode(node.row, node.col+1);
        return null;
    }

    /**
     * TODO: Implement disableNode function disableing a given node by
     *          - clearing all connections from/to this node
     * @param r
     * @param c
     */
    public void disableNode(int r, int c)
    {
        if (!nodes[r][c].isEnabled())
            return;

        nodes[r][c].setEnabled(false);

        fromMap.get(nodes[r][c]).clear();

        for (int d = 0; d < deltaNeighbors.length; d++) {
            int nr = r+deltaNeighbors[d][ROW];
            int nc = c+deltaNeighbors[d][COL];

            if (isInside(nr, nc) && nodes[nr][nc].enabled) // TODO is this enough?
            {
                List<Connection<T>> connections = fromMap.get(nodes[nr][nc]);
                connections.removeIf((con)->{ return con.to().row ==r && con.to().col==c ; });
            }
        }
    }

    /**
     * TODO: Implement enableNode function enableing a given node by
     *          - adding all connections betweem this node and its neighbors
     * @param r
     * @param c
     */
    public void enableNode(int r, int c)
    {
        if (nodes[r][c].isEnabled())
            return;

        nodes[r][c].setEnabled(true);

        for (int d = 0; d < deltaNeighbors.length; d++) {
            int nr = r+deltaNeighbors[d][ROW];
            int nc = c+deltaNeighbors[d][COL];

            if (isInside(nr, nc) && nodes[nr][nc].enabled) // TODO is this enough?
            {
                T neighbor = (T) nodes[nr][nc];
                fromMap.get(nodes[r][c]).add((Connection<T>) new SimpleConnection<>(nodes[r][c],neighbor,edgeCost));
                fromMap.get(neighbor).add((Connection<T>) new SimpleConnection<>(neighbor,nodes[r][c],edgeCost));
            }
        }
    }


    public T getNode(int r, int c) {
        assert isInside(r,c);

        return (T) nodes[r][c];
    }

    public String toString() {
        StringBuilder sb =new StringBuilder();
        fromMap.keySet().forEach((n)->{
            sb.append(nodeToStr(n)).append("\n");});

        return sb.toString();
    }

    private String nodeToStr(T n) {
        StringBuilder sb = new StringBuilder();

        sb.append(n);

        fromMap.get(n).forEach((c)->{
            sb.append(c).append("  ");
        });

        return sb.toString();
    }

    /**
     * TODO: Create a 3x4 grid graph. Print it out
     * @param args
     */
    public static void main(String[] args) {
        GridGraph<GridNode> grid = new GridGraph<>(3,4,2.0);

        System.out.println(grid);
    }


}
