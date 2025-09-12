package chapter4.decisiontree;

public interface DecisionTreeNode<E,I> {
    // E: External Data  I: Internal Data
    Action<E,I> makeDecision(E extData,I intData);
}
