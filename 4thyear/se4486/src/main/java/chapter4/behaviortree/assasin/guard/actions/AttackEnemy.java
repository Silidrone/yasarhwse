package chapter4.behaviortree.assasin.guard.actions;


import chapter2.steering.Arrive;
import chapter4.behaviortree.Task;
import chapter4.behaviortree.assasin.guard.GuardExtData;
import chapter4.behaviortree.assasin.guard.GuardIntData;
import chapter4.behaviortree.assasin.guard.GuardState;

public class AttackEnemy implements Task<GuardExtData, GuardIntData> {


    @Override
    public boolean run(GuardExtData extData, GuardIntData intData) {
        intData.setState(GuardState.Attack);
        intData.setSteeringBehavior(new Arrive(extData.getAssasinPos(),1));

        return true;
    }
}
