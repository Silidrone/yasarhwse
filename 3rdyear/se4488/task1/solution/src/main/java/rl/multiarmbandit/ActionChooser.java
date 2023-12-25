package rl.multiarmbandit;

public interface ActionChooser {
    int getAction(double[] values);
    String getName();
}
