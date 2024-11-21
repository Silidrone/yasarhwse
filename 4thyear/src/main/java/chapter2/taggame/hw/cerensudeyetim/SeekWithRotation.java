package chapter2.taggame.hw.cerensudeyetim;

import chapter2.taggame.TagArena;
import chapter2.taggame.TagPlayer;
import math.geom2d.Vector2D;
import chapter2.SteeringBehavior;
import chapter2.Acceleration;
import chapter2.AccelerationType;
import chapter2.StaticInfo;
import chapter2.Velocity;

public class SeekWithRotation implements SteeringBehavior {

    private final double maxAcceleration;
    private final TagPlayer player;
    private final TagArena arena;

    public SeekWithRotation(double maxAcceleration, TagPlayer player, TagArena arena) {
        this.maxAcceleration = maxAcceleration;
        this.player = player;
        this.arena = arena;
    }

    @Override
    public Acceleration getSteering(StaticInfo staticInfo, Velocity velocity) {
        TagPlayer targetPlayer = findClosestNonTagPlayer(player, arena);
        if (targetPlayer == null) {
            return Acceleration.NoAcceleration;
        }

        Vector2D target = targetPlayer.getStaticInfo().getPos();
        Vector2D direction = target.minus(staticInfo.getPos()).normalize();
        double desiredOrientation = Math.atan2(direction.y(), direction.x());

        staticInfo.setOrientation(desiredOrientation);
        Vector2D acceleration = direction.times(maxAcceleration);

        return new Acceleration(acceleration, 0, AccelerationType.Dynamic);
    }

    private TagPlayer findClosestNonTagPlayer(TagPlayer player, TagArena arena) {
        TagPlayer closestPlayer = null;
        double closestDistance = Double.MAX_VALUE;
        Vector2D playerPosition = player.getStaticInfo().getPos();

        for (TagPlayer other : arena.getPlayers()) {
            if (!other.isTag()) {
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
}
