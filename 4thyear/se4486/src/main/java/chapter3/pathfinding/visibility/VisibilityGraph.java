package chapter3.pathfinding.visibility;

import chapter3.pathfinding.Connection;
import math.geom2d.Point2D;
import math.geom2d.polygon.Polygon2D;
import utils.GeomUtils;


import java.util.*;
import java.util.List;

public class VisibilityGraph implements Graph2D {

    private List<Point2D> points;
    private List<Polygon2D> polygons;

    private HashMap<Point2D,List<Connection<Point2D>>> fromMap;


    VisibilityGraph(List<Point2D> points, List<Polygon2D> polygons)
    {
        this.points = points;
        this.polygons = polygons;
        fromMap = new HashMap<>();
        buildNodes();
        buildConnections();
    }

    void buildNodes()
    {
        fromMap.clear();

        points.forEach((v)->fromMap.put(v,new ArrayList<>()));

        for (Polygon2D p: polygons)
        {
            p.vertices().forEach((v)-> fromMap.put(v,new ArrayList<>()));
        }
    }

    void buildConnections()
    {
        buildConnectionsBetween(points);
        for (Polygon2D p1:polygons)
        {
            buildConnectionsOf(p1);

            for (Point2D v:points)
                buildConnectionsBetween(v,p1);
            for (Polygon2D p2: polygons)
            {
                if (p1 != p2)
                    buildConnectionsBetween(p1,p2);
            }
        }
    }



    private void buildConnectionsOf(Polygon2D p) {
        for (int i1 = 0; i1<p.vertexNumber()-1;i1++)
        {
            for (int i2 = i1+1; i2 < p.vertexNumber() ; i2++) {
                if (i2-i1==1)
                {
                    buildConnection(p.vertex(i1),p.vertex(i2));
                }
            }
        }
        buildConnection(p.vertex(0),p.vertex(p.vertexNumber()-1));
    }

    private void buildConnectionsBetween(List<Point2D> points) {
        for (int i1 = 0; i1 < points.size() - 1; i1++) {
            for (int i2 = i1+1; i2 < points.size(); i2++) {
                if (GeomUtils.isVisible(points.get(i1), points.get(i2),polygons))
                    buildConnection(points.get(i1),points.get(i2));
            }
        }

    }

    private void buildConnectionsBetween(Polygon2D p1, Polygon2D p2) {

        for(Point2D v1 : p1.vertices() )
        {
            buildConnectionsBetween(v1,p2);
        }
    }

    private void buildConnectionsBetween(Point2D v, Polygon2D p)
    {
        for (Point2D v2: p.vertices())
        {
            if (GeomUtils.isVisible(v,v2,polygons))
            {
                buildConnection(v,v2);
            }
        }
    }

    private void buildConnection(Point2D v1, Point2D v2) {
        fromMap.get(v1).add(new LineConnection(v1,v2));
        fromMap.get(v2).add(new LineConnection(v2,v1));
    }


    @Override
    public List<Connection<Point2D>> connectionsOf(Point2D from) {
        return fromMap.get(from);
    }

    public String toString() {
        StringBuilder sb =new StringBuilder();
        points.forEach(sb::append);
        sb.append("\n");
        polygons.forEach(sb::append);

        sb.append("\n--------------------------\n");
        fromMap.keySet().forEach((v)->{
            sb.append(v+" : ");
            fromMap.get(v).forEach(sb::append);
            sb.append("\n");
        });

        return sb.toString();
    }


    public List<Point2D> getPoints() {
        return points;
    }

    public List<Polygon2D> getPolygons() {
        return polygons;
    }

    public List<Point2D> getNodes() {
        return new ArrayList<>(fromMap.keySet());
    }

    @Override
    public List<LineConnection> getConnections() {
        List<LineConnection> connections = new ArrayList<>();
        for(Point2D p: fromMap.keySet())
        {
            fromMap.get(p).forEach((c)->{connections.add((LineConnection) c);});
        }
        return connections;
    }

    public static void main(String[] args) {

    }



}
