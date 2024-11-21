package chapter1.dxball;

import chapter1.BaseEntity;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;


public class DXBallArea extends BaseEntity<DXBallGame> {

    List<DXTile> tiles;

    public DXBallArea(Point2D topLeft, int width, int height) {
        super(topLeft, width, height);
    }

    @Override
    public void init(DXBallGame game) {
        super.init(game);

        tiles = game.getTileGenerator().generate(this);

    }

    @Override
    public void update(DXBallGame game, long time) {
        super.update(game, time);
    }

    @Override
    public void render(DXBallGame game, Graphics g) {
        super.render(game, g);

        g.setColor(DXConstants.BackGroundColor);
        g.fillRect((int) topLeft.getX(), (int) topLeft.getY(),width,height);

        tiles.forEach((t)->{t.render(game,g);});

    }

    public boolean isEmpty() {
        return tiles.isEmpty();
    }
}
