package chapter3.pathfinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MappedGraph<T> implements Graph<T>{

    HashMap<T,List<Connection<T>>> fromMap;
    List<T> nodes;

    public MappedGraph() {
        nodes = new ArrayList<T>();
        fromMap = new HashMap<>();
    }

    @SafeVarargs
    public MappedGraph(T... nodes) {
        this();
        Arrays.stream(nodes).forEach(this::addNode);
    }


    /**
     * TODO: Implement addNode function that adds a new node to the graph
     * @param node
     */
    public void addNode(T node)
    {
        nodes.add(node);
        fromMap.put(node, new ArrayList<>());

    }

    /**
     * TODO: Implement connectionsOf function of the Graph interface
     * @param from
     * @return
     */
    @Override
    public List<Connection<T>> connectionsOf(T from) {

        return fromMap.get(from);
    }


    /**
     * TODO: Implement addConnection function that adds a new connection to the graph
     * @param from
     * @param to
     * @param cost
     */
    public void addConnection(T from, T to, double cost)
    {
        assert nodes.contains(from) && nodes.contains(to) : "You can not have connections of absent nodes";
        fromMap.get(from).add(new SimpleConnection<>(from,to,cost));

    }

    @Override
    public String toString() {
        StringBuilder sb =new StringBuilder();
        nodes.forEach((n)->{
            sb.append(nodeToStr(n)).append("\n");});

        return sb.toString();
    }

    private String nodeToStr(T n) {
        StringBuilder sb = new StringBuilder();

        sb.append(n);

        fromMap.get(n).forEach((c)->{
            sb.append(c).append("  ");
        });

        return sb.toString();
    }

    /**
     * TODO: build an arbitrary graph of integers 1,2,3,4,5,6,7
     *       print it out
     * @param args
     */
    public static void main(String[] args) {

        MappedGraph<Integer> g = new MappedGraph<>(1,2,3,4,5,6,7);

        g.addConnection(3,1,5.0);
        g.addConnection(3,4,2.0);
        g.addConnection(4,7,8.0);
        g.addConnection(5,6,1.0);
        g.addConnection(1,2,2.0);
        g.addConnection(7,4,5.0);

        System.out.println(g);


    }
}
