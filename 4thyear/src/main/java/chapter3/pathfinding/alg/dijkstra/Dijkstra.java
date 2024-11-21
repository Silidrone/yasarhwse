package chapter3.pathfinding.alg.dijkstra;

import chapter3.pathfinding.*;
import chapter3.pathfinding.alg.PathfindingAlgorithm;
import chapter3.pathfinding.alg.PathfindingDemo;
import chapter3.pathfinding.alg.PathfindingList;
import chapter3.pathfinding.grid.GridGraph;

import java.util.*;

public class Dijkstra<T> implements  PathfindingAlgorithm<T>,PathfindingDemo<T> {

    PathfindingList<T> openList;
    HashMap<T, DijkstraNode<T>> closedList;
    private T start;
    private T target;
    private T current;


    private Graph<T> graph;
    private Path<T> path;
    private boolean demoOn;

    public Dijkstra() {
        openList = new PathfindingList<>();
        closedList = new HashMap<>();
    }

    @Override
    public Path<T> findPath(Graph<T> graph, T start, T end) {

        if ( start.equals(end))
            return new ListPath<>(new ArrayList<>(),0.0);


        init(graph,start,end);


        while (!openList.isEmpty() && path==null)
        {
            DijkstraNode<T> currentDNode = (DijkstraNode<T>) openList.removeMin();
            processNode(currentDNode);
        }

        return path != null ? path:new ListPath<T>(Collections.emptyList(),0);
    }


    public void initDemo(Graph<T> graph,T start, T end) {
        demoOn = true;
        init(graph,start,end);

    }

    @Override
    public boolean isOver() {
        return !demoOn;
    }

    private void processNode(DijkstraNode<T> dNode)
    {
        T currentNode = dNode.getNode();
        if (currentNode.equals(target))
        {
            System.out.println("Dijkstra Inserted: "+openList.getInsertCount());
            path = buildPath(dNode,start,closedList);
            return;
        }

        List<Connection<T>> neighbors = graph.connectionsOf(currentNode);

        for (Connection<T> connection:neighbors)
        {
            T neighbor = connection.to();

            if (closedList.containsKey(neighbor))
                continue;

            DijkstraNode<T> newRecord = new DijkstraNode<>(neighbor,dNode.getEstimatedCost()+connection.cost(),connection);

            if ( openList.contains(neighbor))
            {
                if ( openList.get(neighbor).getEstimatedCost()> newRecord.getEstimatedCost() )
                    openList.update(newRecord);
            }
            else openList.insert(newRecord);


        }

        closedList.put(dNode.getNode(),dNode);
    }
    @Override
    public void step() {
        DijkstraNode<T> currentDNode = (DijkstraNode<T>) openList.removeMin();
        processNode(currentDNode);
        current= currentDNode.node;
        demoOn = path == null && !openList.isEmpty();
    }

    @Override
    public Set<T> openList() {
        return openList.getNodes();
    }

    @Override
    public Set<T> closedList() {
        return closedList.keySet();
    }

    @Override
    public T current() {
        return current;
    }

    @Override
    public Path getPath() {
        return path;
    }

    @Override
    public void reset() {
        openList.init();
        closedList.clear();
        start = null;
        target =null;
    }


    private void init(Graph<T> graph,T start, T end) {
        this.graph = graph;
        this.start= start;
        this.target = end;
        openList.init();
        closedList.clear();
        DijkstraNode<T> dNode = new DijkstraNode<>(start,0,null);
        path = null;
        openList.insert(dNode);
    }

    private Path<T> buildPath(DijkstraNode<T> currentDNode, T start, HashMap<T,DijkstraNode<T>> closedList) {

        List<Connection<T>> path = new ArrayList<>();
        double weight =0;

        while (!currentDNode.getNode().equals(start))
        {
            path.add(currentDNode.getConnection());
            weight += currentDNode.getConnection().cost();
            currentDNode = closedList.get(currentDNode.getConnection().from());


        }

        Collections.reverse(path);




        return new ListPath<>(path, weight);
    }



    private static GridGraph buildGrid() {
        GridGraph graph = GridGraph.gridOf(20,20,1);
        //graph.disableNode(1,2);
        //graph.disableNode(2,1);
        return graph;
    }

    private static <T> void demoDijsktra(Graph<T> graph,T start, T end) {
        System.out.println(graph);

        PathfindingAlgorithm<T> algorithm = new Dijkstra<>();

        Path<T> path = algorithm.findPath(graph,start,end);

        System.out.println(path);
    }

    private static <T> void demoDijsktraSteps(Graph<T> graph,T start, T end) {
        System.out.println(graph);

        PathfindingDemo<T> algorithm = new Dijkstra<>();
        algorithm.initDemo(graph, start, end);

        while (!algorithm.isOver())
        {
            algorithm.step();
        }

        Path<T> path = algorithm.findPath(graph,start,end);

        System.out.println(path);
    }

    private static MappedGraph<Integer> buildMappedGraph() {
        MappedGraph<Integer> graph = new MappedGraph<>(1,2,3,4,5,6,7);

        graph.addConnection(1,2,5);
        graph.addConnection(1,3,4);
        graph.addConnection(2,3,1);
        graph.addConnection(2,7,8);
        graph.addConnection(2,5,9);
        graph.addConnection(3,4,9);
        graph.addConnection(3,1,6);
        graph.addConnection(3,6,5);
        graph.addConnection(4,5,1);
        graph.addConnection(4,7,3);
        graph.addConnection(5,4,1);
        graph.addConnection(5,7,7);
        return graph;
    }


    public static void main(String[] args) {
        //demoDijsktra( buildMappedGraph());
        GridGraph graph = buildGrid();
        demoDijsktra(buildGrid(),graph.getNode(1,1), graph.getNode(18,15));
    }
}
