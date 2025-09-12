package chapter4.decisiontree.demo.assasin.npc.decisiontree.action;


import chapter2.SteeringBehavior;
import chapter4.decisiontree.Action;
import chapter4.decisiontree.demo.assasin.npc.GuardState;
import chapter4.decisiontree.demo.assasin.npc.decisiontree.GuardDTNode;
import chapter4.decisiontree.demo.assasin.npc.decisiontree.GuardExtData;
import chapter4.decisiontree.demo.assasin.npc.decisiontree.GuardIntData;

public abstract class GuardAction extends Action<GuardExtData, GuardIntData> implements GuardDTNode {

    protected GuardExtData extData;
    protected GuardIntData intData;

    public GuardAction() {
    }

    @Override
    public Action<GuardExtData, GuardIntData> makeDecision(GuardExtData extData, GuardIntData intData) {

        this.extData = extData;
        this.intData = intData;

        return super.makeDecision(extData, intData);

    }

    public abstract GuardState getState();
    public abstract SteeringBehavior getSteering();
}
