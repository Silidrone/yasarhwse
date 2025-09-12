package chapter4.decisiontree;

public abstract class Decision<E,I> implements DecisionTreeNode<E,I> {

    protected DecisionTreeNode<E,I> trueBranch;
    protected DecisionTreeNode<E,I> falseBranch;

    public Decision(DecisionTreeNode<E,I> trueBranch, DecisionTreeNode<E,I> falseBranch) {
        this.trueBranch = trueBranch;
        this.falseBranch = falseBranch;
    }

    @Override
    public Action<E,I> makeDecision(E extData, I intData) {

        System.out.println(getClass().getName());
        DecisionTreeNode<E,I> child = getBranch(extData,intData);

        return child.makeDecision(extData,intData);
    }

    /**
     * TO BE immplemented in subclasses. Represents the test to be made
     * @param extData
     * @param intData
     * @return
     */
    protected abstract DecisionTreeNode<E,I> getBranch(E extData, I intData);
}
