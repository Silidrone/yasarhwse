package chapter4.statemachine.demo;



import chapter4.decisiontree.Decision;
import chapter4.statemachine.Action;
import chapter4.statemachine.State;
import chapter4.statemachine.Transition;

import java.util.Collections;
import java.util.List;

public class DTTransition<E,I> implements Transition<E,I> {

    Decision<E,I> root;
    TargetState<E,I> targetState;


    @Override
    public boolean isTriggered(E extData, I intData) {

        targetState = (TargetState<E, I>) root.makeDecision(extData,intData);
        return targetState != null;
    }

    @Override
    public State<E, I> getTargetState() {
        return targetState == null ? null:targetState.getTarget();
    }

    @Override
    public List<Action<E, I>> getActions() {
        return targetState == null ? Collections.emptyList():targetState.getActions();
    }
}
