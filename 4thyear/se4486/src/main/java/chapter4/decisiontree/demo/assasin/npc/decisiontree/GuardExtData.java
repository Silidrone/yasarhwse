package chapter4.decisiontree.demo.assasin.npc.decisiontree;


import math.geom2d.Vector2D;
import math.geom2d.polygon.Polygon2D;


import java.util.List;

public class GuardExtData {
    final Vector2D assasinPos;
    final List<Vector2D> guardsPos;
    final List<Polygon2D> obstacles;
    private int time;



    public GuardExtData(Vector2D assasinPos, List<Vector2D> guerdsPos, List<Polygon2D> obstacles, int time) {
        this.assasinPos = assasinPos;
        this.guardsPos = guerdsPos;
        this.obstacles = obstacles;
        this.time = time;

    }

    public Vector2D getAssasinPos() {
        return assasinPos;
    }

    public List<Polygon2D> getObstacles() {
        return obstacles;
    }

    public List<Vector2D> getGuardsPos() {
        return guardsPos;
    }

    public int getTime() {
        return time;
    }
}
