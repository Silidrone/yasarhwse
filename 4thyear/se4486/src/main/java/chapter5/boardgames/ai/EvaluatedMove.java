package chapter5.boardgames.ai;


import chapter5.boardgames.base.Move;

public class EvaluatedMove {

    Move move;
    double score;

    public EvaluatedMove(Move move, double score) {
        this.move = move;
        this.score = score;
    }

    public Move getMove() {
        return move;
    }

    public double getScore() {
        return score;
    }
}
