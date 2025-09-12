package chapter3.pathfinding.alg.astar;

import chapter3.pathfinding.*;
import chapter3.pathfinding.alg.PathfindingAlgorithm;
import chapter3.pathfinding.alg.PathfindingDemo;
import chapter3.pathfinding.alg.PathfindingList;
import chapter3.pathfinding.alg.PathfindingNode;
import chapter3.pathfinding.grid.GridGraph;
import chapter3.pathfinding.grid.GridNode;

import java.util.*;

public class AStar<T> implements PathfindingDemo<T> {

    PathfindingList<T> openList;
    HashMap<T, AStarNode<T>> closedList;
    AStarHeuristic<T> heuristic;
    private T start;
    private T end;
    private T current;
    private Graph<T> graph;
    private boolean demoOn;
    private Path<T> path;

    public AStar(AStarHeuristic<T> heuristic) {
        openList = new PathfindingList<>();
        closedList = new HashMap<>();
        this.heuristic= heuristic;
    }

    @Override
    public Path<T> findPath(Graph<T> graph, T start, T end) {

        if ( start.equals(end))
            return new ListPath<>(new ArrayList<>(),0.0);


        init(graph,start,end);


        while (!openList.isEmpty()&& path==null)
        {
            AStarNode<T> currentDNode = (AStarNode<T>) openList.removeMin();
            processNode( currentDNode);
        }

        return path==null ? new ListPath<T>(Collections.emptyList(),0):path;
    }

    private void processNode( AStarNode<T> node) {
        T currentNode = node.getNode();
        if (currentNode.equals(end))
        {
            System.out.println("AStar Inserted: "+openList.getInsertCount());
            path = buildPath(node,start,closedList);
            return ;
        }

        List<Connection<T>> neighbors = graph.connectionsOf(currentNode);

        for (Connection<T> connection:neighbors)
        {
            T neighbor = connection.to();
            double neighborCost = node.getCostSoFar()+connection.cost();
            double endNodeHeuristic;
            if (closedList.containsKey(neighbor)) {

                AStarNode<T> neighborNode = closedList.get(neighbor);

                if (neighborCost>= neighborNode.getCostSoFar())
                    continue;

                closedList.remove(neighbor);

                endNodeHeuristic = neighborNode.getEstimatedCost()-neighborNode.getCostSoFar();
            }
            else if (openList.contains(neighbor))
            {
                AStarNode<T> neighborNode = (AStarNode<T>) openList.get(neighbor);
                if (neighborNode.getCostSoFar()<=neighborCost)
                    continue;

                endNodeHeuristic =  neighborNode.getEstimatedCost()- neighborNode.getCostSoFar();
            }
            else
            {
                endNodeHeuristic = heuristic.estimate(graph,neighbor, end);
            }


            AStarNode<T> endNodeRecord= new AStarNode<T>(neighbor,neighborCost,neighborCost+endNodeHeuristic,connection);

            if (!openList.contains(neighbor))
                openList.insert(endNodeRecord);
            else openList.update(endNodeRecord);


        }

        closedList.put(node.getNode(), node);
    }

    @Override
    public void initDemo(Graph<T> graph, T start, T end) {
        demoOn = true;
        init(graph,start,end);

    }

    @Override
    public boolean isOver() {
        return !demoOn;
    }

    @Override
    public void step() {
        AStarNode<T> currentDNode = (AStarNode<T>)  openList.removeMin();
        processNode(currentDNode);
        current = currentDNode.node;
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
    public Path<T> getPath() {
        return path;
    }

    @Override
    public void reset() {
        openList.init();
        closedList.clear();
        start = null;
        end =null;
    }


    private void init(Graph<T> graph,T start, T end) {

        this.start = start;
        this.end = end;
        this.graph= graph;
        path= null;
        openList.init();
        closedList.clear();
        PathfindingNode<T> dNode = new AStarNode<>(start,0,heuristic.estimate(graph,start,end),null);

        openList.insert(dNode);
    }

    private Path<T> buildPath(AStarNode<T> currentDNode, T start, HashMap<T,AStarNode<T>> closedList) {

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

    public static void main(String[] args) {
        //demoDijsktra( buildMappedGraph());
        GridGraph graph = buildGrid();
        AStarHeuristic<GridNode> heuristic = new ManhattanDistanceHeuristic(1);
        demoAStar(buildGrid(),graph.getNode(1,1), graph.getNode(18,18), heuristic);
    }

    private static GridGraph buildGrid() {
        GridGraph graph = GridGraph.gridOf(20,20,1);
        graph.disableNode(1,2);
        graph.disableNode(2,1);
        return graph;
    }

    private static <T> void demoAStar(Graph<T> graph, T start, T end, AStarHeuristic<T> heuristic) {
        System.out.println(graph);

        PathfindingAlgorithm<T> algorithm = new AStar<>(heuristic);

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

}
