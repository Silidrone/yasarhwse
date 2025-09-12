package chapter4.behaviortree.assasin.guard.actions;


import chapter2.steering.Arrive;
import chapter4.behaviortree.Task;
import chapter4.behaviortree.assasin.guard.GuardExtData;
import chapter4.behaviortree.assasin.guard.GuardIntData;
import chapter4.behaviortree.assasin.guard.GuardState;
import math.geom2d.Vector2D;

public class GoToGuardPoint implements Task<GuardExtData, GuardIntData> {

    private static final double GUARD_DISTANCE = 100;
    Vector2D target;

    public GoToGuardPoint(Vector2D target) {
        this.target = target;
    }

    @Override
    public boolean run(GuardExtData extData, GuardIntData intData) {

        Vector2D assasinDirection = extData.getAssasinPos().minus(target).normalize().times(GUARD_DISTANCE);
        Vector2D guardPoint = target.plus(assasinDirection);

        intData.setState(GuardState.Guarding);
        intData.setSteeringBehavior(new Arrive(guardPoint,1));

        return true;
    }
}
