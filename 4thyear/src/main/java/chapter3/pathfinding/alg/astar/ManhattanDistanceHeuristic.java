package chapter3.pathfinding.alg.astar;

import chapter3.pathfinding.Graph;
import chapter3.pathfinding.grid.GridNode;

public class ManhattanDistanceHeuristic<T extends GridNode> implements AStarHeuristic<T> {


    private final double edgeWeight;

    public ManhattanDistanceHeuristic(double edgeWeight) {
        this.edgeWeight = edgeWeight;
    }

    @Override
    public double estimate(Graph<T> graph, T node, T target) {
        return  edgeWeight * (Math.abs(node.getRow()-target.getRow()) + Math.abs(node.getCol()-target.getCol()));
    }
}
