package rl.multiarmbandit;

/**
 * Represents any kind of bandit machines(multiarmed or single armed)
 * That can generate a double reward after each roll.
 */
public interface MultiArmBandit {
    void reset();
    double roll(int arm);
    int armCount();
    void print();
}
