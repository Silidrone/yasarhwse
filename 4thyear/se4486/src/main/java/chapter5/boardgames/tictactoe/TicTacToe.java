package chapter5.boardgames.tictactoe;


import chapter5.boardgames.base.Move;
import utils.RandomUtils;

import java.util.ArrayList;
import java.util.List;

public class TicTacToe {


    static long zobristValues[][][];



    public static List<Move> getMoves(TicTacToeCell[][] cells) {
        List<Move> moves = new ArrayList<>();

        for (int r = 0; r < cells.length; r++) {
            for (int c = 0; c < cells.length; c++) {
                if (cells[r][c]== TicTacToeCell.Empty)
                    moves.add(new TicTacToeMove(r,c));
            }
        }
        return moves;
    }

    public static boolean isGameOver(TicTacToeCell[][] cells) {
       return hasPerfectRow(cells)|| hasPerfectCol(cells)|| hasPerfectDiagonal(cells)|| isFull(cells);
    }

    private static boolean isFull(TicTacToeCell[][] cells) {
        for (int r = 0; r < cells.length; r++) {
            for (int c = 0; c < cells[r].length; c++) {
                if (cells[r][c]== TicTacToeCell.Empty)
                    return false;
            }
        }
        return true;
    }

    public static boolean winBy(TicTacToeBoard  board, int player) {
        if (TicTacToe.hasPerfectRowOf(board.getBoard(),TicTacToe.playerSymbol(player)))
            return true;

        if (TicTacToe.hasPerfectColOf(board.getBoard(),TicTacToe.playerSymbol(player)))
            return true;

        return TicTacToe.hasPerfectDiagonalOf(board.getBoard(),TicTacToe.playerSymbol(player));
    }

    private static boolean hasPerfectDiagonal(TicTacToeCell[][] cells) {
        return hasPerfectDiagonalOf(cells,TicTacToeCell.Cross) || hasPerfectDiagonalOf(cells,TicTacToeCell.Circle) ;
    }

    public static boolean hasPerfectDiagonalOf(TicTacToeCell[][] cells, TicTacToeCell symbol) {
        boolean perfectDiagonal= true;
        for (int i = 0; i < cells.length; i++) {
            if (cells[i][i]!= symbol)
            {
                perfectDiagonal = false;
                break;
            }
        }
        if (perfectDiagonal)
            return true;
        perfectDiagonal = true;
        for (int i = 0; i < cells.length; i++) {
            if (cells[i][cells.length-i-1]!= symbol)
            {
                perfectDiagonal = false;
                break;
            }
        }
        return perfectDiagonal;
    }

    private static boolean hasPerfectCol(TicTacToeCell[][] cells) {
        return hasPerfectColOf(cells,TicTacToeCell.Cross) || hasPerfectColOf(cells, TicTacToeCell.Circle) ;
    }

    public static boolean hasPerfectColOf(TicTacToeCell[][] cells, TicTacToeCell symbol) {
        boolean perfectCol = false;
        for (int c = 0; c<cells[0].length ; c++) {
            perfectCol = true;
            for (int r = 0; r < cells.length; r++) {
                if (cells[r][c] != symbol) {
                    perfectCol = false;
                    break;
                }
            }
            if (perfectCol)
                return true;
        }
        return false;
    }

    private static boolean hasPerfectRow(TicTacToeCell[][] cells) {
        return hasPerfectRowOf(cells,TicTacToeCell.Cross) || hasPerfectRowOf(cells, TicTacToeCell.Circle) ;
    }

    public static boolean hasPerfectRowOf(TicTacToeCell[][] cells, TicTacToeCell symbol) {

        boolean perfectRow = false;
        for (int r = 0; r <cells.length ; r++) {
            perfectRow = true;
            for (int c = 0; c < cells[r].length; c++) {
                if (cells[r][c] != symbol) {
                    perfectRow = false;
                    break;
                }
            }
            if (perfectRow)
                return true;
        }
        return false;
    }

    // More reusable version of perfect row check, a bit less readable though
    public static boolean hasPerfectRowOfC(TicTacToeCell[][] cells, TicTacToeCell symbol) {

        return   hasPerfectLineOf(cells,0,1, cells.length, new int[]{0,1},symbol);
    }

    public static boolean hasPerfectLineOf(TicTacToeCell[][] cells, int start, int delta, int count, int[] deltas ,TicTacToeCell symbol) {

        boolean perfectLine = false;
        for (int i = start; i <count ; i+=delta) {
            perfectLine = true;
            for (int l = 0; l < cells.length; l++) {
                if (cells[l+ deltas[0]][l+deltas[1]] != symbol) {
                    perfectLine = false;
                    break;
                }
            }
            if (perfectLine)
                return true;
        }
        return false;
    }

    public static TicTacToeCell playerSymbol(int currentPlayer) {
        return currentPlayer==0 ?
                TicTacToeCell.Cross: TicTacToeCell.Circle;
    }

    public static TicTacToeCell[][] initialBoard(int boardSize) {
        TicTacToeCell cells[][] = new TicTacToeCell[boardSize][boardSize];
        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c <boardSize; c++) {
                cells[r][c] = TicTacToeCell.Empty;
            }
        }
        return cells;
    }

    public static int otherPlayer(int player) {
        return (player+1)%2;
    }





    public static long zobristKey(TicTacToeCell[][] cells) {
        if (zobristValues == null)
            initializeZobrist(cells.length);

        long key = 0;

        for (int r = 0; r < cells.length; r++) {
            for (int c = 0; c < cells[r].length; c++) {
                key ^= zobristValues[r][c][ cells[r][c].toInt()];
            }
        }

        return key;
    }

    private static void initializeZobrist(int boardSize) {

        zobristValues = new long[boardSize][boardSize][3];


        for (int r = 0; r < boardSize; r++) {
            for (int c = 0; c < boardSize; c++) {
                for (int i = 0; i < 3; i++) {
                    zobristValues[r][c][i] = RandomUtils.randomLong();
                }
            }
        }
    }

    public static long updateKey(long key, TicTacToeMove m, int oldState, int newState) {
        key ^= zobristValues[m.getRow()][m.getCol()][oldState];
        key ^= zobristValues[m.getRow()][m.getCol()][newState];
        return key;
    }
}
