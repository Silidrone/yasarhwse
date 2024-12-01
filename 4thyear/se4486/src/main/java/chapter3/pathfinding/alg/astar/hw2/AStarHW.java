package chapter3.pathfinding.alg.astar.hw2;

import chapter3.pathfinding.*;
import chapter3.pathfinding.alg.PathfindingAlgorithm;
import chapter3.pathfinding.alg.PathfindingList;
import chapter3.pathfinding.alg.astar.AStarHeuristic;
import chapter3.pathfinding.alg.astar.AStarNode;

import java.util.*;

public class AStarHW<T> implements PathfindingAlgorithm<T> {
    PathfindingList<T> openList;
    HashMap<T, AStarNode<T>> closedList;
    private T start;
    private T target;
    private T current;
    AStarHeuristic<T> heuristic;

    private Graph<T> graph;
    private Path<T> path;

    public AStarHW(AStarHeuristic<T> heuristic) {
        openList = new PathfindingList<>();
        closedList = new HashMap<>();
        this.heuristic = heuristic;
    }

    /**
     * TODO: Implement A* algorithm.
     *
     * @param graph
     * @param start
     * @param end
     * @return
     */
    @Override
    public Path<T> findPath(Graph<T> graph, T start, T end) {
        if (start.equals(end))
            return new ListPath<>(new ArrayList<>(), 0.0);

        init(graph, start, end);

        while (!openList.isEmpty() && path == null) {
            AStarNode<T> currentAStarNode = (AStarNode<T>) openList.removeMin();
            processNode(currentAStarNode);
        }

        return path != null ? path : new ListPath<T>(Collections.emptyList(), 0);
    }


    private void processNode(AStarNode<T> aStarNode) {
        T currentNode = aStarNode.getNode();
        if (currentNode.equals(target)) {
            System.out.println("A* Inserted: " + openList.getInsertCount());
            path = buildPath(aStarNode, start, closedList);
            return;
        }

        List<Connection<T>> neighbors = graph.connectionsOf(currentNode);

        for (Connection<T> connection : neighbors) {
            T neighbor = connection.to();

            if (closedList.containsKey(neighbor))
                continue;

            var g_cost = aStarNode.getCostSoFar() + connection.cost();
            var f_cost = g_cost + heuristic.estimate(graph, neighbor, target);
            AStarNode<T> newRecord = new AStarNode<>(neighbor, g_cost, f_cost, connection);

            if (openList.contains(neighbor)) {
                if (openList.get(neighbor).getEstimatedCost() > newRecord.getEstimatedCost())
                    openList.update(newRecord);
            } else openList.insert(newRecord);
        }

        closedList.put(aStarNode.getNode(), aStarNode);
    }


    @Override
    public Path<T> getPath() {
        return path;
    }


    private void init(Graph<T> graph, T start, T target) {

        this.start = start;
        this.target = target;
        this.graph = graph;
        path = null;
        openList.init();
        closedList.clear();

        openList.insert(new AStarNode<>(start, 0, heuristic.estimate(graph, start, target), null));
    }

    private Path<T> buildPath(AStarNode<T> currentAStarNode, T start, HashMap<T, AStarNode<T>> closedList) {
        List<Connection<T>> path = new ArrayList<>();
        double weight = 0;

        while (!currentAStarNode.getNode().equals(start)) {
            path.add(currentAStarNode.getConnection());
            weight += currentAStarNode.getConnection().cost();
            currentAStarNode = closedList.get(currentAStarNode.getConnection().from());
        }

        Collections.reverse(path);
        return new ListPath<>(path, weight);
    }
}
