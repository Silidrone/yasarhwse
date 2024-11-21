package chapter3.pathfinding;

public interface Connection<T> {
    T from();
    T to();

    double cost();
}
