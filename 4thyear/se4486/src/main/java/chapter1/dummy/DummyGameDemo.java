package chapter1.dummy;

import chapter1.gameengine.GameEngine;
import chapter1.gameengine.SimpleGameEngine;

public class DummyGameDemo {

    public static void main(String[] args) {
        DummyGame dg = new DummyGame(800,600);
        DummyGameViewer dgv = new DummyGameViewer(dg);
        DummyGameController dgc = new DummyGameController(dg);
        dgv.addController(dgc);
        GameEngine<DummyGame> dge = new SimpleGameEngine<>();

        dge.start(dg,dgv);
    }
}
