package chapter4.decisiontree.demo.assasin;



import chapter2.base.GameEntity;
import math.geom2d.Vector2D;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.geom.Rectangle2D;

public class Target implements GameEntity {
    Vector2D pos;
    public Target(Vector2D target) {
        pos = target;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame, Rectangle2D boundary) {

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        try {
            Image img = new Image("./bee.png");
            graphics.drawImage(img, (float)( pos.x()-img.getWidth()/2.0), (float) (pos.y()-img.getHeight()/2.0 ));

        } catch (SlickException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, float time) {

    }
}
