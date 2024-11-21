package chapter2.taggame.hw.muhammed;

import chapter2.Acceleration;
import chapter2.AccelerationType;
import chapter2.SteeringBehavior;
import chapter2.taggame.TagArena;
import chapter2.taggame.TagPlayer;
import chapter2.taggame.TagSteeringEngine;
import math.geom2d.Point2D;
import math.geom2d.Vector2D;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;
import java.util.stream.Collectors;

// Since I cannot access DemoWidth and DemoHeight from TagGame, please put the same values here otherwise my steering algorithm won't work.

public class TSE_HW_Muhammed implements TagSteeringEngine {
    final int DemoWidth = 800;
    final int DemoHeight = 600;
    final double maxAcceleration = 2;

    // parameters to my algorithm
    double safeDistanceThreshold = 0.2;
    double chasingCornerDistanceThreshold = 0.2;
    double cornerWeightMultiplier = 0.1;
    double cornerAvoidanceDotProduct = 0.7;
    double chasingPrioritizationDotProduct = 0.9;

    public TSE_HW_Muhammed() {
        final double bottomLeftTopRightDistance = Math.hypot(DemoWidth, DemoHeight);
        safeDistanceThreshold *= bottomLeftTopRightDistance;
        chasingCornerDistanceThreshold *= bottomLeftTopRightDistance;
        cornerWeightMultiplier *= bottomLeftTopRightDistance;
    }

    ArrayList<Point2D> corners = new ArrayList<Point2D>() {{
        add(new Point2D(0, 0));
        add(new Point2D(0, DemoHeight));
        add(new Point2D(DemoWidth, 0));
        add(new Point2D(DemoWidth, DemoHeight));
    }};

    public static Point2D convertVectorToPoint2D(Vector2D v) {
        return new Point2D(v.x(), v.y());
    }

    @Override
    public SteeringBehavior getSteeringBehavior(TagPlayer me, TagArena arena) {
        List<TagPlayer> otherPlayers = arena.getPlayers().stream()
                .filter(p -> p != me)
                .collect(Collectors.toList());

        return (staticInfo, velocity) -> {
            Vector2D linearAcceleration = new Vector2D(0, 0);
            boolean isTagged = me.isTag();
            Point2D myPosition = convertVectorToPoint2D(me.getStaticInfo().getPos());

            if (isTagged) {
                TagPlayer targetOpponent = null;
                double closestVisibleDistance = Double.MAX_VALUE;
                double closestDistance = Double.MAX_VALUE;

                Vector2D directionToFace = velocity.getLinear().normalize();

                for (TagPlayer opponent : otherPlayers) {
                    Point2D opponentPosition = convertVectorToPoint2D(opponent.getStaticInfo().getPos());
                    double distance = myPosition.distance(opponentPosition);

                    Vector2D toOpponent = new Vector2D(myPosition, opponentPosition).normalize();
                    double dotProduct = Vector2D.dot(directionToFace, toOpponent);
                    boolean isInView = dotProduct > chasingPrioritizationDotProduct;

                    double cornerWeight = Math.max(0, 1.0 - (opponentPosition.distance(getNearestCorner(opponent)) / chasingCornerDistanceThreshold));

                    if (isInView && distance < closestVisibleDistance) {
                        closestVisibleDistance = distance;
                        targetOpponent = opponent;
                    } else if (distance - cornerWeight * cornerWeightMultiplier < closestDistance) {
                        closestDistance = distance;
                        targetOpponent = opponent;
                    }
                }

                if (targetOpponent != null) {
                    linearAcceleration = targetOpponent.getStaticInfo().getPos().minus(staticInfo.getPos()).normalize().times(maxAcceleration);
                }
            } else {
                TagPlayer taggedOpponent = otherPlayers.stream().filter(TagPlayer::isTag).findFirst().orElse(null);

                if (taggedOpponent != null) {
                    Point2D opponentPosition = convertVectorToPoint2D(taggedOpponent.getStaticInfo().getPos());
                    List<SimpleEntry<Point2D, Double>> orderedCorners = orderCornersByDistance(taggedOpponent);

                    orderedCorners = orderedCorners.stream().filter(cornerEntry -> {
                        Point2D corner = cornerEntry.getKey();
                        double cornerDot = Vector2D.dot(taggedOpponent.getStaticInfo().getPos().normalize(), new Vector2D(opponentPosition, corner).normalize());
                        return cornerDot > -cornerAvoidanceDotProduct;
                    }).collect(Collectors.toList());

                    if (!orderedCorners.isEmpty()) {
                        double distanceToOpponent = myPosition.distance(opponentPosition);

                        double fleeWeight = Math.max(0, (safeDistanceThreshold - distanceToOpponent) / safeDistanceThreshold);
                        double seekWeight = 1.0 - fleeWeight;
                        Point2D furthestCornerFromOpponent = orderedCorners.get(orderedCorners.size() - 1).getKey();

                        Vector2D desiredDirection = new Vector2D(myPosition, furthestCornerFromOpponent).normalize();
                        Vector2D fromMeToOpponent =  new Vector2D(myPosition, opponentPosition).normalize();
                        linearAcceleration = desiredDirection.times(seekWeight).plus(fromMeToOpponent.times(-fleeWeight));

                        linearAcceleration = linearAcceleration.normalize().times(maxAcceleration);
                    }
                }
            }
            return new Acceleration(linearAcceleration, 0.0, AccelerationType.Dynamic);
        };
    }


    private Point2D getNearestCorner(TagPlayer player) {
        return orderCornersByDistance(player).get(0).getKey();
    }

    private List<SimpleEntry<Point2D, Double>> orderCornersByDistance(TagPlayer player) {
        List<SimpleEntry<Point2D, Double>> distanceEntries = new ArrayList<>();

        for (Point2D corner : corners) {
            double distance = convertVectorToPoint2D(player.getStaticInfo().getPos()).distance(corner);
            distanceEntries.add(new SimpleEntry<>(corner, distance));
        }

        distanceEntries.sort(Comparator.comparingDouble(SimpleEntry::getValue));

        return distanceEntries;
    }

}
