package rl.methods.dp;


import rl.base.*;

import java.util.*;

/**
 * TODO-2 : Implement Policy Iteration
 * <p>
 * The Policy Iteration algorithm covered in PART-IV
 * <p>
 * See  PolicyIteration.png for the pseudocode
 */
public class PolicyIteration implements DynamicProgramming {

    final double GAMMA = 0.99;      // GAMMA of the algorithm. See the pseudocode
    final double EPSILON = 1e-7;    // EPSILON of the algorithm. See the pseudocode
    final double INITIAL_V = 0.0;   // Initial Values for the V(s) for all states

    HashMap<RLState, Double> v;      // V-Table
    HashMap<RLState, Double> vNext;  // V-Table to be calculated in the next iteration

    DeterministicPolicy pi;         // The Policy to be established according to V-Table

    // Renderers for visualizing  the outputs of the algorithm
    private RLValueRenderer valueRenderer;
    private RLPolicyRenderer policyRenderer;

    public PolicyIteration(DeterministicPolicy pi) {
        this.pi = pi;
    }

    public void setValueRenderer(RLValueRenderer valueRenderer) {
        this.valueRenderer = valueRenderer;
    }

    public void setPolicyRenderer(RLPolicyRenderer policyRenderer) {
        this.policyRenderer = policyRenderer;
    }

    /**
     * Initializes the algorithm variables (V-Tables)
     *
     * @param environment
     */
    private void init(EnvironmentModel environment) {
        vNext = new HashMap<>();
        v = new HashMap<>();

        for (RLState state : environment.getStates())
            vNext.put(state, INITIAL_V);

        for (RLState state : environment.getStates())
            pi.setAction(state, environment.getActions(state).get(0));
    }

    /**
     * `TODO-2.1 : Implement Policy Iteration algorithm. See pesudocode in PolicyIteration.png
     *       HINT: See the {@link ValueIteration} implementation to have an idea
     *
     * @param environment
     * @param iteration
     */
    public void perform(EnvironmentModel environment, int iteration) {
        init(environment);

        for (int i = 0; i < iteration && !(policyEvaluation(environment) < EPSILON && policyImprovement(environment)); i++);

        printResults(environment);
    }

    private double policyEvaluation(EnvironmentModel environment) {
        double delta;
        v.putAll(vNext);
        delta = 0;

        for (RLState state : environment.getStates()) {
            update(environment, state);
            delta = Math.max(delta, Math.abs(v.get(state) - vNext.get(state)));
        }
        return delta;
    }

    private boolean policyImprovement(EnvironmentModel environment) {
        boolean policyStable = true;
        for (RLState state : environment.getStates()) {
            RLAction oldAction = pi.getAction(state);
            List<Double> returns = getReturns(environment, state);
            pi.setAction(state, environment.getActions(state).get(returns.indexOf(Collections.max(returns))));

            if (oldAction != pi.getAction(state)) {
                policyStable = false;
            }
        }

        return policyStable;
    }

    private void update(EnvironmentModel environment, RLState state) {
        double newValue = 0;
        if(!environment.isTerminal(state)){
            RLAction action = pi.getAction(state);
            for (RLTransition transition : environment.getTransitions(state, action)) {
                newValue += transition.prob() * (transition.reward() + GAMMA * v.get(transition.to()));
            }
        }
        vNext.put(state,newValue);
    }

    private List<Double> getReturns(EnvironmentModel environment, RLState state) {
        List<Double> actionValues = new ArrayList<>();
        for (RLAction action : environment.getActions(state)) {
            double val = 0;
            for (RLTransition transition : environment.getTransitions(state, action)) {
                val += transition.prob() * (transition.reward() + GAMMA * v.get(transition.to()));
            }
            actionValues.add(val);
        }

        return actionValues;
    }

    private void printResults(EnvironmentModel model) {
        if (valueRenderer != null)
            valueRenderer.renderValues(model, v);

        if (policyRenderer != null)
            policyRenderer.renderPolicy(model, pi);
    }
}
