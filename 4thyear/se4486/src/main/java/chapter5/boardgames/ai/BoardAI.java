package chapter5.boardgames.ai;

import chapter5.boardgames.base.Board;
import chapter5.boardgames.base.Move;

public interface BoardAI {
    Move getBestMove(Board board, BoardEvaluator evaluator);
}
