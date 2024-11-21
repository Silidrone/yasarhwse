package chapter2.taggame.hw.cerensudeyetim;

import chapter2.*;
import chapter2.taggame.TagArena;
import chapter2.taggame.TagPlayer;
import chapter2.taggame.TagSteeringEngine;
import math.geom2d.Vector2D;

public class TSE_HW_ceren implements TagSteeringEngine {

    //a constant maximum acceleration
    private static final double maxAcceleration = 10.0;

    public SteeringBehavior getSteeringBehavior(TagPlayer player, TagArena arena) {
        if (player.isTag()) {
            // if the player is the tag using SeekWithRotation to chase the closest non-tag player
            TagPlayer targetPlayer = findClosestNonTagPlayer(player, arena);
            if (targetPlayer != null) {
                return new SeekWithRotation(maxAcceleration, player, arena);
            }
        } else {
            // if the player is not the tag using ImprovedFlee to evade the closest tag player
            TagPlayer closestTagPlayer = findClosestTagPlayer(player, arena);
            if (closestTagPlayer != null) {
                return new ImprovedFlee(closestTagPlayer.getStaticInfo().getPos(), maxAcceleration, 1.0);
            }
        }
        return null;
    }

    private TagPlayer findClosestNonTagPlayer(TagPlayer player, TagArena arena) {
        TagPlayer closestPlayer = null;
        double closestDistance = Double.MAX_VALUE;

        Vector2D playerPosition = player.getStaticInfo().getPos();

        for (TagPlayer other : arena.getPlayers()) {
            if (!other.isTag() && !other.equals(player)) {
                Vector2D otherPosition = other.getStaticInfo().getPos();
                double distance = playerPosition.minus(otherPosition).norm();
                if (distance < closestDistance) {
                    closestDistance = distance;
                    closestPlayer = other;
                }
            }
        }
        return closestPlayer;
    }

    private TagPlayer findClosestTagPlayer(TagPlayer player, TagArena arena) {
        TagPlayer closestTagPlayer = null;
        double closestDistance = Double.MAX_VALUE;

        Vector2D playerPosition = player.getStaticInfo().getPos();

        for (TagPlayer other : arena.getPlayers()) {
            if (other.isTag()) {
                Vector2D otherPosition = other.getStaticInfo().getPos();
                double distance = playerPosition.minus(otherPosition).norm();
                if (distance < closestDistance) {
                    closestDistance = distance;
                    closestTagPlayer = other;
                }
            }
        }
        return closestTagPlayer;
    }
}
