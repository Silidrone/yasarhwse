package rl.multiarmbandit;

import java.security.SecureRandom;
import java.util.Random;

/**
 * A random action chooser than selects arms randomly without using the
 * estimated values of the arms
 */
public class RandomAC implements ActionChooser {
    Random r = new SecureRandom();

    @Override
    public int getAction(State s) {
        return r.nextInt(s.values().length);
    }

    @Override
    public String getName() {
        return "Random";
    }
}
