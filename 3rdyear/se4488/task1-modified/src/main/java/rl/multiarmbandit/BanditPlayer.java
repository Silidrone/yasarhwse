package rl.multiarmbandit;

/**
 * Represents the player of multiarm-bandit machine.
 */
public interface BanditPlayer {
    int play();
    void feedReward(int a, double bandit);
    void init(int armCount);

    void printStats();

    String getName();
}
