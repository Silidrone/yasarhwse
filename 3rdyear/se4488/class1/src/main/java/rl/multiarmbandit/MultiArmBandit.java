package rl.multiarmbandit;

/**
 * Represents any kind of bandit machines(multiarmed or single armed)
 * That can generate a double reward after each roll.
 */
public interface MultiArmBandit {
    // Resets the machine
    void reset();

    // Rolls the given arm of the machine and generate reward
    double roll(int arm);

    // The number of arms
    int armCount();

    // Prints the machine info such as the q*() for each arm
    void print();
}
