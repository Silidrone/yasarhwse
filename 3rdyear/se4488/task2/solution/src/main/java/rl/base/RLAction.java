package rl.base;

/**
 * Represents an action of an MDP environment
 */
public interface RLAction {
    String name();
    int hashCode();
    boolean equals(Object o);
}
