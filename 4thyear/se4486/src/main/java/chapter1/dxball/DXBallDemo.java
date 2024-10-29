package chapter1.dxball;

import chapter1.gameengine.SimpleGameEngine;

public class DXBallDemo {
    public static void main(String[] args) {
        DXBallGame game = new DXBallGame();
        game.setTileGenerator(new Level1TileGenerator());
        DXBallGameViewer viewer = new DXBallGameViewer(game);

        viewer.addController(game);
        SimpleGameEngine<DXBallGame> engine =  new SimpleGameEngine<>();

        engine.start(game,viewer);
    }
}
