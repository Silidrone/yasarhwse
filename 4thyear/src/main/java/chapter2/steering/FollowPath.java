package chapter2.steering;

import chapter2.Acceleration;
import chapter2.StaticInfo;
import chapter2.SteeringBehavior;
import chapter2.Velocity;
import math.geom2d.Vector2D;

import java.util.List;

public class FollowPath implements SteeringBehavior {

    private static final double ARRIVAL_THRESHOLD = 0.1;
    List<Vector2D> path;

    int current;
    Seek seek;


    public FollowPath(List<Vector2D> path) {
        this.path = path;
        current =0;
        seek= new Seek(new Vector2D(0,0),5);
    }

    @Override
    public Acceleration getSteering(StaticInfo staticInfo, Velocity velocity) {

        if ( staticInfo.getPos().minus(path.get(current)).norm() < ARRIVAL_THRESHOLD)
        {
            current = (current+1)%path.size();
        }

        seek.setTarget(path.get(current));

        // code here..

        return seek.getSteering(staticInfo,velocity);
    }
}
