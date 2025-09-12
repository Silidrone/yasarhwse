package chapter4.statemachine;

import java.util.List;

public interface Transition<E,I> {

    boolean isTriggered(E extData, I intData);

    State<E,I> getTargetState();

    List<Action<E,I>  > getActions();
}
