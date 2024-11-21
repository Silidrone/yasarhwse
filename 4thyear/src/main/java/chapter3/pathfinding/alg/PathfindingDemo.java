package chapter3.pathfinding.alg;

import chapter3.pathfinding.Graph;

import java.util.Set;

public interface PathfindingDemo<T> extends PathfindingAlgorithm<T> {
    void initDemo(Graph<T> graph, T start, T end);
    boolean isOver();
    void step();

    Set<T> openList();
    Set<T> closedList();
    T current();

    void reset();
}
