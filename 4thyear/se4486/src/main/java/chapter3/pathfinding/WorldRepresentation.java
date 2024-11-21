package chapter3.pathfinding;



import chapter3.pathfinding.alg.PathfindingAlgorithm;

import java.awt.geom.Point2D;

public interface WorldRepresentation<Node>
{
    Node quantize(Point2D p);
    Point2D localize(Node node);

    void addPath(Path<Node> path);

    Node getStart();
    Node getEnd();

    void removePaths();

    void keyPressed(int key, char c);

    void mouseClicked(int button, int x, int y, int clickCount);

    Graph<Node> getGraph();



    void update(PathfindingAlgorithm<Node> algorithm);

    void reset();

    boolean isVisible(Node head, Node end);
}
