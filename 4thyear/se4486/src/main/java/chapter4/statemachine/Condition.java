package chapter4.statemachine;

public interface Condition<E,I> {
    boolean test(E extData, I intData);
}
