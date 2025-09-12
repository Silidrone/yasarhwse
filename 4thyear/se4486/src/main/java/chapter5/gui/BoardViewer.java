package chapter5.gui;

import chapter5.boardgames.base.Board;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.geom.Rectangle2D;

public interface BoardViewer {
    void init(Rectangle2D boundary, Board board);

    void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics);
}
