package rl.base;

import java.util.Set;

/**
 * Represents a deterministic RL Policy
 */
public interface DeterministicPolicy extends RLPolicy {
    void setAction(RLState state, RLAction action);
    boolean containsActionFor(RLState state);
    Set<RLState> getStates();

}
