package chapter4.decisiontree.demo.simple;

import chapter4.decisiontree.Decision;
import chapter4.decisiontree.DecisionTreeNode;

public class VisibilityTest extends Decision<SimpleGameExtData,SimpleGameIntData> {
    public VisibilityTest(DecisionTreeNode<SimpleGameExtData, SimpleGameIntData> trueBranch, DecisionTreeNode<SimpleGameExtData, SimpleGameIntData> falseBranch) {
        super(trueBranch, falseBranch);
    }

    @Override
    protected DecisionTreeNode<SimpleGameExtData, SimpleGameIntData> getBranch(SimpleGameExtData extData, SimpleGameIntData intData) {
        return extData.isPlayerVisible ? trueBranch:falseBranch ;
    }
}
