package chapter4.decisiontree.demo.simple;

import chapter4.decisiontree.Decision;
import chapter4.decisiontree.DecisionTreeNode;

public class AllarmExpiredTest extends Decision<SimpleGameExtData,SimpleGameIntData> {
    private static final int ALLARM_EXPIRED_TIME = 10;

    public AllarmExpiredTest(DecisionTreeNode<SimpleGameExtData, SimpleGameIntData> trueBranch, DecisionTreeNode<SimpleGameExtData, SimpleGameIntData> falseBranch) {
        super(trueBranch, falseBranch);
    }

    @Override
    protected DecisionTreeNode<SimpleGameExtData, SimpleGameIntData> getBranch(SimpleGameExtData extData, SimpleGameIntData intData) {
        return extData.time- extData.lastAlertTime > ALLARM_EXPIRED_TIME ? trueBranch:falseBranch ;
    }
}
