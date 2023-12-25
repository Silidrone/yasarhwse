package rl.base;

/**
 * Represents transitions following an action in an MDP
 */
public interface RLTransition {
    RLState to();
    double prob();

    double reward();
}
