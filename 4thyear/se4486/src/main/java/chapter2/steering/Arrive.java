package chapter2.steering;



import chapter2.*;
import math.geom2d.Vector2D;

public class Arrive  implements SteeringBehavior {

    Vector2D target;

    double maxAcceleration = 0.2;
    double maxSpeed = 10;

    double targetRadius = 1;
    double slowDownRadius = 50;


    double timeToTarget = 0.1;


    public Arrive(Vector2D target, double maxAcceleration) {
        this.target = target;
        this.maxAcceleration = maxAcceleration;
    }

    /**
     * TODO: Analyze the code below. Write a test for this behavior compare with seek
     * @param staticInfo
     * @param velocity
     * @return
     */
    @Override
    public Acceleration getSteering(StaticInfo staticInfo, Velocity velocity) {

        Vector2D direction = target.minus(staticInfo.getPos());
        double distance = direction.norm();

        double targetSpeed = maxSpeed;

        if (distance<targetRadius)
            targetSpeed = 0;
        else if (distance<slowDownRadius)
        {
            targetSpeed *= distance/slowDownRadius;
        }

        Vector2D targetVelocity = direction.normalize().times(targetSpeed);

        Vector2D linear = targetVelocity.minus(velocity.getLinear()).times(1/timeToTarget);

        if (linear.norm()>maxAcceleration)
            linear = linear.normalize().times(maxAcceleration);




        return new Acceleration(linear,0, AccelerationType.Dynamic);
    }

    public void setTarget(Vector2D target) {
        this.target = target;
    }
}
