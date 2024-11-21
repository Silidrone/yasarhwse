package chapter2.taggame.hw.gurur;

import chapter2.*;
import chapter2.steering.Seek;
import chapter2.taggame.TagArena;
import chapter2.taggame.TagPlayer;
import math.geom2d.Point2D;
import math.geom2d.Vector2D;
import utils.RandomUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class Polis implements SteeringBehavior {

    private final double closeRadius =200;

    TagPlayer player;
    TagArena arena;
    int[][] corners;


    public Polis(TagPlayer player, TagArena arena) {
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

    @Override
    public Acceleration getSteering(StaticInfo staticInfo, Velocity velocity) {

        Vector2D target = null;
        if(anyoneClose()){
            target = getClosest();
        }
        else {
            int[] corner = corners[getCorner()];
            target = getClosestToCorner(corner);
        }

        Vector2D acceleration =  target.minus(staticInfo.getPos());
        return new Acceleration(acceleration, 0, AccelerationType.Dynamic);
    }

    private Vector2D getClosestToCorner(int[] corner) {
        Vector2D cornerPosition = new Vector2D(corner[0], corner[1]);
        TagPlayer closestPlayer = null;
        double minDistance = Double.MAX_VALUE;

        for (TagPlayer otherPlayer : arena.getPlayers()) {
            if (otherPlayer != player) {
                Vector2D playerPosition = otherPlayer.getStaticInfo().getPos();
                double distance = playerPosition.minus(cornerPosition).norm();

                if (distance < minDistance) {
                    minDistance = distance;
                    closestPlayer = otherPlayer;
                }
            }
        }

        return closestPlayer.getStaticInfo().getPos();
    }

    private int getCorner() {
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

    private Vector2D getClosest() {
        Vector2D playerPosition = player.getStaticInfo().getPos();
        double minDistance = Double.MAX_VALUE;
        TagPlayer closestPlayer = null;

        for (TagPlayer otherPlayer : arena.getPlayers()) {
            if (otherPlayer != player) {
                Vector2D distance = playerPosition.minus(otherPlayer.getStaticInfo().getPos());
                double distanceNorm = distance.norm();

                if (distanceNorm < minDistance && distanceNorm < closeRadius) {
                    minDistance = distanceNorm;
                    closestPlayer = otherPlayer;
                }
            }
        }


        return closestPlayer.getStaticInfo().getPos();
    }

    private boolean anyoneClose() {
        ArrayList<TagPlayer> peopleAround = new ArrayList<>();

        Vector2D playerPosition = player.getStaticInfo().getPos();

        for(TagPlayer otherPlayer : arena.getPlayers()) {
            if (otherPlayer != player) {
                Vector2D distance = playerPosition.minus(otherPlayer.getStaticInfo().getPos());
                double distanceNorm = distance.norm();
                if (distanceNorm < closeRadius) {
                    peopleAround.add(otherPlayer);
                }
            }
        }

        return !peopleAround.isEmpty();
    }

}
