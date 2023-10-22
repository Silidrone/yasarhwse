package rl.multiarmbandit;

import java.util.Arrays;
import java.util.Objects;

/**
 * Plays the multiarm-bandit machine to maximize the reward by using simple reinforcement learning methods (Value estimation)
 * Those methods are supposed to be implemented as {@link ValueEstimator} and {@link ActionChooser} instances
 */
public class RLBanditPlayer implements BanditPlayer {
    private double totalReward;
    private final String name;
    ValueEstimator valueEstimator;
    ActionChooser actionChooser;
    int[] updateTimes;
    long timeSteps;

    public RLBanditPlayer(String name, ValueEstimator valueEstimator, ActionChooser actionChooser) {
        this.valueEstimator = valueEstimator;
        this.actionChooser = actionChooser;
        this.name = name;
    }

    @Override
    public int play() {
        return actionChooser.getAction(new State(valueEstimator.getValues(), timeSteps, updateTimes));
    }

    @Override
    public void feedReward(int a, double reward) {
        valueEstimator.feed(a, updateTimes, timeSteps, reward);
        totalReward += reward;
        updateTimes[a]++;
        timeSteps++;
    }

    @Override
    public void init(int armCount) {
        totalReward = 0;
        valueEstimator.init(armCount);
        timeSteps = 1;
        updateTimes = new int[armCount];
        Arrays.fill(updateTimes, 1);
    }

    @Override
    public void printStats() {
        System.out.println(" TR: " + totalReward);
    }

    @Override
    public String getName() {
        return name + "(" + actionChooser.getName() + "-" + valueEstimator.getName() + ")";
    }
}
