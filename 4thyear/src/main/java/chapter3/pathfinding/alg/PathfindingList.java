package chapter3.pathfinding.alg;



import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Set;

public class PathfindingList<T> {

    PriorityQueue<PathfindingNode<T>> heap;
    HashMap<T, PathfindingNode<T>> nodeMap;
    private int insertCount;

    public PathfindingList() {
        heap = new PriorityQueue<>( Comparator.comparingDouble(PathfindingNode::getEstimatedCost));
        nodeMap = new HashMap<>();
    }

    public void insert(PathfindingNode<T> dNode)
    {
        nodeMap.put(dNode.getNode(),dNode);
        heap.offer(dNode);
        insertCount++;
    }

    public void init()
    {
        nodeMap.clear();
        heap.clear();
        insertCount=0;
    }

    public boolean isEmpty()
    {
        return nodeMap.isEmpty();
    }

    public PathfindingNode<T> removeMin()
    {
        PathfindingNode<T> pNode = heap.remove();
        nodeMap.remove(pNode.getNode());
        return pNode;
    }

    public PathfindingNode<T> get(T node )
    {
        return nodeMap.get(node);
    }

    public void  update(PathfindingNode<T> newRecord)
    {
        heap.remove(nodeMap.get(newRecord.getNode()));
        nodeMap.put(newRecord.getNode(),newRecord);
        heap.offer(newRecord);
    }

    public int getInsertCount() {
        return insertCount;
    }

    public boolean contains(T node) {
        return nodeMap.containsKey(node);
    }

    public Set<T> getNodes()
    {
        return nodeMap.keySet();
    }

}
