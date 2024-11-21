package chapter2;


import math.geom2d.Vector2D;

public class Acceleration {

    public static final Acceleration NoAcceleration = new Acceleration(new Vector2D(0,0),
                                                0,AccelerationType.Dynamic);

    Vector2D linear;
    double angular;

    public Acceleration(Vector2D linear, double angular, AccelerationType accelerationType) {
        this.linear = linear;
        this.angular = angular;
        this.accelerationType = accelerationType;
    }

    AccelerationType accelerationType = AccelerationType.Dynamic;

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

    public AccelerationType getAccelerationType() {
        return accelerationType;
    }

    public void setAccelerationType(AccelerationType accelerationType) {
        this.accelerationType = accelerationType;
    }
}
