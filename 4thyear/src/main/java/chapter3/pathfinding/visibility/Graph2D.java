package chapter3.pathfinding.visibility;

import chapter3.pathfinding.Connection;
import chapter3.pathfinding.Graph;
import math.geom2d.Point2D;
import math.geom2d.polygon.Polygon2D;

import java.util.List;

public interface Graph2D extends Graph<Point2D> {
    List<? extends Connection<Point2D>> getConnections();
    List<Point2D> getNodes();
    List<Polygon2D> getPolygons();

}
