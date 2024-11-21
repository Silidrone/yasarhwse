package chapter3.pathfinding.alg.dijkstra;

import chapter3.pathfinding.Connection;
import chapter3.pathfinding.alg.PathfindingNode;


public class DijkstraNode<T> implements PathfindingNode<T> {
    T node;
    double costSoFar;
    Connection<T> connection;

    public DijkstraNode(T node, double costSoFar, Connection<T> connection) {
        this.node = node;
        this.costSoFar = costSoFar;
        this.connection = connection;
    }

    public T getNode() {
        return node;
    }

    public double getEstimatedCost() {
        return costSoFar;
    }

    public Connection<T> getConnection() {
        return connection;
    }
}
