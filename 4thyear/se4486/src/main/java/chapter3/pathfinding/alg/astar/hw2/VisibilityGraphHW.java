package chapter3.pathfinding.alg.astar.hw2;

import chapter3.pathfinding.Connection;
import chapter3.pathfinding.visibility.Graph2D;
import math.geom2d.Point2D;
import math.geom2d.polygon.Polygon2D;

import java.util.Collections;
import java.util.List;

public class VisibilityGraphHW implements Graph2D {

    List<Point2D> points;
    List<Polygon2D> polygons;
    // TODO: You can define necessary additional members here



    public VisibilityGraphHW(List<Point2D> points, List<Polygon2D> polygons) {
        this.points = points;
        this.polygons = polygons;
        buildGraph();
    }

    /**
     * TODO: you can do whatever you want to do initially here
     */
    private void buildGraph() {
    }

    @Override
    public List<? extends Connection<Point2D>> getConnections() {
        return Collections.emptyList();
    }

    /**
     * TODO: Implement properly
     * @return
     */
    @Override
    public List<Point2D> getNodes() {
        return Collections.emptyList();
    }

    /**
     * TODO:Implement properly
     * @return
     */
    @Override
    public List<Polygon2D> getPolygons() {
        return Collections.emptyList();
    }

    /**
     * TODO:Implement properly
     * @param from
     * @return
     */
    @Override
    public List<Connection<Point2D>> connectionsOf(Point2D from) {
        return Collections.emptyList();
    }
}
