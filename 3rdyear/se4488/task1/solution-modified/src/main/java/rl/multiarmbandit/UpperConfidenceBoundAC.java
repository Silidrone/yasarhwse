package rl.multiarmbandit;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.stream.IntStream;

// weird class name
public class UpperConfidenceBoundAC implements ActionChooser {
    double c;

    public UpperConfidenceBoundAC(double c) {
        this.c = c;
    }

    @Override
    public int getAction(State s) {
        double max_value = -1;
        int max_index = -1;
        for (int i = 0; i < s.values().length; i++) {
            double ucb = c * Math.sqrt(Math.log(s.timeSteps()) / s.updateTimes()[i]);
            double value = s.values()[i] + ucb;
            if (value > max_value) {
                max_value = value;
                max_index = i;
            }
        }

        return max_index;
    }

    @Override
    public String getName() {
        return "UCB";
    }
}
