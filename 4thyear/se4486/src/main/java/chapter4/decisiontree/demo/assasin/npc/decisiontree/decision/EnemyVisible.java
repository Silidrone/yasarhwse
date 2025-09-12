package chapter4.decisiontree.demo.assasin.npc.decisiontree.decision;

import chapter4.decisiontree.demo.assasin.npc.decisiontree.GuardDTNode;
import chapter4.decisiontree.demo.assasin.npc.decisiontree.GuardExtData;
import chapter4.decisiontree.demo.assasin.npc.decisiontree.GuardIntData;
import math.geom2d.Vector2D;
import utils.GeomUtils;

public class EnemyVisible extends GuardDecision{
    private static final double FITNESS_THRESHOLD = 50;
    private static final int VISIBILITY_EXPIRE_TIME = 5000;
    private int lastVisible = 2*VISIBILITY_EXPIRE_TIME;
    private Vector2D lastPos;

    public EnemyVisible(GuardDTNode trueBranch, GuardDTNode falseBranch) {
        super(trueBranch, falseBranch);
    }

    @Override
    protected GuardDTNode getBranch(GuardExtData extData, GuardIntData intData) {

        boolean enemyVisible = GeomUtils.isVisible( extData.getAssasinPos(), intData.getPos(), extData.getObstacles());


        if (enemyVisible) {
            lastVisible = 0;

            lastPos = extData.getAssasinPos().clone();

            intData.setLastAssasinPos(lastPos.clone());

            return trueBranch;
        }
        else {
            lastVisible += extData.getTime();
            if (lastVisible < VISIBILITY_EXPIRE_TIME ) {
                intData.setLastAssasinPos(lastPos.clone());
                //System.out.println("NOT VISIBLE ASSASINPO:" +  extData.getAssasinPos()+" CHASING:" + intData.getLastAssasinPos() + " LP: "+ lastPos);
                return trueBranch;
            }
            else return falseBranch;
        }
    }
}
