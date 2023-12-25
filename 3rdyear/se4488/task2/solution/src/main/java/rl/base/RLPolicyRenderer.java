package rl.base;

/**
 * Represents the visualizers for RL Policies
 */
public interface RLPolicyRenderer {
    default void renderPolicy(EnvironmentModel model, RLPolicy pi)
    {
        renderPolicy(pi);
    }
    void renderPolicy( RLPolicy pi);
}
