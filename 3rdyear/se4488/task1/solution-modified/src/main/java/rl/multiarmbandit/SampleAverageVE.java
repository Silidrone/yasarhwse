package rl.multiarmbandit;

import java.util.Arrays;

/**
 * TODO-2 : Implement sample-average method for value estimation
 *          See the description in your textbook.
 *          Hint: It will be similar to ConstantStepSizeVE except here
 *               the update times are used
 */
public class SampleAverageVE implements ValueEstimator {

    double initialEstimation;   // Initial estimations of values for each arm of the bandit
    double[] estimations;       // Current estimation of values for each arm of the bandit

    public SampleAverageVE(double initialEstimation) {
        this.initialEstimation = initialEstimation;
    }

    @Override
    public double[] getValues() {
        return estimations;
    }

    /**
     * TODO-2.1 : A new reward (reward) is observed for the given arm (a).
     *          Update the current estimation accordingly.
     *          Note that the updateTime of the given arm should be incremented
     */
    @Override
    public void feed(int a, int[] updateTimes, long timeSteps, double reward) {
        estimations[a] = estimations[a] + (1.0 / updateTimes[a]) * (reward - estimations[a]);
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
        return "Sample Average";
    }
}
