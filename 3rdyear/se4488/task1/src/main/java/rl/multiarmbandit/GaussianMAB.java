package rl.multiarmbandit;

import java.security.SecureRandom;
import java.util.Random;

/**
 * Represents multiple armed bandit machine
 */
public class GaussianMAB implements MultiArmBandit {

    Random r = new SecureRandom();
    double[] means;

    public GaussianMAB(int armCount) {

        _init(armCount);
    }

    private void _init(int armCount) {
        means = new double[armCount];
        for (int i = 0; i < means.length; i++) {
            means[i] = r.nextGaussian();
        }
    }

    /**
     * Resets the machine with random expected(mean) rewards for each arm
     */
    @Override
    public void reset() {
        _init(means.length);
    }


    @Override
    public double roll(int arm) {
        return means[arm]+ r.nextGaussian();
    }

    @Override
    public int armCount() {
        return means.length;
    }

    /**
     * Prints the expected reward (mean) values for each arm
     */
    @Override
    public void print() {
        for (int i = 0; i < means.length; i++) {
            System.out.println("["+i+"]: "+ means[i]);
        }
    }
}
