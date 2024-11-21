package chapter3.pathfinding.alg;

import chapter3.pathfinding.Graph;
import chapter3.pathfinding.Path;

import java.util.Set;

public interface PathfindingAlgorithm<T> {

    Path<T> findPath(Graph<T> graph, T start, T end);


    Path<T> getPath();


}
