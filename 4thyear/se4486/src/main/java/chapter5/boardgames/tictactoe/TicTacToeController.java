package chapter5.boardgames.tictactoe;



import chapter5.boardgames.base.Board;
import chapter5.boardgames.player.HumanPlayer;
import chapter5.gui.BoardController;
import chapter5.gui.BoardViewer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

public class TicTacToeController implements BoardController {

    TicTacToeViewer viewer;

    @Override
    public void init(BoardViewer viewer) {
        this.viewer = (TicTacToeViewer) viewer;
    }

    @Override
    public void update(Board board, GameContainer gameContainer, StateBasedGame stateBasedGame, float time, HumanPlayer currentPlayer) {
        TicTacToeBoard tttBoard= (TicTacToeBoard) board;
        if (gameContainer.getInput().isMousePressed(Input.MOUSE_LEFT_BUTTON))
        {
            int row = viewer.getRowFromY(gameContainer.getInput().getMouseY());
            int col = viewer.getColFromX(gameContainer.getInput().getMouseX());

            if (row>=0 && row<tttBoard.boardSize && col>=0 && col<tttBoard.boardSize)
            {
                if( tttBoard.cells[row][col]== TicTacToeCell.Empty)
                {
                    TicTacToeMove move = new TicTacToeMove(row,col);
                    currentPlayer.setMove(move);
                }

            }
        }
    }
}
