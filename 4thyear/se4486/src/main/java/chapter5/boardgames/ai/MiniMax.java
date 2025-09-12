package chapter5.boardgames.ai;





import chapter5.boardgames.base.Board;
import chapter5.boardgames.base.Move;
import utils.ThreadUtils;

import java.util.List;

public class MiniMax implements BoardAI {
    int maxDepth;

    public MiniMax(int maxDepth) {
        this.maxDepth = maxDepth;
    }

    @Override
    public Move getBestMove(Board board, BoardEvaluator evaluator) {

        EvaluatedMove em =getBestMove(board,evaluator, board.currentPlayer(),0);

        return em.getMove();
    }

    /**
     *  TODO-1: Implement minimax function as described in the lecture
     * @param board
     * @param evaluator
     * @param player
     * @param depth
     * @return
     */
    private EvaluatedMove getBestMove(Board board, BoardEvaluator evaluator, int player, int depth) {

        if ( board.isGameOver() || depth>= maxDepth)
        {
            double score = evaluator.evaluate(board,player);
            return new EvaluatedMove(null,score);
        }

        boolean  maximizing =  board.currentPlayer() == player;
        boolean  minimizing = !maximizing;

        EvaluatedMove bestMove = new EvaluatedMove(null, maximizing ? -Double.MAX_VALUE: Double.MAX_VALUE);

        List<Move> moves = board.getMoves();

        for (Move m: moves)
        {
            Board newBoard =board.makeMove(m);
            EvaluatedMove opponentMove = getBestMove(newBoard,evaluator,player,depth+1);

            if ( (maximizing && (bestMove.getScore() <opponentMove.getScore())) ||
               (minimizing && (bestMove.getScore() > opponentMove.getScore())) )
            {
                bestMove = new EvaluatedMove(m, opponentMove.getScore());
            }

        }


        return bestMove;

    }
}
