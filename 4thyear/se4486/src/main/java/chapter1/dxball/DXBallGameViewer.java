package chapter1.dxball;

import chapter1.gameengine.JFrameGameViewer;

import java.awt.*;

public class DXBallGameViewer extends JFrameGameViewer<DXBallGame> {

    public DXBallGameViewer(DXBallGame game ) {
        super(game, DXConstants.GameWidth, DXConstants.GameHeight);
    }

    @Override
    protected void _renderGame(Graphics g, DXBallGame game) {
        game.render(g);
    }
}
