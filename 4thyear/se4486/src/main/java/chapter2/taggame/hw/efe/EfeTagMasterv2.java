package chapter2.taggame.hw.efe;

import chapter2.*;
import chapter2.steering.Seek;
import chapter2.taggame.TagPlayer;
import math.geom2d.Vector2D;
import java.util.List;

public class EfeTagMasterv2 implements SteeringBehavior {
    private List<TagPlayer> players;
    private TagPlayer self;
    private Evade evadeBehavior;
    private Seek seekBehavior;
    private double thresholdDistance; // Mesafe kapanma eşiği

    public EfeTagMasterv2(TagPlayer self, List<TagPlayer> players, double maxSpeed, double width, double height, double thresholdDistance) {
        this.self = self;
        this.players = players;
        this.evadeBehavior = new Evade(maxSpeed, width, height);
        this.seekBehavior = new Seek(new Vector2D(), maxSpeed);
        this.thresholdDistance = thresholdDistance;
    }

    @Override
    public Acceleration getSteering(StaticInfo staticInfo, Velocity velocity) {
        if (self.isTag()) {
            TagPlayer fastestPlayer = findFastestPlayer(staticInfo);
            if (fastestPlayer != null) {
                seekBehavior.setTarget(fastestPlayer.getStaticInfo().getPos());
                return seekBehavior.getSteering(staticInfo, velocity);
            }
        } else {
            TagPlayer closestTagger = findClosestTagger(staticInfo);
            if (closestTagger != null) {
                double distance = staticInfo.getPos().minus(closestTagger.getStaticInfo().getPos()).norm();
                Vector2D taggerVelocity = closestTagger.getVelocity().getLinear();

                if (distance < thresholdDistance && taggerVelocity.minus(staticInfo.getPos()).norm() > 0) {
                    // Ebe yaklaşıyorsa kaç
                    evadeBehavior.setTarget(closestTagger.getStaticInfo().getPos());
                    return evadeBehavior.getSteering(staticInfo, velocity);
                } else {
                    // Mesafe kapanıyorsa en hızlı oyuncuya yönel
                    TagPlayer fastestPlayer = findFastestPlayer(staticInfo);
                    if (fastestPlayer != null) {
                        seekBehavior.setTarget(fastestPlayer.getStaticInfo().getPos());
                        return seekBehavior.getSteering(staticInfo, velocity);
                    }
                }
            }
        }
        return new Acceleration(new Vector2D(0, 0), 0, AccelerationType.Dynamic);
    }

    private TagPlayer findFastestPlayer(StaticInfo staticInfo) {
        TagPlayer fastest = null;
        double minTime = Double.MAX_VALUE;

        for (TagPlayer player : players) {
            if (player == self) continue;

            double distance = staticInfo.getPos().minus(player.getStaticInfo().getPos()).norm();
            Vector2D speedVector = self.getVelocity().getLinear().minus(player.getVelocity().getLinear());
            double speed = speedVector.norm();
            if (speed > 0) {
                double timeToReach = distance / speed;
                if (timeToReach < minTime) {
                    minTime = timeToReach;
                    fastest = player;
                }
            }
        }
        return fastest;
    }

    private TagPlayer findClosestTagger(StaticInfo staticInfo) {
        TagPlayer closest = null;
        double minDistance = Double.MAX_VALUE;

        for (TagPlayer player : players) {
            if (!player.isTag()) continue;
            double distance = staticInfo.getPos().minus(player.getStaticInfo().getPos()).norm();
            if (distance < minDistance) {
                minDistance = distance;
                closest = player;
            }
        }
        return closest;
    }
}
