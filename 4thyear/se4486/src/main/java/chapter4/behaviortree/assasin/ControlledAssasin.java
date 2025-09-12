package chapter4.behaviortree.assasin;


import chapter2.BasicRenderable;
import chapter2.StaticInfo;
import chapter2.SteeringBehavior;
import chapter2.Velocity;
import math.geom2d.Vector2D;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.geom.Rectangle2D;

public class ControlledAssasin extends Assasin {
    public static final float PLAYER_RADIUS = 20;
    public static final int NORMAL_SPEED = 3;
    private static final int WALK_SPEED = 1;
    private static final double WALK_OFFSET = 3;

    String name;

   private boolean terminated= false;



    public ControlledAssasin(String name, StaticInfo staticInfo) {
        super(name,staticInfo);
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame, Rectangle2D boundary) {
        super.init(gameContainer,stateBasedGame,boundary);

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        super.render(gameContainer,stateBasedGame,graphics);

        float r = (float) (body.getRadius()+WALK_OFFSET);
        if (walking)
            graphics.drawOval((float) (staticInfo.getPos().x()-r), (float) (staticInfo.getPos().y()-r),2*r, 2*r);
    }



    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, float time) {
        super.update(gameContainer, stateBasedGame,time);

        if (!terminated)
            applyControls(gameContainer);
    }

    private void applyControls(GameContainer gameContainer) {
        int x = 0, y = 0;

        walking = gameContainer.getInput().isKeyDown(Input.KEY_SPACE);

        if (gameContainer.getInput().isKeyDown(Input.KEY_LEFT)) {
            x = walking ? -WALK_SPEED : -NORMAL_SPEED;
        } else if (gameContainer.getInput().isKeyDown(Input.KEY_RIGHT)) {
            x = walking ? WALK_SPEED : NORMAL_SPEED;
        }

        if (gameContainer.getInput().isKeyDown(Input.KEY_DOWN)) {
            y = walking ? -WALK_SPEED : -NORMAL_SPEED;
        } else if (gameContainer.getInput().isKeyDown(Input.KEY_UP)) {
            y = walking ? WALK_SPEED : NORMAL_SPEED;
        }

        running = !walking && (Math.abs(x) > 0 || Math.abs(y) > 0);

        setVelocity(new Velocity(new Vector2D(x, y), 0));
    }


    public void terminate() {
        terminated= true;
        setSteeringBehavior(SteeringBehavior.NoSteering);
        setVelocity(Velocity.NoVelocity());
        ((BasicRenderable)body).setColor(Color.gray);
    }
}
