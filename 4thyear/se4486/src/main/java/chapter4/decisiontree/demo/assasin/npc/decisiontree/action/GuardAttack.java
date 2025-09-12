package chapter4.decisiontree.demo.assasin.npc.decisiontree.action;


import chapter2.SteeringBehavior;
import chapter2.steering.Arrive;
import chapter4.decisiontree.demo.assasin.npc.GuardState;
import chapter4.decisiontree.demo.assasin.npc.decisiontree.GuardExtData;
import chapter4.decisiontree.demo.assasin.npc.decisiontree.GuardIntData;

public class GuardAttack extends GuardAction {





    @Override
    public GuardState getState() {
        return GuardState.Attack;
    }

    @Override
    public SteeringBehavior getSteering() {
        return new Arrive(extData.getAssasinPos(),1);
    }
}
