package chapter2.taggame.hw.sinan;

import chapter2.Acceleration;
import chapter2.AccelerationType;
import chapter2.StaticInfo;
import chapter2.Velocity;
import chapter2.taggame.TagArena;
import chapter2.taggame.TagPlayer;
import math.geom2d.Vector2D;
import java.util.List;

public class RunAway extends TagGameBehavior {
    private static final double WALL_GAP = 35;
    public RunAway(TagPlayer player, TagArena arena, double maxAcceleration) {
        super(player, arena, maxAcceleration);
    }

    private Vector2D findTagPos(){
        List<TagPlayer> players = arena.getPlayers();
        TagPlayer tag = null;
        for (int i = 0; i < players.size(); i++) {
            if(players.get(i).isTag()){
                tag = players.get(i);
            }
        }
        return tag.getStaticInfo().getPos();
    }

    @Override
    public Acceleration getSteering(StaticInfo staticInfo, Velocity velocity) {
        Vector2D myPos = player.getStaticInfo().getPos();
        Vector2D tagPos = findTagPos();
        Vector2D diffVector = myPos.minus(tagPos);

        diffVector = checkWalls(myPos, tagPos, diffVector);

        diffVector = diffVector.normalize().times(maxAcceleration);
        return new Acceleration(diffVector,0, AccelerationType.Dynamic);
    }

    private Vector2D checkCorners(Vector2D myPos, Vector2D diffVector){
        if (myPos.x() < WALL_GAP && myPos.y() < WALL_GAP) { // bottom left
            if (Math.abs(diffVector.x()) > Math.abs(diffVector.y()))
                return new Vector2D(0, maxAcceleration);
            else
                return new Vector2D(maxAcceleration, 0);
        }else if (myPos.x() < WALL_GAP && myPos.y() > arena.getHeight()-WALL_GAP) { // top left
            if (Math.abs(diffVector.x()) > Math.abs(diffVector.y()))
                return new Vector2D(0, -maxAcceleration);
            else
                return new Vector2D(maxAcceleration, 0);
        }else if (myPos.x() > arena.getWidth() - WALL_GAP && myPos.y() < WALL_GAP) { // bottom right
            if (Math.abs(diffVector.x()) > Math.abs(diffVector.y()))
                return new Vector2D(0, maxAcceleration);
            else
                return new Vector2D(-maxAcceleration, 0);
        } else if (myPos.x() > arena.getWidth() - WALL_GAP && myPos.y() > arena.getHeight() - WALL_GAP) { // top right
            if (Math.abs(diffVector.x()) > Math.abs(diffVector.y()))
                return new Vector2D(0, -maxAcceleration);
            else
                return new Vector2D(-maxAcceleration, 0);
        }
        return diffVector;
    }

    private Vector2D checkWalls(Vector2D myPos, Vector2D tagPos, Vector2D diffVector) {
        Vector2D diffVectorCorner = checkCorners(myPos, diffVector);
        if (!diffVectorCorner.equals(diffVector))
            return diffVectorCorner;

        if (myPos.x() < WALL_GAP || myPos.x() > arena.getWidth() - WALL_GAP) { //right or left
            if (myPos.y() > tagPos.y())
                return new Vector2D(0, maxAcceleration);
            else
                return new Vector2D(0, -maxAcceleration);
        } else if (myPos.y() < WALL_GAP || myPos.y() > arena.getHeight()-WALL_GAP) { // bottom or top
            if (myPos.x() > tagPos.x())
                return new Vector2D(maxAcceleration, 0);
            else
                return new Vector2D(-maxAcceleration, 0);
        }
        return diffVector;
    }
}