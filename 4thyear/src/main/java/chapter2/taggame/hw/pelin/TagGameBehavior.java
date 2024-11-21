package chapter2.taggame.hw.pelin;

import chapter2.*;
import chapter2.steering.Seek;
import chapter2.taggame.TagPlayer;
import math.geom2d.Vector2D;

import java.util.List;
import java.util.Random;

public class TagGameBehavior implements SteeringBehavior {

    private TagPlayer player;
    private List<TagPlayer> players;
    private Seek seekBehavior;
    private double maxSpeed;
    private int width, height;
    private double alertDistance = 300;        // Distance at which non-taggers evade the tagger
    private double cornerThreshold = 100;      // Minimum distance to target before switching
    private double bufferDistance = 30;        // Distance to avoid edges and corners
    private double curveDistance = 10;         // Distance to create the parabolic path
    private Random random = new Random();
    private Vector2D targetPoint;              // Current target point for the non tagger
    private boolean avoidingCorner = false;    // Flag to check if non tagger is avoiding a corner

    public TagGameBehavior(TagPlayer player, List<TagPlayer> players, double maxSpeed, int width, int height) {
        this.player = player;
        this.players = players;
        this.seekBehavior = new Seek(new Vector2D(0, 0), maxSpeed);
        this.maxSpeed = maxSpeed;
        this.width = width;
        this.height = height;

        // Initialize a random safe target point for non taggers at the start of the game
        if (!player.isTag()) {
            this.targetPoint = chooseRandomSafePoint(null);
        }
    }

    @Override
    public Acceleration getSteering(StaticInfo staticInfo, Velocity velocity) {
        if (player.isTag()) {
            return taggerChasingBehavior(staticInfo, velocity);
        } else {
            return nonTaggerBehavior(staticInfo, velocity);
        }
    }

    private Acceleration taggerChasingBehavior(StaticInfo staticInfo, Velocity velocity) {
        TagPlayer target = findClosestPlayer();

        if (target == null) {
            return Acceleration.NoAcceleration;
        }

        Vector2D targetPos = target.getStaticInfo().getPos();
        seekBehavior.setTarget(targetPos);
        return seekBehavior.getSteering(staticInfo, velocity);
    }

    private TagPlayer findClosestPlayer() {
        TagPlayer closestPlayer = null;
        double minDistance = Double.MAX_VALUE;

        for (TagPlayer other : players) {
            if (other == player) continue;
            double distance = calculateDistance(player.getStaticInfo().getPos(), other.getStaticInfo().getPos());
            if (distance < minDistance) {
                minDistance = distance;
                closestPlayer = other;
            }
        }

        return closestPlayer;
    }

    private Acceleration nonTaggerBehavior(StaticInfo staticInfo, Velocity velocity) {
        TagPlayer tagger = findTagger();
        if (tagger == null) {
            return Acceleration.NoAcceleration;
        }

        Vector2D taggerPos = tagger.getStaticInfo().getPos();
        double distanceToTagger = calculateDistance(staticInfo.getPos(), taggerPos);

        // If the tagger is within the alert distance, run in a safe direction away from the tagger
        if (distanceToTagger < alertDistance) {
            targetPoint = chooseSafePointAwayFromTagger(staticInfo.getPos(), taggerPos);
            avoidingCorner = false;
        }

        // If close to the current target point or avoiding a corner, check corner avoidance
        if (calculateDistance(staticInfo.getPos(), targetPoint) < cornerThreshold || avoidingCorner) {
            if (isNearCorner(staticInfo.getPos())) {
                targetPoint = calculateParabolicPoint(staticInfo.getPos(), targetPoint);  // Calculate a parabolic for avoding corners
                avoidingCorner = true;
            } else {
                avoidingCorner = false;
                targetPoint = chooseFurthestSafePoint(taggerPos);  // Choose a new safe point farther from the tagger
            }
        }

        // Move toward the target point
        seekBehavior.setTarget(targetPoint);
        return seekBehavior.getSteering(staticInfo, velocity);
    }

    private TagPlayer findTagger() {
        for (TagPlayer p : players) {
            if (p.isTag()) {
                return p;
            }
        }
        return null;
    }

    private Vector2D chooseRandomSafePoint(Vector2D taggerPos) {
        Vector2D randomPoint;
        do {
            // Generate a random point within the safe area
            double x = bufferDistance + random.nextDouble() * (width - 2 * bufferDistance);
            double y = bufferDistance + random.nextDouble() * (height - 2 * bufferDistance);
            randomPoint = new Vector2D(x, y);
        } while (taggerPos != null && calculateDistance(randomPoint, taggerPos) < alertDistance);

        return randomPoint;
    }

    private Vector2D chooseSafePointAwayFromTagger(Vector2D nonTaggerPos, Vector2D taggerPos) {
        Vector2D escapeDirection = nonTaggerPos.minus(taggerPos).normalize();
        double safeX = clamp(nonTaggerPos.x() + escapeDirection.x() * (bufferDistance + random.nextDouble() * (width / 2 - bufferDistance)), bufferDistance, width - bufferDistance);
        double safeY = clamp(nonTaggerPos.y() + escapeDirection.y() * (bufferDistance + random.nextDouble() * (height / 2 - bufferDistance)), bufferDistance, height - bufferDistance);

        return new Vector2D(safeX, safeY);
    }

    private Vector2D chooseFurthestSafePoint(Vector2D taggerPos) {
        Vector2D[] safePoints = {
                new Vector2D(bufferDistance, bufferDistance),
                new Vector2D(width - bufferDistance, bufferDistance),
                new Vector2D(bufferDistance, height - bufferDistance),
                new Vector2D(width - bufferDistance, height - bufferDistance)
        };

        Vector2D furthestSafePoint = null;
        double maxDistance = -1;

        for (Vector2D point : safePoints) {
            double distance = calculateDistance(point, taggerPos);
            if (distance > maxDistance) {
                maxDistance = distance;
                furthestSafePoint = point;
            }
        }

        return furthestSafePoint;
    }

    private boolean isNearCorner(Vector2D pos) {
        return (pos.x() <= bufferDistance || pos.x() >= width - bufferDistance) &&
                (pos.y() <= bufferDistance || pos.y() >= height - bufferDistance);
    }

    private Vector2D calculateParabolicPoint(Vector2D currentPos, Vector2D targetCorner) {
        // Create a point that makes a parabolic path to avoid the corner
        // For the non tagger players don't get stuck in the corner
        double midX = (currentPos.x() + targetCorner.x()) / 2;
        double midY = (currentPos.y() + targetCorner.y()) / 2;


        if (targetCorner.x() < bufferDistance) midX += curveDistance;
        else if (targetCorner.x() > width - bufferDistance) midX -= curveDistance;

        if (targetCorner.y() < bufferDistance) midY += curveDistance;
        else if (targetCorner.y() > height - bufferDistance) midY -= curveDistance;

        return new Vector2D(midX, midY);
    }

    private double calculateDistance(Vector2D pos1, Vector2D pos2) {
        return Math.sqrt(Math.pow(pos2.x() - pos1.x(), 2) + Math.pow(pos2.y() - pos1.y(), 2));
    }

    private double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }
}
