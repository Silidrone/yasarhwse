package rl.multiarmbandit;

public interface ActionChooser {
    int getAction(State state);
    String getName();
}
