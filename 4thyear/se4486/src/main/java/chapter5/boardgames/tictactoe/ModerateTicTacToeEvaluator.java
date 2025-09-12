package chapter5.boardgames.tictactoe;


import chapter5.boardgames.base.Board;
import chapter5.boardgames.ai.BoardEvaluator;

public class ModerateTicTacToeEvaluator implements BoardEvaluator {

    final double WIN_SCORE = 10000;
    final double LOSS_SCORE = -10000;
    final double DRAW_SCORE = 0;



    @Override
    public double evaluate(Board board, int player) {
        if (board.isGameOver()) {
            if (winBy((TicTacToeBoard) board, player))
                return WIN_SCORE - ((TicTacToeBoard) board).getMoveCount();
            else if (winBy((TicTacToeBoard) board, TicTacToe.otherPlayer(player)))
                return LOSS_SCORE;
            else return DRAW_SCORE;
        }
        int score =0;
        TicTacToeBoard tttb= (TicTacToeBoard) board;
        TicTacToeCell[][] cells = tttb.cells;

        for (int r = 0; r < tttb.boardSize; r++) {
            score += rowScore(cells,r, player);
            score += colScore(cells,r,player);

            score -= rowScore(cells,r,TicTacToe.otherPlayer(player));
            score -= colScore(cells,r,TicTacToe.otherPlayer(player));
        }

        //System.out.println("EVALUTED:" + score);
        return score;
    }

    private int colScore(TicTacToeCell[][] cells, int c, int player) {
        int score =0;
        for (TicTacToeCell[] row : cells) {
            if (row[c] == TicTacToe.playerSymbol(TicTacToe.otherPlayer(player))) {
                return 0;
            }
            if ((row[c] == TicTacToe.playerSymbol(player)))
                score++;
        }

        return score*score;
    }

    private int rowScore(TicTacToeCell[][] cells, int r, int player) {
        int score =0;
        for (int c = 0; c < cells[r].length; c++) {
            if (cells[r][c]== TicTacToe.playerSymbol(TicTacToe.otherPlayer(player)))
            {
                return 0;
            }
            if ((cells[r][c]== TicTacToe.playerSymbol(player)))
                score++;
        }

        return score*score;
    }

    private boolean winBy(TicTacToeBoard  board, int player) {
        if (TicTacToe.hasPerfectRowOf(board.getBoard(),TicTacToe.playerSymbol(player)))
            return true;

        if (TicTacToe.hasPerfectColOf(board.getBoard(),TicTacToe.playerSymbol(player)))
            return true;



        return TicTacToe.hasPerfectDiagonalOf(board.getBoard(),TicTacToe.playerSymbol(player));

    }
}
