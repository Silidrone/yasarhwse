package rl.multiarmbandit;


import java.util.Arrays;

/**
 * * The Constant-Step Size method for value estimation
 */
public class ConstantStepSizeVE implements ValueEstimator {
    double initialEstimation;   // Initial estimations of values for each arm of the bandit
    double[] estimations;       // Current estimation of values for each arm of the bandit

    double stepSize; // The step Size

    public ConstantStepSizeVE(double initialEstimation, double stepSize) {
        this.initialEstimation = initialEstimation;
        this.stepSize = stepSize;
    }

    @Override
    public double[] getValues() {
        return estimations;
    }

    /**
     * A new reward (reward) is observed for the given arm (a).
     * Update the current estimation accordingly.
     * Note that the updateTime of the given arm should be incremented
     */
    @Override
    public void feed(int a, double reward) {
        estimations[a] = estimations[a] + stepSize * (reward - estimations[a]);
    }

    /**
     * Initializes the estimations and update times.
     *
     * @param armCount
     */
    @Override
    public void init(int armCount) {
        estimations = new double[armCount];
        Arrays.fill(estimations, initialEstimation);
    }

    @Override
    public String getName() {
        return "Constant Step Size-" + stepSize;
    }
}
