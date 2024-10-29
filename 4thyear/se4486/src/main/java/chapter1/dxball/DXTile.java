package chapter1.dxball;

import chapter1.BaseEntity;

import java.awt.*;
import java.awt.geom.Point2D;

public class DXTile extends BaseEntity<DXBallGame>
{
    Color color;


    public DXTile(Color color, double x, double y,int width, int height)
    {
        super(new Point2D.Double(x,y),width,height);
        this.color = color;
    }

    @Override
    public void init(DXBallGame game) {
        super.init(game);
    }

    @Override
    public void update(DXBallGame game, long time) {
        super.update(game, time);
    }

    @Override
    public void render(DXBallGame game, Graphics g) {
        super.render(game, g);

        g.setColor(color);

        g.fillRect((int) topLeft.getX(), (int) topLeft.getY(),width,height);


    }
}
