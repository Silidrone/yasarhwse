package chapter3.pathfinding.visibility;

import math.geom2d.Point2D;
import math.geom2d.line.Line2D;
import math.geom2d.line.LineSegment2D;
import math.geom2d.polygon.Polygon2D;
import math.geom2d.polygon.SimplePolygon2D;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Polygon;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GeomUtils {

    public static boolean isVisible(Point2D v1, Point2D v2, List<Polygon2D> polygons) {

        Line2D line = new Line2D(v1,v2);

        for (Polygon2D p: polygons)
        {
            if (GeomUtils.isObscuredBy(line,p))
                return false;
        }
        return true;
    }


    public static boolean isObscuredBy(Line2D line, Polygon2D p) {

        if ( ( GeomUtils.contains(p,line.p1) && !p.vertex(p.closestVertexIndex(line.p1)).almostEquals(line.p1,0.01))||
                ( GeomUtils.contains(p,line.p2) && !p.vertex(p.closestVertexIndex(line.p2)).almostEquals(line.p2,0.01)))
        {
            return true;
        }

        for (LineSegment2D edge:p.edges())
        {
            if (edge.isParallel(line))
                continue;

            Point2D ip = line.intersection(edge);
            if (line.intersection(edge)!=null)
            {
                List<Point2D> pointsAround = getPointsAround(line,ip);
                if (contains(p,pointsAround.get(0)))
                    return true;
                if (pointsAround.size()>1 && contains(p,pointsAround.get(1)))
                    return true;
            }
        }
        return false;
    }

    private static boolean contains(Polygon2D p, Point2D p1) {
        float[] points = new float[p.vertexNumber()*2];

        for (int i = 0; i < p.vertexNumber(); i++) {
            points[2*i]= (float) p.vertex(i).x();
            points[2*i+1]= (float) p.vertex(i).y();
        }

        Polygon polygon = new Polygon(points);

        return polygon.contains((float) p1.x(), (float) p1.y());
    }

    public static List<Point2D> getPointsAround(Line2D line, Point2D ip) {
        List<Point2D> point2DS = new ArrayList<>();
        Point2D p1= ip.plus(line.direction().normalize().times(0.01));
        Point2D p2= ip.minus(line.direction().normalize().times(0.01));
        if (line.contains(p1))
            point2DS.add(p1);
        if (line.contains(p2))
            point2DS.add(p2);
        return point2DS;
    }

    public static List<Point> getVertices(Polygon p) {
        float[] points= p.getPoints();
        List<Point> pointList = new ArrayList<>();
        for (int i = 0; i < points.length / 2; i++) {
            pointList.add(new Point(points[2*i],points[2*i+1]));
        }
        return pointList;
    }




}
