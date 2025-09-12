package chapter4.decisiontree.demo.assasin.npc;


import chapter2.Ball;
import chapter2.MovingEntity;
import chapter2.StaticInfo;
import chapter2.SteeringBehavior;
import chapter4.decisiontree.demo.assasin.npc.decisiontree.*;
import chapter4.decisiontree.demo.assasin.npc.decisiontree.action.*;
import chapter4.decisiontree.demo.assasin.npc.decisiontree.decision.EnemyVisible;
import chapter4.decisiontree.demo.assasin.npc.decisiontree.decision.HealthyEnough;
import chapter4.decisiontree.demo.assasin.npc.decisiontree.decision.ProximityTest;
import math.geom2d.Vector2D;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.geom.Rectangle2D;

public class Guard extends MovingEntity {
    private static final float PLAYER_RADIUS = 20;

    String name;

    GuardDTNode decisionTree;
    private GuardState guardState;
    private double health = 100;

    public void setPatrolBehavior(SteeringBehavior patrolBehavior) {
        this.patrolBehavior = patrolBehavior;
    }

    SteeringBehavior patrolBehavior;


    public Guard(StaticInfo staticInfo) {
        super(staticInfo, new Ball(Color.blue,PLAYER_RADIUS));

        /**
         * TODO-1: Build a proper decision tree by :
         *          - Implement 3 decisions
         *              > EnemyVisible
         *              > EnemyClose
         *              > HealthyEnough
         *          - Instantiate decision tree with implemented decisions and actions
         *
         *  TODO-2: This tree seems to be too naive that it gives up chasing when it stops seeing assasin
         *          Can we solve this somehow by considering timeout in !(EnemyVisible)
         *
         */
        decisionTree = new EnemyVisible(
                                new HealthyEnough(
                                        new ProximityTest(
                                                new GuardAttack(),
                                                new GuardChase()),
                                        new GuardRunAway()),
                                new NullAction()
                                        );



   }






    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame, Rectangle2D boundary) {
        super.init(gameContainer,stateBasedGame,boundary);
        guardState = GuardState.Patrol;
        setSteeringBehavior(patrolBehavior);


    }




    public GuardAction updateState(GuardExtData npcData) {


        GuardAction npcAction = (GuardAction) decisionTree.makeDecision(npcData, new GuardIntData(health,staticInfo.getPos(),guardState,npcData.getAssasinPos()));

        guardState = npcAction.getState();
        if (guardState!=GuardState.Patrol)
            setSteeringBehavior(npcAction.getSteering());
        else setSteeringBehavior(patrolBehavior);

        ((Ball)body).setColor(guardState.color);

        return npcAction;
    }

    public Vector2D pos() {
        return staticInfo.getPos();
    }
}
