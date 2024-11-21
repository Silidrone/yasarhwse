package chapter3.pathfinding.alg.astar;

import chapter3.pathfinding.Graph;

public interface AStarHeuristic<T> {

    double estimate(Graph<T> graph, T node, T target);
}
