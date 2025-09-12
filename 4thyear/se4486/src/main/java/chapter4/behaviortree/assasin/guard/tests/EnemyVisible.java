package chapter4.behaviortree.assasin.guard.tests;


import chapter4.behaviortree.Task;
import chapter4.behaviortree.assasin.guard.GuardExtData;
import chapter4.behaviortree.assasin.guard.GuardIntData;
import utils.GeomUtils;

public class EnemyVisible implements Task<GuardExtData, GuardIntData> {


    public static final int TIMEOUT = 3000;

    double normalizeAngle(double angle)
    {
        while (angle<0)
        {
            angle += 2*Math.PI;
        }

        while (angle>2*Math.PI)
            angle-= 2*Math.PI;
        return angle;
    }

    boolean enemyVisible(GuardExtData extData, GuardIntData intData)
    {
        double orientation = normalizeAngle(intData.getStaticInfo().getOrientation());
        double assasingAngle = normalizeAngle(extData.getAssasinPos().minus(intData.getStaticInfo().getPos()).angle());

        boolean isInViewCone =  Math.abs(orientation-assasingAngle)<Math.PI/2;

        boolean enemyVisible =isInViewCone && GeomUtils.isVisible(extData.getAssasinPos(),intData.getStaticInfo().getPos(),extData.getObstacles());
        return enemyVisible;
    }

    @Override
    public boolean run(GuardExtData extData, GuardIntData intData) {

        boolean enemyVisible = enemyVisible(extData,intData);

        if (enemyVisible)
        {
            intData.setLastAssasinPos(extData.getAssasinPos());
            intData.setLastAssasinSeen(0);
            return true;
        }
        else
        {
            intData.setLastAssasinSeen(intData.getLastAssasinSeen()+ extData.getTime());
            return intData.getLastAssasinSeen()< TIMEOUT;
        }
    }
}
