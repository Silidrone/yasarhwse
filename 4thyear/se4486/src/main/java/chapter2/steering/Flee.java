package chapter2.steering;



import chapter2.*;
import math.geom2d.Vector2D;

public class Flee implements SteeringBehavior {

    Vector2D target;

    double maxAcceleration;

    public Flee(Vector2D target, double maxAcceleration) {
        this.target = target;
        this.maxAcceleration = maxAcceleration;
    }

    @Override
    public Acceleration getSteering(StaticInfo staticInfo, Velocity velocity) {

        Vector2D linear = staticInfo.getPos().minus(target);

        if (linear.norm()>maxAcceleration)
        {
            linear = linear.normalize().times(maxAcceleration);
        }

        return new Acceleration(linear,0, AccelerationType.Dynamic);
    }

    public void setTarget(Vector2D target) {
        this.target=target;
    }
}
