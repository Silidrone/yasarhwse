package rl.multiarmbandit;

import java.security.SecureRandom;
import java.util.Random;

/**
 * TODO-1 : Implement a random action chooser
 *          A random action chooser selects arms randomly without using the
 *          estimated values of the arms.
 *          Hint: See Constant AC to have an idea
 */
public class RandomAC implements ActionChooser {

    Random r = new SecureRandom();

    /**
     * TODO-1.1 : Implement random action selection in this function.

     * @param values
     * @return
     */
    @Override
    public int getAction(double[] values) {
        int action = 0; // Assign this properly


        /* your code here*/
        action = r.nextInt(values.length);


        return action;
    }

    @Override
    public String getName() {
        return "Random";
    }

    /**
     * TODO- 1.2 : Write a simple test code as follows
     *      - Create an RLBanditPlaeyer with RandomAC and DummyVE
     *      - Create a Gaussian bandit machine with 3 arms
     *      - print the machine info
     *      - Make the player roll the machine 10 times
     *      - At each roll print :
     *            -the selected action
     *            -the generated reward
     *      - Print statistics of the player
     *
     * @param args
     */
    public static void main(String[] args) {
        RLBanditPlayer player = new RLBanditPlayer("DummyPlayer",new DummyVE(3),new RandomAC());
        MultiArmBandit machine = new GaussianMAB(3);
        machine.print();

        for (int i = 0; i < 10; i++) {
            int arm = player.play();
            double reward = machine.roll(arm);
            player.feedReward(arm,reward);
            System.out.println("Action:" + arm + " Reward: " + reward);
        }

        player.printStats();

    }
}
