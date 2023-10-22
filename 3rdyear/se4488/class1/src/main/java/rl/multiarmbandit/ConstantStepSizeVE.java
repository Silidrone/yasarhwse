package rl.multiarmbandit;

import java.util.Arrays;

/**
 * TODO-3 : Implement Constant-Step Size method for value estimation
 *          Remember in Constant SS the update rule is:
 *          Q_n+1(a) = Q_n(a)  + Step_Size * ( Reward - Q_n(a))
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
     * TODO-3.1 : A new reward (reward) is observed for the given arm (a).
     *            Update the current estimation accordingly.
     *
     * @param a
     * @param reward
     */
    @Override
    public void feed(int a, double reward) {

        /* your code here*/
        //   Q_n+1(a) = Q_n(a)  + Step_Size * ( Reward - Q_n(a))
        estimations[a] = estimations[a] + stepSize * (reward-estimations[a]);

    }

    /**
     * Initializes the estimations and update times.
     * @param armCount
     */
    @Override
    public void init(int armCount) {
        estimations = new double[armCount];
        Arrays.fill(estimations, initialEstimation);
    }

    @Override
    public String getName() {
        return "Constant Step Size-"+stepSize;
    }
}
