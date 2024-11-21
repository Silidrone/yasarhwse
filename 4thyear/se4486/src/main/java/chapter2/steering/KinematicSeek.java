package chapter2.steering;


import chapter2.*;
import math.geom2d.Vector2D;

public class KinematicSeek implements SteeringBehavior {

    Vector2D targetPosition;
    float maxSpeed;

    public KinematicSeek(Vector2D targetPosition, float maxSpeed) {
        this.targetPosition = targetPosition;
        this.maxSpeed = maxSpeed;
    }

    /**
     * TODO: Implement simple kinematic seek as described in your book
     * @param staticInfo
     * @param velocity
     * @return
     */
    @Override
    public Acceleration getSteering(StaticInfo staticInfo, Velocity velocity) {

        Vector2D finalVelocity =  targetPosition.minus(staticInfo.getPos());


        if (finalVelocity.norm()> maxSpeed)
        {
            finalVelocity = finalVelocity.normalize().times(maxSpeed);
        }

        return new Acceleration(finalVelocity,0, AccelerationType.Kinematic);
    }


}
