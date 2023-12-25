package rl.base;

import java.util.List;

/**
 * Represents a model of MDP environment
 */
public interface EnvironmentModel {
    List<RLTransition> getTransitions(RLState state, RLAction action);
    List<RLState> getStates();
    List<RLAction> getActions(RLState state);
    boolean isTerminal(RLState state);
}
