package rl;

import rl.base.DeterministicPolicy;
import rl.base.RLAction;
import rl.base.RLState;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Represents a deterministic policy by storing actions in a hash map for each state
 */
public class MapBasedPolicy implements DeterministicPolicy {

    Map<RLState, RLAction> pi;

    public MapBasedPolicy() {
        pi = new HashMap<>();
    }

    @Override
    public void setAction(RLState state, RLAction action) {
        pi.put(state,action);
    }

    @Override
    public boolean containsActionFor(RLState state) {
        return pi.containsKey(state);
    }

    @Override
    public Set<RLState> getStates() {
        return pi.keySet();
    }

    @Override
    public RLAction getAction(RLState s) {
        return pi.get(s);
    }


}
