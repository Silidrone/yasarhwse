package chapter5.boardgames.ai;



import chapter5.boardgames.base.Board;
import chapter5.boardgames.base.Move;

import java.util.List;

public class AlphaBetaPruning implements BoardAI {
	int maxDepth;


	public AlphaBetaPruning(int maxDepth) {
		this.maxDepth = maxDepth;
	}

	@Override
	public Move getBestMove(Board board, BoardEvaluator evaluator) {

		EvaluatedMove em = getBestMove(board, evaluator, board.currentPlayer(), 0, -Double.MAX_VALUE, Double.MAX_VALUE);

		return em.getMove();
	}

	private EvaluatedMove getBestMove(Board board, BoardEvaluator evaluator,int player, int depth, double alpha,
			double beta) {
		if (board.isGameOver() || depth > maxDepth) {
			double score = evaluator.evaluate(board, player);
			return new EvaluatedMove(null, score);
		}

		EvaluatedMove bestMove = (player== board.currentPlayer()) ?
					alphaIteration(board,evaluator,player,depth,alpha,beta) :
					betaIteration(board,evaluator,player,depth,alpha,beta);

		return bestMove;
	}

	private EvaluatedMove betaIteration(Board board, BoardEvaluator evaluator, int player, int depth, double alpha, double beta) {
		List<Move> moves = board.getMoves();
		EvaluatedMove bestMove = new EvaluatedMove(null, Double.MAX_VALUE);
		for (Move m : moves) {
			Board newBoard = board.makeMove(m);
			EvaluatedMove value = getBestMove(newBoard, evaluator, player, depth + 1, alpha, beta);

			if (value.score < bestMove.score) {
				bestMove = new EvaluatedMove(m, value.score);
			}

			beta = Math.min(beta, bestMove.score);

			if (beta <= alpha) {
				break;
			}
		}

		return bestMove;
	}

	private EvaluatedMove alphaIteration(Board board, BoardEvaluator evaluator, int player,int depth, double alpha, double beta )
	{
		List<Move> moves = board.getMoves();
		EvaluatedMove bestMove = new EvaluatedMove(null, -Double.MAX_VALUE);

		for (Move m : moves) {
			Board newBoard = board.makeMove(m);
			EvaluatedMove value = getBestMove(newBoard, evaluator, player, depth + 1, alpha, beta);


			if (value.score > bestMove.score) {
				bestMove = new EvaluatedMove(m, value.score);
			}

			alpha = Math.max(alpha, bestMove.score);

			if (beta <= alpha) {
				break;
			}
		}

		return bestMove;
	}
}
