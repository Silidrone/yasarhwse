package chapter2.taggame;


import chapter2.*;
import math.geom2d.Vector2D;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.geom.Rectangle2D;

public class TagPlayer extends MovingEntity implements TagGameListener {
    private static final float PLAYER_RADIUS = 20;
    private static final int TAGRADIUS = 23;
    private static final Color TAGCOLOR = Color.red;

    String name;

    TagSteeringEngine steeringEngine;
    TagArena arena;

    int tagCount;
    boolean isTag;

     public TagPlayer(String name, StaticInfo staticInfo, Color color) {
        super(staticInfo, new Ball(color,PLAYER_RADIUS));
        this.name = name;

    }

    public boolean isTag() {
        return isTag;
    }

    public void setSteeringEngine(TagSteeringEngine engine)
    {
        steeringEngine = engine;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame, Rectangle2D boundary) {
        super.init(gameContainer,stateBasedGame,boundary);
        arena = ((TagGame)stateBasedGame).getArena();
        tagCount=0;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        super.render(gameContainer,stateBasedGame,graphics);

        if (isTag)
            renderTag(graphics);
    }

    private void renderTag(Graphics graphics) {
        Vector2D center = staticInfo.getPos();
        Color c = graphics.getColor();
        graphics.setColor(TAGCOLOR);
        graphics.drawOval( (float)center.x()-TAGRADIUS, (float) center.y()-TAGRADIUS, 2*TAGRADIUS,2*TAGRADIUS);

        graphics.setColor(c);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, float time) {
        super.update(gameContainer, stateBasedGame,time);
//        if(name.equals("Can")) {
//            System.out.println("Can: " + (isTag ? "is tagged!" : "is NOT tagged!"));
//        }
    }

    @Override
    public void tagChanged(TagPlayer player) {
        isTag = player==this;

        if (isTag) {
            setSteeringBehavior(SteeringBehavior.NoSteering);
            setVelocity(Velocity.NoVelocity());
            setAcceleration(Acceleration.NoAcceleration);
            tagCount++;
        }
        else {
            setSteeringBehavior(steeringEngine.getSteeringBehavior(this,arena));
        }

    }

    @Override
    public void warmupFinished() {
        setSteeringBehavior(steeringEngine.getSteeringBehavior(this,arena));
    }

    public boolean tagging(TagPlayer player) {

        double distance = staticInfo.getPos().minus(player.staticInfo.getPos()).norm();

        return  ( body.getRadius()+player.body.getRadius() >= distance);

    }
}
