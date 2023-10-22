package rl.multiarmbandit;

/**
 * A dummy value estimator that does nothing when a new action-reward is fed.
 * and returns always a value array filled with 0's
 */
public class DummyVE implements ValueEstimator{


    int armCount;

    public DummyVE(int armCount) {
        this.armCount = armCount;
    }

    @Override
    public double[] getValues() {
        return new double[armCount];
    }

    @Override
    public void feed(int a, double reward) {

    }

    @Override
    public void init(int armCount) {
        this.armCount=armCount;
    }

    @Override
    public String getName() {
        return "Dummy-VE";
    }
}
