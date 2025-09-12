package chapter4.behaviortree.assasin;


import chapter2.base.GameEntity;
import math.geom2d.polygon.Polygon2D;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.geom.Rectangle2D;

public class Obstacle implements GameEntity {

    Polygon2D p;

    Obstacle(Polygon2D p)
    {
        this.p = p;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame, Rectangle2D boundary) {

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        float vertices[] = new float[p.vertexNumber()*2];
        for (int i = 0; i < p.vertexNumber(); i++) {
            vertices[2*i]= (float) p.vertex(i).x();
            vertices[2*i+1]= (float) p.vertex(i).y();
        }
        graphics.setColor(Color.green);
        graphics.fill(new Polygon(vertices));
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, float time) {

    }
}
