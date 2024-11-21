package chapter2.taggame.hw.gurur;

import chapter2.*;
import chapter2.steering.Seek;
import chapter2.taggame.TagArena;
import chapter2.taggame.TagPlayer;
import math.geom2d.Vector2D;
import utils.RandomUtils;

import java.util.ArrayList;

public class Hirsiz implements SteeringBehavior {

    private final double cornerStayDistance =200;

    TagPlayer player;
    TagArena arena;
    int[][] corners;


    public Hirsiz(TagPlayer player, TagArena arena) {
        this.player=player;
        this.arena=arena;
        corners= new int[][]{
                {0, (int) arena.getHeight()},       // top left corner
                {(int) arena.getWidth(), (int) arena.getHeight()},     // top right corner
                {0, 0},     // bottom left corner
                {(int) arena.getWidth(), 0}    // bottom right corner
        };
        getSteering(player.getStaticInfo(), player.getVelocity());
    }

    public TagPlayer getPolis(){
        for(TagPlayer otherPlayer : arena.getPlayers()) {
            if(otherPlayer.isTag()) {
                return otherPlayer;
            }
        }
        return null;
    }

    @Override
    public Acceleration getSteering(StaticInfo staticInfo, Velocity velocity) {

        Vector2D target = null;
        int corner;
        corner = getCornerOfPolis();
        int crossCorner;
        switch (corner) {
            case 0:
                crossCorner = 3;
                break;
            case 1:
                crossCorner = 2;
                break;
            case 2:
                crossCorner = 1;
                break;
            default:
                crossCorner = 0;
                break;
        }
            if (isPolisAndHirsizInSameCorner()) {
                    target = getEscapeCorner(crossCorner);
            }
            else {
                target = getTargetCorner(crossCorner);
            }


        Vector2D acceleration =  target.minus(staticInfo.getPos());
        return new Acceleration(acceleration, 0, AccelerationType.Dynamic);
    }

    private Vector2D getEscapeCorner(int corner) {
        TagPlayer polis = getPolis();
        if (polis == null) {
            return getTargetCorner(corner);
        }

        Vector2D polisPosition = polis.getStaticInfo().getPos();

        int[] oppositeCornerCoords = corners[(corner + 2) % 4];
        Vector2D oppositeCornerPosition = new Vector2D(oppositeCornerCoords[0], oppositeCornerCoords[1]);
        double distanceToOppositeCorner = polisPosition.minus(oppositeCornerPosition).norm();

        int[] adjacentCorner1Coords = corners[(corner + 1) % 4];
        Vector2D adjacentCorner1Position = new Vector2D(adjacentCorner1Coords[0], adjacentCorner1Coords[1]);
        double distanceToAdjacentCorner1 = polisPosition.minus(adjacentCorner1Position).norm();

        int[] adjacentCorner2Coords = corners[(corner + 3) % 4];
        Vector2D adjacentCorner2Position = new Vector2D(adjacentCorner2Coords[0], adjacentCorner2Coords[1]);
        double distanceToAdjacentCorner2 = polisPosition.minus(adjacentCorner2Position).norm();

        double maxDistance = Math.max(Math.max(distanceToOppositeCorner, distanceToAdjacentCorner1), distanceToAdjacentCorner2);
        int bestEscapeCorner;

        if (maxDistance == distanceToOppositeCorner) {
            bestEscapeCorner = (corner + 2) % 4;
        } else if (maxDistance == distanceToAdjacentCorner1) {
            bestEscapeCorner = (corner + 1) % 4;
        } else {
            bestEscapeCorner = (corner + 3) % 4;
        }

        return getTargetCorner(bestEscapeCorner);
    }

    private boolean isPolisAndHirsizInSameCorner() {
        return getCornerOfPolis()==getCornerOfPlayer();
    }

    private Vector2D getTargetCorner(int crossCorner) {
        int[] corner = corners[crossCorner];
        Vector2D cornerPosition = new Vector2D(corner[0], corner[1]);

        Vector2D direction = cornerPosition.minus(player.getStaticInfo().getPos());
        if (direction.norm() == 0) {
            return cornerPosition;
        }
        direction = direction.normalize().times(cornerStayDistance);

        Vector2D targetPosition = cornerPosition.plus(direction);

        return targetPosition;
    }

    private int getCornerOfPolis() {
        TagPlayer polis = getPolis();
        if(polis!= null) {

            double polisX = polis.getStaticInfo().getPos().x();
            double polisY = polis.getStaticInfo().getPos().y();
            double arenaWidth = arena.getWidth();
            double arenaHeight = arena.getHeight();

            if (polisX <= arenaWidth / 2 && polisY <= arenaHeight / 2) {
                return 2;
            } else if (polisX > arenaWidth / 2 && polisY <= arenaHeight / 2) {
                return 3;
            } else if (polisX <= arenaWidth / 2 && polisY > arenaHeight / 2) {
                return 0;
            } else {
                return 1;
            }
        }
        return 0;
    }

    private int getCornerOfPlayer() {
        double playerX = player.getStaticInfo().getPos().x();
        double playerY = player.getStaticInfo().getPos().y();
        double arenaWidth = arena.getWidth();
        double arenaHeight = arena.getHeight();

        if (playerX <= arenaWidth / 2 && playerY <= arenaHeight / 2) {
            return 2;
        } else if (playerX > arenaWidth / 2 && playerY <= arenaHeight / 2) {
            return 3;
        } else if (playerX <= arenaWidth / 2 && playerY > arenaHeight / 2) {
            return 0;
        } else {
            return 1;
        }
    }

}
