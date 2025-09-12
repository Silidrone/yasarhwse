package chapter4.behaviortree.assasin.guard;


import chapter2.Ball;
import chapter2.MovingEntity;
import chapter2.StaticInfo;
import chapter2.SteeringBehavior;
import chapter4.behaviortree.CompositeTask;
import chapter4.behaviortree.Selector;
import chapter4.behaviortree.Sequence;
import chapter4.behaviortree.Task;
import chapter4.behaviortree.assasin.guard.actions.AttackEnemy;
import chapter4.behaviortree.assasin.guard.actions.ChaseEnemy;
import chapter4.behaviortree.assasin.guard.actions.GoToGuardPoint;
import chapter4.behaviortree.assasin.guard.tests.EnemyAudible;
import chapter4.behaviortree.assasin.guard.tests.EnemyClose;
import chapter4.behaviortree.assasin.guard.tests.EnemyVisible;
import chapter4.behaviortree.assasin.guard.tests.HealthyEnough;
import math.geom2d.Vector2D;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.geom.Rectangle2D;
import java.util.Arrays;

public class Guard extends MovingEntity {
    private static final float PLAYER_RADIUS = 20;
    private static final double INITIAL_HEALTH = 100;

    String name;

    CompositeTask<GuardExtData,GuardIntData> behaviorTree;

    GuardIntData internal;

    public void setPatrolBehavior(SteeringBehavior patrolBehavior) {
        this.patrolBehavior = patrolBehavior;
    }

    SteeringBehavior patrolBehavior;


    /**
     *  TODO: build the behavior tree of Guard as described in "GuardBehaviorTree.png"
     * @param staticInfo
     * @param targetPos
     */
    public Guard(StaticInfo staticInfo, Vector2D targetPos) {
        super(staticInfo, new Ball(Color.blue,PLAYER_RADIUS));

        // Null tree always returning false
        // ! REPLACE this with actual tree
        behaviorTree = new Selector<>();

        CompositeTask<GuardExtData,GuardIntData> enemyVisibleSequence= new Sequence<>();

        enemyVisibleSequence.addChild(new EnemyVisible());

        CompositeTask<GuardExtData,GuardIntData> visibleEnemySelector = new Selector<>();

        CompositeTask<GuardExtData,GuardIntData> enemyCloseSequence = new Sequence<>();

        enemyVisibleSequence.addChild(visibleEnemySelector);

        enemyCloseSequence.addChild(new EnemyClose());
        enemyCloseSequence.addChild(new AttackEnemy());

        CompositeTask<GuardExtData,GuardIntData> healthyEnoughSequence = new Sequence<>();

        healthyEnoughSequence.addChild(new HealthyEnough());
        healthyEnoughSequence.addChild(new ChaseEnemy());

       visibleEnemySelector.addChild(enemyCloseSequence);
       visibleEnemySelector.addChild(healthyEnoughSequence);
       visibleEnemySelector.addChild(new GoToGuardPoint(targetPos));


       CompositeTask<GuardExtData, GuardIntData> enemyaudibleSequence = new Sequence<>();

       enemyaudibleSequence.addChild(new EnemyAudible(EnemyAudible.TIMEOUT));
       enemyaudibleSequence.addChild(new GoToGuardPoint(targetPos));


       behaviorTree.addChild(enemyVisibleSequence);
       behaviorTree.addChild(enemyaudibleSequence);


        internal = new GuardIntData(INITIAL_HEALTH ,staticInfo,null,EnemyVisible.TIMEOUT*2,EnemyAudible.TIMEOUT*2,GuardState.Patrol);


    }






    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame, Rectangle2D boundary) {
        super.init(gameContainer,stateBasedGame,boundary);

        setSteeringBehavior(patrolBehavior);

        internal.setState(GuardState.Patrol);

    }




    public void updateState(GuardExtData extData) {

        internal.setStaticInfo(staticInfo);

        if (!behaviorTree.run(extData,internal))
        {
            internal.setState(GuardState.Patrol);
        }


        if (internal.getState()!=GuardState.Patrol)
            setSteeringBehavior(internal.getSteeringBehavior());
        else setSteeringBehavior(patrolBehavior);

        ((Ball)body).setColor(internal.getState().color);
    }

    public Vector2D pos() {
        return staticInfo.getPos();
    }


    public GuardState getState() {
        return internal.getState();
    }
}
