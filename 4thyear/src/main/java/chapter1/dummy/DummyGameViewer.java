package chapter1.dummy;

import chapter1.gameengine.JFrameGameViewer;
import utils.ThreadUtils;

import java.awt.*;


public class DummyGameViewer extends JFrameGameViewer<DummyGame> {
    private static final Color BACKGROUNCOLOR = Color.gray;
    private static final Color MAINCOLOR = Color.yellow;


    public DummyGameViewer(DummyGame game) {
        super(game, game.getMaxX(), game.getMaxY());
    }

    @Override
    protected void _renderGame(Graphics g, DummyGame game) {
        g.setColor(BACKGROUNCOLOR);
        g.fillRect(0,0,width,height);

        g.setColor(MAINCOLOR);
        g.fillRect(game.getX(),game.getY(),DummyGame.DELTA,DummyGame.DELTA );

        //ThreadUtils.pause(10);
    }
}
