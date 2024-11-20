package chapter2;

import math.geom2d.Vector2D;

public class Velocity {

    public static Velocity NoVelocity() { return new Velocity(new Vector2D(0,0),0);};


    Vector2D linear;
    double angular;


    public Velocity(Vector2D linear, double rotation) {
        this.linear = linear;
        this.angular = rotation;
    }

    public Vector2D getLinear() {
        return linear;
    }

    public void setLinear(Vector2D linear) {
        this.linear = linear;
    }

    public double getAngular() {
        return angular;
    }

    public void setAngular(double angular) {
        this.angular = angular;
    }

    public void update(Acceleration acceleration, float time)
    {
        // code here..
        if (acceleration.getAccelerationType() == AccelerationType.Kinematic)
        {
            linear= acceleration.getLinear();
            angular = acceleration.getAngular();
        }
        else {
            linear = linear.plus(acceleration.getLinear().times(time));
            angular = angular+ acceleration.getAngular()*time;
        }

    }
}
