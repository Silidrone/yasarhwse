package chapter3.pathfinding.visibility;

import chapter3.pathfinding.Connection;
import math.geom2d.Point2D;

public class LineConnection implements Connection<Point2D> {

    Point2D p1;
    Point2D p2;
    double cost;

    public LineConnection(Point2D v1, Point2D v2) {
        p1 = v1;
        p2= v2;
        cost = p1.distance(p2);
    }

    @Override
    public Point2D from() {
        return p1;
    }

    @Override
    public Point2D to() {
        return p2;
    }

    @Override
    public double cost() {
        return cost;
    }

    @Override
    public String toString() {
        return "<"+ p1 + "--"+ p2 + ">";
    }
}
