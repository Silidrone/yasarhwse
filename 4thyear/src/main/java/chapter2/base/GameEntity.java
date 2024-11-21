package chapter2.base;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.geom.Rectangle2D;

public interface GameEntity {
    void init(GameContainer gameContainer, StateBasedGame stateBasedGame, Rectangle2D boundary);
    void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics);
    void update(GameContainer gameContainer, StateBasedGame stateBasedGame, float time);
}
