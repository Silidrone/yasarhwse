package chapter5.boardgames.ai;


import chapter5.boardgames.base.Board;
import chapter5.boardgames.base.Move;

import java.util.HashMap;

public class BoardAIWithTT implements BoardAI {

    BoardAI boardAI;
    HashMap<Long,Move> table;
    long hitCount;

    public BoardAIWithTT(BoardAI boardAI) {
        this.boardAI = boardAI;
        table = new HashMap<>();
        hitCount =0;
    }

    @Override
    public Move getBestMove(Board board, BoardEvaluator evaluator) {
        if (table.containsKey(board.getKey()))
        {
            Move m = table.get(board.getKey());
            if (hitCount++%10==0)
                System.out.println("HIT:"+ hitCount);
            return m;
        }

        Move m = boardAI.getBestMove(board,evaluator);

        table.put(board.getKey(),m);
        return m;
    }
}
