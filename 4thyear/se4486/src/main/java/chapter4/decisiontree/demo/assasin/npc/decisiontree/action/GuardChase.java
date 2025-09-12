package chapter4.decisiontree.demo.assasin.npc.decisiontree.action;


import chapter2.SteeringBehavior;
import chapter2.steering.Seek;
import chapter4.decisiontree.demo.assasin.npc.GuardState;
import chapter4.decisiontree.demo.assasin.npc.decisiontree.GuardExtData;
import chapter4.decisiontree.demo.assasin.npc.decisiontree.GuardIntData;

public class GuardChase extends GuardAction {



    @Override
    public GuardState getState() {
        return GuardState.Chase;
    }

    @Override
    public SteeringBehavior getSteering() {
        //System.out.println("CHASE: "+ intData.getLastAssasinPos());
        return new Seek(intData.getLastAssasinPos(),1);
    }
}
