package chapter5.boardgames.base;

import java.util.List;

public interface Board {

    int playerCount();
    

    List<Move> getMoves();
    Board makeMove(Move m);
    int currentPlayer();
    boolean isGameOver();


    boolean isValid(Move m);

    void perform(Move m);

    Board cloneBoard();

    void init();

    int winner();

    long getKey();
}
