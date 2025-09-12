package chapter4.statemachine;



public interface Action<E,I> {
    void perform(E extData, I intData);
}
