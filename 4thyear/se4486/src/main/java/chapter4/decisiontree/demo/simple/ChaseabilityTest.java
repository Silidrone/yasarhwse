package chapter4.decisiontree.demo.simple;

import chapter4.decisiontree.Decision;
import chapter4.decisiontree.DecisionTreeNode;

public class ChaseabilityTest extends Decision<SimpleGameExtData,SimpleGameIntData> {
    private static final double CHASE_DISTANCE = 5.0;

    public ChaseabilityTest(DecisionTreeNode<SimpleGameExtData, SimpleGameIntData> trueBranch, DecisionTreeNode<SimpleGameExtData, SimpleGameIntData> falseBranch) {
        super(trueBranch, falseBranch);
    }

    @Override
    protected DecisionTreeNode<SimpleGameExtData, SimpleGameIntData> getBranch(SimpleGameExtData extData, SimpleGameIntData intData) {
        return extData.playerPos.minus(intData.pos).norm() < CHASE_DISTANCE ? trueBranch:falseBranch ;
    }
}
