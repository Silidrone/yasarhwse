package rl.methods.dp;


import rl.base.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The Value Iteration algorithm covered in PART-IV
 *
 * See ValueIteration.png for the pseudocode
 *
 * Understand this implementation and use similar approach to implement PolicyIteration
 */
public class ValueIteration implements DynamicProgramming{

    final double GAMMA = 0.99;      // GAMMA of the algorithm. See the pseudocode
    final double EPSILON = 1e-7;    // EPSILON of the algorithm. See the pseudocode
    final double INITIAL_V = 0.0;   // Initial Values for the V(s) for all states

    HashMap<RLState,Double> v;      // V-Table
    HashMap<RLState,Double> vNext;  // V-Table to be calculated in the next iteration

    DeterministicPolicy policy;     // The Policy to be established according to V-Table

    // Renderers for visualizing  the outputs of the algorithm
    private RLValueRenderer valueRenderer;
    private RLPolicyRenderer policyRenderer;


    public ValueIteration(DeterministicPolicy policy) {
        this.policy = policy;
    }

    public void setValueRenderer(RLValueRenderer valueRenderer) {
        this.valueRenderer = valueRenderer;
    }

    public void setPolicyRenderer(RLPolicyRenderer policyRenderer) {
        this.policyRenderer = policyRenderer;
    }


    /**
     * Initializes the algorithm variables (V-Tables)
     * @param model
     */
    private void init(EnvironmentModel model) {
        vNext = new HashMap<>();
        v = new HashMap<>();

        for (RLState state:model.getStates())
            vNext.put(state,INITIAL_V);
    }

    public void perform(EnvironmentModel model, int iteration)
    {
        init(model);
        double delta =0;

        for (int n = 0; n < iteration; n++) {
            v.putAll(vNext);
            delta = 0;

            for (RLState state: model.getStates())
            {
                update(model,state);
                double err = Math.abs(vNext.get(state)-v.get(state));
                if (err>delta)
                    delta=err;
            }
            if (delta<EPSILON)
                break;
        }
        printResults(model);
    }

    private void printResults(EnvironmentModel model) {
        if (valueRenderer != null)
            valueRenderer.renderValues(model,v);

        if (policyRenderer != null)
            policyRenderer.renderPolicy(model,policy);
    }

    List<Double> actionValues(EnvironmentModel environment, RLState state)
    {
        List<Double> actionValues=new ArrayList<>();
        for (RLAction action:environment.getActions(state))
        {
            double val =0;
            for(RLTransition transition: environment.getTransitions(state,action))
            {
                val += transition.prob()*transition.reward()+ transition.prob()*v.get(transition.to());
            }
            actionValues.add(val);
        }
        return actionValues;
    }
    private void update(EnvironmentModel environment, RLState state) {

        double newValue = 0;

        if (!environment.isTerminal(state)) {
            List<Double> actionValues = actionValues(environment,state);
            int best = maxIndex(actionValues);

            newValue = GAMMA*actionValues.get(best);

            policy.setAction(state,environment.getActions(state).get(best));
        }
        vNext.put(state,newValue);
    }

    private int maxIndex(List<Double> list) {
        int maxIndex= 0;

        for (int i = 1; i <list.size() ; i++) {
            if (list.get(i)>list.get(maxIndex))
            {
                maxIndex=i;
            }
        }
        return maxIndex;
    }







}
