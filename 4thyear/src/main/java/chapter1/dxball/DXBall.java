package chapter1.dxball;

import chapter1.BaseEntity;

import java.awt.*;
import java.awt.geom.Point2D;

public class DXBall extends BaseEntity<DXBallGame> {

    Point2D velocity;


    public DXBall(double x, double y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void init(DXBallGame game) {
        super.init(game);
        topLeft.setLocation(game.initialBallPos());

        velocity= new Point2D.Double(0,0) ;

    }

    @Override
    public void update(DXBallGame game, long time) {
        super.update(game, time);

        double x = topLeft.getX() + velocity.getX()*time;
        double y = topLeft.getY() + velocity.getY()*time;

        topLeft.setLocation(x,y);
    }

    @Override
    public void render(DXBallGame game, Graphics g) {
        super.render(game, g);

        g.setColor(DXConstants.BallColor);

        g.fillOval((int) topLeft.getX(), (int) topLeft.getY(),width,height);

    }

    public void setVelocity(Point2D v) {
        velocity.setLocation(v);
    }

    public Point2D getVelocity() {
        return velocity;
    }
}

