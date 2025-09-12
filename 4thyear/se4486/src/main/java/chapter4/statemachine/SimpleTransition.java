package chapter4.statemachine;


import java.util.List;

public class SimpleTransition<E,I>  implements Transition<E,I>{

    Condition<E,I> condition;
    State<E,I> targetState;
    List<Action<E,I>> actions;


    @Override
    public boolean isTriggered(E extData, I intData) {
        return condition.test(extData,intData);
    }

    @Override
    public State<E,I> getTargetState() {
        return targetState;
    }

    @Override
    public List<Action<E,I>> getActions() {
        return actions;
    }

    public void setActions(List<Action<E,I>> actions) {
        this.actions = actions;
    }

    public void setTargetState(State<E,I> state) {
        this.targetState = state;
    }

    public void setCondition(Condition<E,I> condition) {
        this.condition = condition;
    }
}
