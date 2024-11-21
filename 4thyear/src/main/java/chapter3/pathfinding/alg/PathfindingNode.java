package chapter3.pathfinding.alg;

public interface PathfindingNode<T> {
    T getNode();
    double getEstimatedCost();
}
