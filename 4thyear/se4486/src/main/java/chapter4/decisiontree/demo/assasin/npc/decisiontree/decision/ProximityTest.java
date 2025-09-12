package chapter4.decisiontree.demo.assasin.npc.decisiontree.decision;

import chapter4.decisiontree.demo.assasin.npc.decisiontree.GuardDTNode;
import chapter4.decisiontree.demo.assasin.npc.decisiontree.GuardExtData;
import chapter4.decisiontree.demo.assasin.npc.decisiontree.GuardIntData;

public class ProximityTest extends GuardDecision{
    private static final double ATTACK_DISTANCE = 10;

    public ProximityTest(GuardDTNode trueBranch, GuardDTNode falseBranch) {
        super(trueBranch, falseBranch);
    }

    @Override
    protected GuardDTNode getBranch(GuardExtData extData, GuardIntData intData) {

        return extData.getAssasinPos().minus(intData.getPos()).norm() < ATTACK_DISTANCE ? trueBranch:falseBranch;
    }
}
