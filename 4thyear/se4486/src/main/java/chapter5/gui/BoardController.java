package chapter5.gui;

import chapter5.boardgames.base.Board;
import chapter5.boardgames.player.HumanPlayer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

public interface BoardController {
    public void init(BoardViewer viewer);
    public void update(Board board, GameContainer gameContainer, StateBasedGame stateBasedGame, float time, HumanPlayer currentPlayer);
}
