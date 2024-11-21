package chapter3.pathfinding;

import java.util.List;

public interface Graph<T> {
    List<Connection<T>> connectionsOf(T from);
}
