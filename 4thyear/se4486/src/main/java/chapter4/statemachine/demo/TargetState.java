package chapter4.statemachine.demo;




import chapter4.statemachine.Action;
import chapter4.statemachine.State;

import java.util.List;

public class TargetState<E,I> extends chapter4.decisiontree.Action<E,I> {
    List<Action<E,I>> actions;
    State<E,I> target;

    public TargetState(List<Action<E,I>> actions, State<E, I> target) {
        this.actions = actions;
        this.target = target;
    }

    public List<chapter4.statemachine.Action<E,I>> getActions() {
        return actions;
    }

    public State<E, I> getTarget() {
        return target;
    }
}
