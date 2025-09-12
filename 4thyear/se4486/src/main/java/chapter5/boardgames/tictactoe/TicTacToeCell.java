package chapter5.boardgames.tictactoe;

public enum TicTacToeCell {
    Cross, Circle ,Empty;

    public int toInt() {
        switch (this)
        {
            case Cross:return 0;
            case Circle:return 1;
            case Empty:return 2;
        }
        return 0;
    }
}
