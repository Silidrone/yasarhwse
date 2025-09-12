package chapter5.boardgames.tictactoe;


import chapter5.boardgames.base.Board;
import chapter5.boardgames.ai.BoardEvaluator;

public class SimpleTicTacToeEvaluator implements BoardEvaluator {

    final double WIN_SCORE = 10000;
    final double LOSS_SCORE = -10000;
    final double DRAW_SCORE = 0;


    /**
     * TODO-2: Implement very naive evaluator that returns a non-zero result
     *         onluy when the game is over
     * @param board
     * @param player
     * @return
     */
    @Override
    public double evaluate(Board board, int player) {
        if (!board.isGameOver())
            return DRAW_SCORE;

        if (winBy((TicTacToeBoard) board,player))
            return WIN_SCORE-((TicTacToeBoard) board).getMoveCount();
        else if (winBy((TicTacToeBoard) board, TicTacToe.otherPlayer(player)))
            return LOSS_SCORE;
        else return DRAW_SCORE;
    }

    private boolean winBy(TicTacToeBoard  board, int player) {
        if (TicTacToe.hasPerfectRowOf(board.getBoard(),TicTacToe.playerSymbol(player)))
            return true;

        if (TicTacToe.hasPerfectColOf(board.getBoard(),TicTacToe.playerSymbol(player)))
            return true;



        return TicTacToe.hasPerfectDiagonalOf(board.getBoard(),TicTacToe.playerSymbol(player));

    }
}
