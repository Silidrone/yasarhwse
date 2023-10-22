import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import rl.multiarmbandit.*;

import java.io.IOException;


/**
 * TODO-5 : Implement the following test functions as documented
 */
public class TestBanditExperiment {

    /**
     * TODO-5.1 : Implement the test function according to the documentation below
     *      Create a simple experiment using SimpleExperiment as follows:
     *      - The Bandit machine is a Gaussion MAB with 10 arms.
     *      - The Bandit player is a player with constant action chooser of action 3.
     *      - Save the resulting chart to "RewardDistribution-3.jpeg"
     *      - Perform the eperiment with 1000 rolls
     *
     */
    @Test
    void testRewardDistribution() throws IOException {

        /*your code here*/
        MultiArmBandit bandit = new GaussianMAB(10);
        BanditPlayer player= new RLBanditPlayer("Constantine",new DummyVE(10),new ConstantAC(3));
        BanditExperiment experiment = new SimpleExperiment(bandit,player,"./data/out/RewardDistribution-3.jpeg");
        experiment.performExperiment(1000);
    }

    /**
     * TODO-5.2 : Implement the test function according to the documentation below
     *      Create a comparison  experiment using ComparisonExperiment as follows:
     *      - The Bandit machine is a Gaussion MAB with 10 arms.
     *      - The 1st Bandit player is a player with constant action chooser of action 3.
     *      - The 2nd Bandit player is a player with random action chooser
     *      - Save the resulting chart to "RandomvsConstant.jpeg"
     *      - Perform the experiment with 2000 independent runs each with 1000 rolls
     */
    @Test
    void testRandomvsConstant() throws IOException {
        /*your code here*/
        MultiArmBandit bandit = new GaussianMAB(10);
        BanditPlayer cPlayer= new RLBanditPlayer("Constantine",new DummyVE(10),new ConstantAC(3));
        BanditPlayer rPlayer= new RLBanditPlayer("Randy",new DummyVE(10),new RandomAC());
        ComparisonExperiment experiment = new ComparisonExperiment(bandit,2000,"./data/out/RandomvsConstant.jpeg");
        experiment.addPlayer(cPlayer);
        experiment.addPlayer(rPlayer);

        experiment.performExperiment(1000);
    }

    /**
     * TODO-5.3 : Implement the test function according to the documentation below
     *      Perform the same experiment described in the previous test case with additional
     *      player that has greedy action chooser and Constant Step Size (step size= 0.5) value estimator
     *      - Save the resulting chart to "RandomvsConstantvsGreedy.jpeg"
     */
    @Test
    void testRandomvsConstantvsGreedy() throws IOException {
        /*your code here*/
        MultiArmBandit bandit = new GaussianMAB(10);
        BanditPlayer cPlayer= new RLBanditPlayer("Constantine",new DummyVE(10),new ConstantAC(3));
        BanditPlayer rPlayer= new RLBanditPlayer("Randy",new DummyVE(10),new RandomAC());
        BanditPlayer gPlayer= new RLBanditPlayer("Greg",new ConstantStepSizeVE(0.0,0.5),new GreedyAC());
        ComparisonExperiment experiment = new ComparisonExperiment(bandit,2000,"./data/out/RandomvsConstantvsGreedy.jpeg");
        experiment.addPlayer(cPlayer);
        experiment.addPlayer(rPlayer);
        experiment.addPlayer(gPlayer);

        experiment.performExperiment(1000);
    }



}
