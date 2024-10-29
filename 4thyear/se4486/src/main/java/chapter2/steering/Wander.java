package chapter2.steering;

import chapter2.Acceleration;
import chapter2.StaticInfo;
import chapter2.SteeringBehavior;
import chapter2.Velocity;
import math.geom2d.Vector2D;
import utils.RandomUtils;

public class Wander implements SteeringBehavior {

    double maxAngularChange = 0.2;
    double wanderOrientation=0;

    private final double wanderOffset=100;
    private final double wanderRadius =100;

    Seek seek= new Seek(new Vector2D(),5);

    /**
     * TODO: Implement a Wandering algorithm
     * @param staticInfo
     * @param velocity
     * @return
     */
    @Override
    public Acceleration getSteering(StaticInfo staticInfo, Velocity velocity) {

        Vector2D delta = new Vector2D(wanderOffset,0).rotate(staticInfo.getOrientation());
        Vector2D center = staticInfo.getPos().plus(delta);

        wanderOrientation += RandomUtils.randomBinomial()*maxAngularChange;

        delta = new Vector2D(wanderRadius,0).rotate(wanderOrientation);
        Vector2D target = center.plus(delta);

        seek.setTarget(target);
        return seek.getSteering(staticInfo,velocity);
    }


}
