package chapter2;

import math.geom2d.Vector2D;

public class StaticInfo {
    Vector2D pos;
    double orientation;

    OrientationType orientationType;

    public StaticInfo(Vector2D pos, double orientation, OrientationType orientationType) {
        this.pos = pos;
        this.orientation = orientation;
        this.orientationType = orientationType;
    }

    public Vector2D getPos() {
        return pos;
    }

    public void setPos(Vector2D pos) {
        this.pos = pos;
    }

    public double getOrientation() {
        return orientation;
    }

    public void setOrientation(double orientation) {
        this.orientation = orientation;
    }

    public void update(Velocity velocity, Acceleration acceleration, float time) {
        Vector2D newPos = pos.plus(velocity.getLinear().times(time));

        if (acceleration.getAccelerationType() == AccelerationType.Dynamic) {
            newPos = newPos.plus(acceleration.getLinear().times(0.5 * time * time));
        }

        pos = newPos;

        if (orientationType == OrientationType.VelocityBased) {
            adjustOrientation(velocity.getLinear());
        } else {
            orientation = velocity.getAngular() * time + 0.5 * acceleration.getAngular() * time * time;
        }
    }


    private void adjustOrientation(Vector2D finalLinearVelocity) {
        if (finalLinearVelocity.norm() > 0.001)
            orientation = Math.atan2(finalLinearVelocity.y(), finalLinearVelocity.x());
    }

}
