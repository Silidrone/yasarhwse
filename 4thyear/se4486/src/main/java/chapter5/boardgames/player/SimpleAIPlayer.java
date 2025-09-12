package chapter5.boardgames.player;


import chapter5.boardgames.ai.BoardAI;
import chapter5.boardgames.ai.BoardEvaluator;
import chapter5.boardgames.ai.BoardEvaluatorWithTT;
import chapter5.boardgames.base.Board;
import chapter5.boardgames.base.Move;
import com.google.common.util.concurrent.SimpleTimeLimiter;
import com.google.common.util.concurrent.TimeLimiter;
import org.newdawn.slick.Image;

import java.util.concurrent.*;

public class SimpleAIPlayer extends AbstractPlayer implements AIPlayer {

    public final static int MAX_THINK_TIME = 100;
    public final static int MAX_TIMEOUT = 2;

    BoardAI ai;
    BoardEvaluator evaluator;



    int maxTimePerMove;
    private boolean calculating = false;
    private int timeOutCount;

    public SimpleAIPlayer(String name, BoardAI ai,BoardEvaluator evaluator, int maxTimePerMove) {
        super(name, PlayerType.AIPlayer);
        this.ai = ai;
        this.evaluator = evaluator;
        this.maxTimePerMove = maxTimePerMove;
    }


    @Override
    public void calculateMove(Board board) {

        if (timeOutCount>MAX_TIMEOUT)
        {
            System.out.println("MAX_TIMEOUT is reached. Returning the first move!");
            if (!board.isGameOver())
                move = board.getMoves().get(0);
            return;
        }
        if (calculating)
            return;

        calculating = true;
        Runnable aiJob= new Runnable() {
            @Override
            public void run() {
                TimeLimiter timeLimiter = SimpleTimeLimiter.create(Executors.newSingleThreadExecutor());


                Callable<Move> timedAIJob = () -> ai.getBestMove(board, evaluator);

                try {
                    move = timeLimiter.callWithTimeout(timedAIJob,maxTimePerMove, TimeUnit.SECONDS);
                } catch (TimeoutException e) {
                    System.out.println("TIMEOUT! First move will be played..");
                    move = board.getMoves().get(0);
                    timeOutCount++;

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                calculating = false;
                if (evaluator instanceof BoardEvaluatorWithTT)
                {
                    System.out.println("EVAL_HIT:"+((BoardEvaluatorWithTT)evaluator).getHitCount());
                }
            }


        };

        Thread aiThread = new Thread(aiJob);
        aiThread.start();



    }


    @Override
    public Image getLogo() {
        return null;
    }

    @Override
    public void init(Board board) {
        timeOutCount =0;
        calculating =false;
    }
}
