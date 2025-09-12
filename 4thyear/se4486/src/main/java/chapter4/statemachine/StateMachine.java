package chapter4.statemachine;


import java.util.ArrayList;
import java.util.List;

public class StateMachine<E, I> {
    State<E,I> initialState;
    State<E,I> currentState;

    public List<Action<E,I>> update(E extData, I intData)
    {
        List<Transition<E,I>> transitions = currentState.getTransitions();

        List<Action<E,I> > actions = new ArrayList<>();

        boolean aTransitionOccurred = false;

        for (Transition<E,I> transition: transitions)
        {
            if (transition.isTriggered(extData,intData))
            {
                actions.addAll(currentState.getExitActions());
                currentState = transition.getTargetState();
                actions.addAll(currentState.getEntryActions());
                aTransitionOccurred = true;
                break;
            }
        }

        if (!aTransitionOccurred)
            actions = currentState.getActions();

        return actions;

    }

    public State<E,I> getCurrentState() {
        return currentState;
    }

    public void init() {
        currentState = initialState;
    }

    public void setInitialState(State<E,I> state) {
        this.initialState = state;
    }
}
