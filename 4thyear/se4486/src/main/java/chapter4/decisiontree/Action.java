package chapter4.decisiontree;

public abstract class Action<E,I> implements DecisionTreeNode<E,I> {
    @Override
    public Action<E,I> makeDecision(E extData, I intData) {
        return this;
    }
}
