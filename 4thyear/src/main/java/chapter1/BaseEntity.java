package chapter1;

import chapter1.gameengine.Game;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class BaseEntity<G extends Game> implements GameEntity<G>{

    protected Point2D topLeft;
    protected int width;
    protected int height;

    public BaseEntity(Point2D topLeft, int width, int height) {
        this.topLeft = topLeft;
        this.width = width;
        this.height = height;
    }

    public BaseEntity(double x, double y, int width, int height) {
        this(new Point2D.Double(x,y),width,height);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }



    @Override
    public Point2D topLeft() {
        return topLeft;
    }

    @Override
    public Rectangle2D boundingRect() {
        return new Rectangle2D.Double(topLeft.getX(),topLeft.getY(),width,height);
    }

    @Override
    public void init(G game) {

    }

    @Override
    public void update(G game, long time) {

    }

    @Override
    public void render(G game, Graphics g) {

    }
}
