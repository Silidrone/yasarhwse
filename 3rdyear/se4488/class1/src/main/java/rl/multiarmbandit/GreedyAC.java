package rl.multiarmbandit;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 * TODO-2 : Implement a bandit action chooser that selects arms in Greedy fashion
 *          according to the given estimated values of each arm
 */
public class GreedyAC implements ActionChooser {

    /**
     * TODO-2.1 : Implement greedy selection in this function.

     * @param values
     * @return
     */
    @Override
    public int getAction(double[] values) {
        int action =0; // Assign this properly


        /*your code here*/
        for (int a = 1; a < values.length; a++) {
            if ( values[a]>values[action])
                action= a;
        }


        return action;
    }

    @Override
    public String getName() {
        return "Greedy";
    }
}
