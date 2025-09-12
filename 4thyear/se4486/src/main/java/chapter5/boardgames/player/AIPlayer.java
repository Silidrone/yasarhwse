package chapter5.boardgames.player;

import chapter5.boardgames.base.Board;

public interface AIPlayer extends Player {
    void calculateMove(Board b);
}
