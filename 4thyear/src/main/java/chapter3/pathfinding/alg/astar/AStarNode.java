package chapter3.pathfinding.alg.astar;

import chapter3.pathfinding.Connection;
import chapter3.pathfinding.alg.PathfindingNode;


public class AStarNode<T> implements PathfindingNode<T> {
    T node;
    double costSoFar;
    double estimatedCost;
    Connection<T> connection;

    public AStarNode(T node, double costSoFar, double estimatedCost,Connection<T> connection) {
        this.node = node;
        this.costSoFar = costSoFar;
        this.connection = connection;
        this.estimatedCost = estimatedCost;
    }

    public T getNode() {
        return node;
    }

    public double getCostSoFar()
    {
        return costSoFar;
    }

    public double getEstimatedCost() {
        return estimatedCost;
    }

    public Connection<T> getConnection() {
        return connection;
    }
}
