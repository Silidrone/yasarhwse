package chapter4.behaviortree.assasin.guard.tests;


import chapter4.behaviortree.Task;
import chapter4.behaviortree.assasin.guard.GuardExtData;
import chapter4.behaviortree.assasin.guard.GuardIntData;

public class HealthyEnough implements Task<GuardExtData, GuardIntData> {
    private static final double FITNESS_THRESHOLD = 25;


    @Override
    public boolean run(GuardExtData extData, GuardIntData intData) {
        return intData.getHealth()>= FITNESS_THRESHOLD;
    }
}
