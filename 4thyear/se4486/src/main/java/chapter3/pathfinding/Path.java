package chapter3.pathfinding;

import java.util.List;

/**
 * A sequential list of connections representing a path
 * @param <T>
 */
public interface Path<T> {

    List<Connection<T>> path();
    double weight();
}
