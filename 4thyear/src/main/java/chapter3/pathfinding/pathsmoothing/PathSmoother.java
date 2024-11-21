package chapter3.pathfinding.pathsmoothing;

import chapter3.pathfinding.Path;
import chapter3.pathfinding.WorldRepresentation;

public interface PathSmoother<T> {

    Path<T> smooth(WorldRepresentation<T> world, Path<T> path);
}
