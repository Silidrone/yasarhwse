package chapter3.pathfinding;

import java.util.ArrayList;
import java.util.List;

public class ListPath<T> implements Path<T> {

    List<Connection<T>> path;
    double weight;

    public ListPath(List<Connection<T>> path, double weight) {
        this.path = path;
        this.weight = weight;
    }

    /**
     * TODO: implement constructor that builds path from a list of connections
     * @param connections
     */
    public ListPath(List<Connection<T>> connections)
    {
        // code here..
    }

    public ListPath() {
        this.path = new ArrayList<>();
        this.weight = 0.0;
    }

    /**
     * TODO: Implement addConnection method to add a new connection to the end of the path
     * @param c
     */
    public void addConnection(Connection<T> c)
    {
        // code here..
    }

    @Override
    public List<Connection<T>> path() {
        return path;
    }

    @Override
    public double weight() {
        return weight;
    }

    @Override
    public String toString() {
        if (path.isEmpty())
            return "<<NO-PATH>>";

        StringBuilder sb= new StringBuilder();

        path.stream().limit(path.size()-1).forEach((n)-> {
            sb.append(n).append("->");
        });

        sb.append(path.get(path.size()-1));

        sb.append(" : ").append(weight);

        return sb.toString();
    }

    public Connection<T> getLast() {
        return path.get(path.size()-1);
    }

    public int size() {
        return path.size();
    }

    public Connection<T> remove(int i) {
        weight -= path.get(i).cost();
        return path.remove(i);
    }

    /**
     * TODO: build a path of 1-3-7-4-2-5 with equal connection weights of 3
     *       print it out
     * @param args
     */
    public static void main(String[] args) {
        // code here..
    }
}
