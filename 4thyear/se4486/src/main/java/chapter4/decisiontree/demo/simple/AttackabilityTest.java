package chapter4.decisiontree.demo.simple;

import chapter4.decisiontree.Decision;
import chapter4.decisiontree.DecisionTreeNode;

public class AttackabilityTest extends Decision<SimpleGameExtData,SimpleGameIntData> {
    private static final double ATTACK_DISTANCE = 5.0;

    public AttackabilityTest(DecisionTreeNode<SimpleGameExtData, SimpleGameIntData> trueBranch, DecisionTreeNode<SimpleGameExtData, SimpleGameIntData> falseBranch) {
        super(trueBranch, falseBranch);
    }

    @Override
    protected DecisionTreeNode<SimpleGameExtData, SimpleGameIntData> getBranch(SimpleGameExtData extData, SimpleGameIntData intData) {
        return extData.playerPos.minus(intData.pos).norm() < StrategyService.getAttackDistance() ? trueBranch:falseBranch ;
    }
}
