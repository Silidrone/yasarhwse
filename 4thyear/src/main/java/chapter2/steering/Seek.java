package chapter2.steering;


import chapter2.*;
import math.geom2d.Vector2D;

public class Seek implements SteeringBehavior {

    Vector2D target;
    double maxAcceleration;

    public Seek(Vector2D target, double maxAcceleration) {
        this.target = target;
        this.maxAcceleration = maxAcceleration;
    }

    @Override
    public Acceleration getSteering(StaticInfo staticInfo, Velocity velocity) {
        Vector2D acceleration = target.minus(staticInfo.getPos());

        if (acceleration.norm() > maxAcceleration) {
            acceleration = acceleration.normalize().times(maxAcceleration);
        }

        if (acceleration.norm() < 0.001) {
            return Acceleration.NoAcceleration;
        }

        return new Acceleration(acceleration, 0, AccelerationType.Dynamic);
    }

    public void setTarget(Vector2D target) {
        this.target = target;
    }
}
