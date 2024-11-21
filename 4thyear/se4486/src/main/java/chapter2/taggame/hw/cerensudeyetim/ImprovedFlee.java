package chapter2.taggame.hw.cerensudeyetim;

import math.geom2d.Vector2D;
import chapter2.SteeringBehavior;
import chapter2.Acceleration;
import chapter2.AccelerationType;
import chapter2.StaticInfo;
import chapter2.Velocity;

public class ImprovedFlee implements SteeringBehavior {
    private final Vector2D target;
    private final double maxAcceleration;
    private final double predictionTime;

    public ImprovedFlee(Vector2D target, double maxAcceleration, double predictionTime) {
        this.target = target;
        this.maxAcceleration = maxAcceleration;
        this.predictionTime = predictionTime;
    }

    @Override
    public Acceleration getSteering(StaticInfo staticInfo, Velocity velocity) {
        Vector2D predictedPosition = target.plus(velocity.getLinear().times(predictionTime));
        Vector2D acceleration = staticInfo.getPos().minus(predictedPosition);

        if (acceleration.norm() > maxAcceleration) {
            acceleration = acceleration.normalize().times(maxAcceleration);
        }

        if (acceleration.norm() < 0.001) {
            return Acceleration.NoAcceleration;
        }

        return new Acceleration(acceleration, 0, AccelerationType.Dynamic);
    }
}
