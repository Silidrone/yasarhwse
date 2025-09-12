package chapter5.boardgames.ai;

import chapter5.boardgames.base.Board;
import chapter5.boardgames.base.Move;

import java.util.HashMap;

public class BoardEvaluatorWithTT implements BoardEvaluator{
    BoardEvaluator evaluator;
    HashMap<Long, Double> table;
    long hitCount;

    public BoardEvaluatorWithTT(BoardEvaluator evaluator) {
        this.evaluator = evaluator;
        table = new HashMap<>();
        hitCount=0;
    }

    @Override
    public double evaluate(Board board, int player) {

        if (table.containsKey(board.getKey()))
        {
            double score = table.get(board.getKey());
            hitCount++;
            //if (hitCount++%10==0)
            //    System.out.println("EVAL-HIT:"+ hitCount);
            return score;
        }

        double score = evaluator.evaluate(board,player);
        table.put(board.getKey(), score);

        return score;
    }

    public long getHitCount() {
        return hitCount;
    }
}
