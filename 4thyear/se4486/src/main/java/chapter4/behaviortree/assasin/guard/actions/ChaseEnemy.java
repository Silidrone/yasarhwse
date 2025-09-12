package chapter4.behaviortree.assasin.guard.actions;


import chapter2.steering.Seek;
import chapter4.behaviortree.Task;
import chapter4.behaviortree.assasin.guard.GuardExtData;
import chapter4.behaviortree.assasin.guard.GuardIntData;
import chapter4.behaviortree.assasin.guard.GuardState;

public class ChaseEnemy implements Task<GuardExtData, GuardIntData> {


    @Override
    public boolean run(GuardExtData extData, GuardIntData intData) {

        intData.setState(GuardState.Chase);
        intData.setSteeringBehavior(new Seek(intData.getLastAssasinPos(),1));

        return true;
    }
}
