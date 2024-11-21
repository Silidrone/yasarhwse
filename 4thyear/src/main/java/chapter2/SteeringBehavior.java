package chapter2;

import math.geom2d.Vector2D;

public interface SteeringBehavior {
    SteeringBehavior NoSteering = (x, y) -> new Acceleration(new Vector2D(0, 0), 0.0, AccelerationType.Dynamic);

    Acceleration getSteering(StaticInfo staticInfo, Velocity velocity);
}
