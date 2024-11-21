package chapter1.dxball;

import chapter1.BaseEntity;

import java.awt.*;
import java.awt.geom.Point2D;

public class DXBallRacket extends BaseEntity<DXBallGame> {



    public DXBallRacket(double x, double y, int width, int height) {
        super(x,y, width, height);
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

        g.setColor(DXConstants.RacketColor);
        g.fillRect((int) topLeft.getX(), (int) topLeft.getY(),width,height);
    }
}
