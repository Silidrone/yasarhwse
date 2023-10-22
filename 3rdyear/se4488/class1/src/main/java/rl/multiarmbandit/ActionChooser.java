package rl.multiarmbandit;

/**
 * Represents action selection algorithms
 */
public interface ActionChooser {
    int getAction(double[] values);
    String getName();
}
