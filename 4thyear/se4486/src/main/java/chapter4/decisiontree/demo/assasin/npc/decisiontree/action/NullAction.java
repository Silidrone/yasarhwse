package chapter4.decisiontree.demo.assasin.npc.decisiontree.action;


import chapter2.SteeringBehavior;
import chapter4.decisiontree.demo.assasin.npc.GuardState;
import chapter4.decisiontree.demo.assasin.npc.decisiontree.GuardExtData;
import chapter4.decisiontree.demo.assasin.npc.decisiontree.GuardIntData;

public class NullAction extends GuardAction {

    @Override
    public GuardState getState() {
        return GuardState.Patrol;
    }

    @Override
    public SteeringBehavior getSteering() {
        return SteeringBehavior.NoSteering;
    }
}
