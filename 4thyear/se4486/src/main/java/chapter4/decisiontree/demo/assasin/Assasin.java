package chapter4.decisiontree.demo.assasin;


import chapter2.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.geom.Rectangle2D;

public class Assasin  extends MovingEntity {
    public static final float PLAYER_RADIUS = 20;

    String name;

    SteeringBehavior normalBehavior;

    protected boolean terminated= false;


    public void setNormalBehavior(SteeringBehavior normalBehavior) {
        this.normalBehavior = normalBehavior;
        setSteeringBehavior(normalBehavior);
    }



    public Assasin(String name, StaticInfo staticInfo) {
        super(staticInfo, new Ball(Color.yellow,PLAYER_RADIUS));
        this.name = name;

    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame, Rectangle2D boundary) {
        super.init(gameContainer,stateBasedGame,boundary);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        super.render(gameContainer,stateBasedGame,graphics);

    }



    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, float time) {
        super.update(gameContainer, stateBasedGame,time);

    }


    public void terminate() {
        terminated= true;
        setSteeringBehavior(SteeringBehavior.NoSteering);
        setVelocity(Velocity.NoVelocity());
        ((BasicRenderable)body).setColor(Color.gray);
    }
}
