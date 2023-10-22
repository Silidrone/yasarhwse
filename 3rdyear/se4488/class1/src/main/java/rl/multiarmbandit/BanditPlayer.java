package rl.multiarmbandit;

/**
 * Represents the player of multiarm-bandit machine.
 */
public interface BanditPlayer {
    // Returns the arm to be rolled
    int play();
    // Updates the object with observed reward
    void feedReward(int a, double bandit);
    // Initializes the player for a given number of arms
    void init(int armCount);

    // Prints the statistics achieved so far
    void printStats();

    // The name of the player
    String getName();
}
