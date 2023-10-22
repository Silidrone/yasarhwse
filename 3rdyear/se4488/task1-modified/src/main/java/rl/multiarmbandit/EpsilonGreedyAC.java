package rl.multiarmbandit;

import java.security.SecureRandom;
import java.util.Random;

/**
 * TODO-1 : Implement a bandit action chooser that selects arms in EpsilonGreedy fashion
 *          according to the given estimated values of each arm
 */
public class EpsilonGreedyAC implements ActionChooser {

    Random rnd = new SecureRandom(); // for random number generation if needed
    GreedyAC greedyAC = new GreedyAC();
    RandomAC randomAC = new RandomAC();

    double epsilon;

    public EpsilonGreedyAC(double epsilon) {
        this.epsilon = epsilon;
    }

    /**
     * TODO-1.1 : Implement epsilon greedy selection in this function.
     *            Note that you can implement other helper functions to be used in this function
     */
    @Override
    public int getAction(State s) {
        return rnd.nextDouble() < epsilon ? randomAC.getAction(s) : greedyAC.getAction(s);
    }

    @Override
    public String getName() {
        return "Epsilon-Greedy";
    }


}
