package chapter4.behaviortree.assasin.guard.tests;


import chapter4.behaviortree.Task;
import chapter4.behaviortree.assasin.guard.GuardExtData;
import chapter4.behaviortree.assasin.guard.GuardIntData;

public class EnemyAudible implements Task<GuardExtData, GuardIntData> {

    public static final int TIMEOUT = 3000;

    int hearTimeOut =  3000;
    private static final double AUDIBLE_DISTANCE = 550;


    public EnemyAudible(int hearTimeOut) {
        this.hearTimeOut = hearTimeOut;
    }

    @Override
    public boolean run(GuardExtData extData, GuardIntData intData) {

        boolean closeEnough = extData.getAssasinPos().minus(intData.getStaticInfo().getPos()).norm()< AUDIBLE_DISTANCE;
        boolean enemyAudible = closeEnough && extData.isAssasinRunning();

        if (enemyAudible)
        {
            intData.lastAssasinHeard = 0;
            return true;
        }
        else
        {
            intData.lastAssasinHeard += extData.getTime();
            return intData.lastAssasinHeard< TIMEOUT;
        }
    }
}
