package chapter3.pathfinding;

/**
 * Simple immutable container class for connection objects
 * @param <T>
 */
public class SimpleConnection<T>  implements Connection<T> {

    private final T from;
    private final T to;
    private final double weight;

    public SimpleConnection(T from, T to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public T from() {
        return from;
    }

    @Override
    public T to() {
        return to;
    }

    @Override
    public double cost() {
        return weight;
    }

    @Override
    public String toString() {
        return "["+from+ "-" +to +":"+weight+"]";
    }
}
