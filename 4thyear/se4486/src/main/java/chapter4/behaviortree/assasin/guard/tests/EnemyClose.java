package chapter4.behaviortree.assasin.guard.tests;


import chapter4.behaviortree.Task;
import chapter4.behaviortree.assasin.guard.GuardExtData;
import chapter4.behaviortree.assasin.guard.GuardIntData;

public class EnemyClose implements Task<GuardExtData, GuardIntData> {
    private static final double PROXIMITY_THRESHOLD = 15;


    @Override
    public boolean run(GuardExtData extData, GuardIntData intData) {
        boolean enmyClose = extData.getAssasinPos().minus(intData.getStaticInfo().getPos()).norm()< PROXIMITY_THRESHOLD;
        return enmyClose;
    }
}
