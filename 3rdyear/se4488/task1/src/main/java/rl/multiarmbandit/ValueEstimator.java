package rl.multiarmbandit;

public interface ValueEstimator {
    double[] getValues();

    void feed(int a, double reward);

    void init(int armCount);

    String getName();

}
