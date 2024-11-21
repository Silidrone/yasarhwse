package chapter1.dummy;

import chapter1.gameengine.BaseGameController;

import java.awt.event.KeyEvent;

public class DummyGameController extends BaseGameController<DummyGame> {

    DummyGame game;

    public DummyGameController(DummyGame game) {
        this.game = game;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);

        if (e.getKeyCode() == KeyEvent.VK_UP)
        {
            game.up();
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            game.down();
        }
    }
}
