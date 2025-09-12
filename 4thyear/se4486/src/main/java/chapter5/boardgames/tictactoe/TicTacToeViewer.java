package chapter5.boardgames.tictactoe;


import base.SimpleGame;
import base.SimplePanel;

import chapter5.boardgames.ai.*;
import chapter5.boardgames.base.Board;
import chapter5.boardgames.player.SimpleAIPlayer;
import chapter5.boardgames.player.SimpleHumanPlayer;
import chapter5.gui.BoardController;
import chapter5.gui.BoardGame;
import chapter5.gui.BoardViewer;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Bootstrap;

import java.awt.geom.Rectangle2D;

public class TicTacToeViewer implements BoardViewer {

    Rectangle2D boundary;
    TicTacToeBoard board;
    private Color BackgroundColor = Color.white;
    private Color LineColor = Color.black;
    private float cellSize;

    @Override
    public void init(Rectangle2D boundary, Board board) {
        this.boundary = boundary;
        this.board= (TicTacToeBoard) board;
        cellSize = (float) (boundary.getHeight()/this.board.boardSize);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {


        graphics.setColor(BackgroundColor);
        graphics.fillRect(0,0,(float) boundary.getWidth(),(float) boundary.getHeight());


        renderCells(graphics);

    }

    private void renderCells(Graphics graphics) {
        for (int r = 0; r < board.boardSize; r++) {
            for (int c = 0; c < board.boardSize; c++) {
                renderCell(r,c, graphics);
            }

        }
    }

    private void renderCell(int r, int c, Graphics graphics) {
        graphics.setColor(LineColor);
        graphics.drawRect(c*cellSize,r*cellSize,cellSize,cellSize);


        renderCellContent(graphics,r,c);
    }

    private void renderCellContent(Graphics graphics, int r, int c) {
        if (board.cells[r][c]== TicTacToeCell.Empty)
            return;

        float x= c*cellSize;
        float y = r * cellSize;

        Image image = null;
        try {
            image = board.cells[r][c]== TicTacToeCell.Cross ?
                        new Image("./res/chapter5/ttt_cross.jpg"):
                        new Image("./res/chapter5/ttt_circle.jpg");
        } catch (SlickException e) {
            e.printStackTrace();
        }

        image.draw(x+1,y+1, (cellSize-2)/image.getWidth());


    }

    public int getRowFromY(int mouseY) {
        int row = (int) ((mouseY-boundary.getMinY())/cellSize);

        return row;
    }

    public int getColFromX(int mouseX) {
        int col = (int) ((mouseX-boundary.getMinX())/cellSize);

        return col;
    }

    public static void main(String[] args) {
        Board board = new TicTacToeBoard(3);


        BoardViewer viewer = new TicTacToeViewer();
        BoardController controller = new TicTacToeController();

        BoardGame tictactoeGame = new BoardGame(board,viewer,controller);

        BoardAI ai1 = new BoardAIWithTT( new AlphaBetaPruning(9));
        BoardEvaluator evaluator1= new ModerateTicTacToeEvaluator();

        BoardAI ai2 = new MiniMaxUndoing(6);




        //tictactoeGame.addPlayer(new SimpleAIPlayer("TTT Master", ai1,new SimpleTicTacToeEvaluator(),SimpleAIPlayer.MAX_THINK_TIME));
        //tictactoeGame.addPlayer(new SimpleAIPlayer("TTT Master 1",ai1, evaluator1,SimpleAIPlayer.MAX_THINK_TIME));
        tictactoeGame.addPlayer(new SimpleAIPlayer("TTT Master 2",ai2, new ModerateTicTacToeEvaluator(),SimpleAIPlayer.MAX_THINK_TIME));
        tictactoeGame.addPlayer(new SimpleHumanPlayer("T"));



        SimplePanel panel = new SimplePanel(tictactoeGame);
        SimpleGame game = new SimpleGame("TicTacToe 1.0",panel);
        Bootstrap.runAsApplication(game,600,600,false);

    }


}
