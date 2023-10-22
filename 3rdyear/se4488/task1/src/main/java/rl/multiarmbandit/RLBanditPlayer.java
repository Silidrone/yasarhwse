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

    public RLBanditPlayer(String name, ValueEstimator valueEstimator, ActionChooser actionChooser) {
        this.valueEstimator = valueEstimator;
        this.actionChooser = actionChooser;
        this.name = name;
    }

    @Override
    public int play() {
        return actionChooser.getAction(valueEstimator.getValues());
    }

    @Override
    public void feedReward(int a, double reward) {
        valueEstimator.feed(a, reward);
        totalReward += reward;
    }

    @Override
    public void init(int armCount) {
        totalReward = 0;
        valueEstimator.init(armCount);
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
