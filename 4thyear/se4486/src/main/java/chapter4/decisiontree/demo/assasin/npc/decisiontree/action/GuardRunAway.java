package chapter4.decisiontree.demo.assasin.npc.decisiontree.action;


import chapter2.SteeringBehavior;
import chapter2.steering.Flee;
import chapter4.decisiontree.demo.assasin.npc.GuardState;
import chapter4.decisiontree.demo.assasin.npc.decisiontree.GuardExtData;
import chapter4.decisiontree.demo.assasin.npc.decisiontree.GuardIntData;

public class GuardRunAway extends GuardAction {


    @Override
    public GuardState getState() {
        return GuardState.RunAway;
    }

    @Override
    public SteeringBehavior getSteering() {
        return new Flee(extData.getAssasinPos(),1);
    }
}
