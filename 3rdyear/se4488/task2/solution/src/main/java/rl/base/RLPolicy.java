package rl.base;

/**
 * Represents the policy of an RL agent.
 *
 */
public interface RLPolicy {
    RLAction getAction(RLState s);
}
