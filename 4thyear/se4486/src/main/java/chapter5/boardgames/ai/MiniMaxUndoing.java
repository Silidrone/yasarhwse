package chapter5.boardgames.ai;





import chapter5.boardgames.base.Board;
import chapter5.boardgames.base.Move;
import chapter5.boardgames.base.Undoable;
import utils.ThreadUtils;

import java.util.List;

public class MiniMaxUndoing implements BoardAI {
    int maxDepth;

    public MiniMaxUndoing(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    @Override
    public Move getBestMove(Board board, BoardEvaluator evaluator) {

        assert board instanceof Undoable;

        EvaluatedMove em =getBestMove(board,evaluator, board.currentPlayer(),0);

        return em.getMove();
    }

    private EvaluatedMove getBestMove(Board board, BoardEvaluator evaluator, int player, int depth) {


        if (board.isGameOver() || depth>maxDepth)
        {
            double score = evaluator.evaluate(board,player);
            return new EvaluatedMove(null,score);
        }

        List<Move> moves= board.getMoves();
        boolean maximizing = player== board.currentPlayer();
        boolean minimizing = !maximizing;

        Move bestMove = null;
        double bestScore = (maximizing) ? - Double.MAX_VALUE: Double.MAX_VALUE;

        for (Move m:moves)
        {
            board.perform(m);
            EvaluatedMove opponentMove = getBestMove(board,evaluator,player,depth+1);

            if ( ( maximizing && bestScore<opponentMove.getScore())||
                 ( minimizing && bestScore>opponentMove.getScore()))
            {
                bestMove = m;
                bestScore = opponentMove.getScore();
            }
            ((Undoable)board).undo(m);

        }

        return new EvaluatedMove(bestMove,bestScore);
    }
}
