package rl.multiarmbandit;

import java.security.SecureRandom;
import java.util.Random;

/**
 *  A constant action chooser selects always the same arm without using the
 *  estimated values of the arms
 */
public class ConstantAC implements ActionChooser {

    int action; // The action that is always selected

    public ConstantAC(int action) {
        this.action = action;
    }

    @Override
    public int getAction(double[] values) {
        return action;
    }

    @Override
    public String getName() {
        return "Constant";
    }
}
