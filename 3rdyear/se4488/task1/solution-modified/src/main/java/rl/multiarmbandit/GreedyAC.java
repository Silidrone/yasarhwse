package rl.multiarmbandit;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * A bandit action chooser that selects arms in Greedy fashion
 * according to the given estimated values of each arm
 */
public class GreedyAC implements ActionChooser {

    /**
     * Greedy action selection
     */
    @Override
    public int getAction(State s) {
        double max = Arrays.stream(s.values()).max().getAsDouble();
        int[] maxValues = IntStream.range(0, s.values().length).filter(x -> s.values()[x] == max).toArray();

        return maxValues[new SecureRandom().nextInt(maxValues.length)];
    }

    @Override
    public String getName() {
        return "Greedy";
    }
}
