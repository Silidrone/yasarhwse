package chapter2.taggame.hw.efe;

import chapter2.*;
import math.geom2d.Vector2D;

public class Evade implements SteeringBehavior {
    private Vector2D target;
    private double maxSpeed;
    private double width;
    private double height;

    public Evade(double maxSpeed, double width, double height) {
        this.maxSpeed = maxSpeed;
        this.width = width;
        this.height = height;
    }

    public void setTarget(Vector2D target) {
        this.target = target;
    }

    @Override
    public Acceleration getSteering(StaticInfo staticInfo, Velocity velocity) {
        Vector2D position = staticInfo.getPos();
        Vector2D directionToTarget = position.minus(target).normalize();
        Vector2D desiredVelocity = directionToTarget.times(maxSpeed);

        // Duvar çarpışma kontrolü
        Vector2D adjustedVelocity = checkAndBounce(position, desiredVelocity);

        return new Acceleration(adjustedVelocity, 0, AccelerationType.Dynamic);
    }

    private Vector2D checkAndBounce(Vector2D position, Vector2D velocity) {
        double x = position.x();
        double y = position.y();
        double vx = velocity.x();
        double vy = velocity.y();

        if (x <= 0 || x >= width) {
            vx = -vx;
        }
        if (y <= 0 || y >= height) {
            vy = -vy;
        }

        return new Vector2D(vx, vy);
    }
}
