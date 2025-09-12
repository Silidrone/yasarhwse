package chapter4.decisiontree.demo.assasin.npc.decisiontree.decision;

import chapter4.decisiontree.demo.assasin.npc.decisiontree.GuardDTNode;
import chapter4.decisiontree.demo.assasin.npc.decisiontree.GuardExtData;
import chapter4.decisiontree.demo.assasin.npc.decisiontree.GuardIntData;

public class HealthyEnough extends GuardDecision{
    private static final double FITNESS_THRESHOLD = 50;

    public HealthyEnough(GuardDTNode trueBranch, GuardDTNode falseBranch) {
        super(trueBranch, falseBranch);
    }

    @Override
    protected GuardDTNode getBranch(GuardExtData extData, GuardIntData intData) {

        return intData.getHealth() > FITNESS_THRESHOLD ? trueBranch:falseBranch;
    }
}
