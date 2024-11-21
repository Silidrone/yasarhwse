package chapter2;


import math.geom2d.Vector2D;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.geom.Rectangle2D;

public class MovingEntity extends BasicGameEntity {


    private final static double MAX_SPEED = 5;
    private final static double MAX_ACCELERATION = 1;
    private final static float TIME_COEFFICIENT = 0.03f;

    Velocity velocity = Velocity.NoVelocity();
    Acceleration acceleration = Acceleration.NoAcceleration;

    Rectangle2D boundary;

    SteeringBehavior steeringBehavior;

    public void setBoundary(Rectangle2D boundary) {
        this.boundary = boundary;
    }

    public MovingEntity(StaticInfo staticInfo, Renderable body) {
        super(staticInfo, body);
    }

    public void setSteeringBehavior(SteeringBehavior steeringBehavior) {
        this.steeringBehavior = steeringBehavior;
    }

    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    public void setAcceleration(Acceleration acceleration) {
        this.acceleration = acceleration;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame, Rectangle2D boundary) {
        super.init(gameContainer, stateBasedGame, boundary);

        this.boundary = boundary;
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, float time) {
        super.update(gameContainer, stateBasedGame, time);

        if (steeringBehavior != null)
            acceleration = steeringBehavior.getSteering(staticInfo, velocity);

        validateAcceleration(acceleration);

        time = time * TIME_COEFFICIENT; // for debug time :1

        staticInfo.update(velocity, acceleration, time);

        updateVelocity(acceleration, time);
        handleBoundaryCollisions();
    }

    private void validateAcceleration(Acceleration acceleration) {
        if (acceleration.getLinear().norm() > MAX_ACCELERATION) {
            acceleration.setLinear(acceleration.linear.normalize().times(MAX_ACCELERATION));
        }
    }

    private void handleBoundaryCollisions() {
        if ((staticInfo.getPos().x() + body.getRadius() >= boundary.getMaxX() && velocity.getLinear().x() > 0)
                || (staticInfo.getPos().x() - body.getRadius() <= boundary.getMinX() && velocity.getLinear().x() < 0)) {
            velocity.setLinear(new Vector2D(velocity.getLinear().x() * -1, velocity.getLinear().y()));
        }

        if ((staticInfo.getPos().y() + body.getRadius() >= boundary.getMaxY() && velocity.getLinear().y() > 0)
                || (staticInfo.getPos().y() - body.getRadius() <= boundary.getMinY() && velocity.getLinear().y() < 0)) {
            velocity.setLinear(new Vector2D(velocity.getLinear().x(), velocity.getLinear().y() * -1));
        }
    }

    private void updateVelocity(Acceleration acceleration, float time) {
        velocity.update(acceleration, time);
        applyLimits();
    }

    private void applyLimits() {
        if (velocity.linear.norm() > MAX_SPEED) {
            velocity.setLinear(velocity.linear.normalize().times(MAX_SPEED));
        }
    }

    public Rectangle2D getBoundary() {
        return boundary;
    }

    public Velocity getVelocity() {
        return velocity;
    }
}
