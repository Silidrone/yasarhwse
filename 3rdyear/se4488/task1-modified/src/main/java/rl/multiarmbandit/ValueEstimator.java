package rl.multiarmbandit;

public interface ValueEstimator {
    double[] getValues();

    void feed(int a, int[] updateTimes, long timeSteps, double reward);

    void init(int armCount);

    String getName();

}
