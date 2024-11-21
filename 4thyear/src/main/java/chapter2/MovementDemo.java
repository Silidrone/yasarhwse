package chapter2;


import chapter2.steering.*;
import math.geom2d.Vector2D;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Bootstrap;

public class MovementDemo extends StateBasedGame {
    private static final int DemoWidth = 800;
    private static final int DemoHeight = 600;
    private final int movementPanel = 0;

    public MovementDemo(String name) {
        super(name);
        addState(new MovementPanel());
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        enterState(movementPanel);
    }

    MovementPanel getPanel() {
        return (MovementPanel) getState(movementPanel);
    }

    public static void main(String[] args) {
        MovementDemo demo = new MovementDemo("Movement Demo");
        MovementPanel panel = demo.getPanel();

        MovingEntity red = new MovingEntity(new StaticInfo(new Vector2D(350, 350), 70, OrientationType.VelocityBased), new Ball(Color.red));
        red.setSteeringBehavior(new Arrive(new Vector2D(50, 50), 10));
        panel.add(red);

        Bootstrap.runAsApplication(demo, DemoWidth, DemoHeight, false);
    }
}
