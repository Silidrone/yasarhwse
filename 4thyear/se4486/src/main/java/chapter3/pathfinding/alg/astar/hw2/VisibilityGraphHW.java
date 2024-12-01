package chapter3.pathfinding.alg.astar.hw2;

import chapter3.pathfinding.Connection;
import chapter3.pathfinding.SimpleConnection;
import chapter3.pathfinding.visibility.GeomUtils;
import chapter3.pathfinding.visibility.Graph2D;
import math.geom2d.Point2D;
import math.geom2d.polygon.Polygon2D;

import java.util.*;
import java.util.stream.Collectors;

public class VisibilityGraphHW implements Graph2D {

    List<Point2D> points;
    List<Polygon2D> polygons;
    List<Connection<Point2D>> connections = new ArrayList<>();

    public VisibilityGraphHW(List<Point2D> points, List<Polygon2D> polygons) {
        this.polygons = polygons;

        Set<Point2D> allPoints = new HashSet<>(points);
        for (Polygon2D polygon : polygons) {
            allPoints.addAll(polygon.vertices());
        }
        this.points = new ArrayList<>(allPoints);

        buildGraph();
    }

    private void buildGraph() {
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                Point2D a = points.get(i);
                Point2D b = points.get(j);
                if (GeomUtils.isVisible(a, b, polygons)) {
                    connections.add(new SimpleConnection<>(a, b, a.distance(b)));
                    connections.add(new SimpleConnection<>(b, a, b.distance(a)));
                }
            }
        }
    }

    @Override
    public List<? extends Connection<Point2D>> getConnections() {
        return connections;
    }

    @Override
    public List<Point2D> getNodes() {
        return points;
    }

    @Override
    public List<Polygon2D> getPolygons() {
        return polygons;
    }

    @Override
    public List<Connection<Point2D>> connectionsOf(Point2D from) {
        return connections.stream()
                .filter(connection -> connection.from().equals(from))
                .collect(Collectors.toList());
    }
}
