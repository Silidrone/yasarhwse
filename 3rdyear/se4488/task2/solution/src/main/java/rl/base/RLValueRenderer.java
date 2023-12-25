package rl.base;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents the visualizers for RL Value Functions
 */
public interface RLValueRenderer {

    default void renderValues(EnvironmentModel model, HashMap<RLState, Double> v)
    {
        renderValues(v);
    }
    void renderValues(Map<RLState, Double> v);
}
