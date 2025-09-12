package chapter5.boardgames.ai;

import chapter5.boardgames.base.Board;

public interface BoardEvaluator {
    public double evaluate(Board board, int player);
}
