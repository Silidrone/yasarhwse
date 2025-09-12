package chapter4.statemachine;

import java.util.List;

public interface State<E,I> {

    List<Action<E,I> > getActions();
    List<Action<E,I> > getEntryActions();
    List<Action<E,I> > getExitActions();

    List<Transition<E,I> > getTransitions();
}
