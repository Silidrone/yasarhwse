package chapter4.behaviortree.assasin;

import chapter2.OrientationType;
import chapter2.StaticInfo;
import chapter2.SteeringBehavior;
import chapter2.steering.FollowPath;
import chapter4.behaviortree.assasin.guard.Guard;
import math.geom2d.Point2D;
import math.geom2d.Vector2D;
import math.geom2d.polygon.Polygon2D;
import math.geom2d.polygon.SimplePolygon2D;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Bootstrap;

import java.util.Arrays;

public class AssasinGame extends StateBasedGame {
    private static final int DemoWidth = 1000;
    private static final int DemoHeight = 600;
    private static final String ASSASINGAME = "Assasin Game";
    private final int arenaIndex =0;


    public AssasinGame() {
        super(ASSASINGAME);
        addState(new AssasinArena());
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        enterState(arenaIndex);
    }

    private AssasinArena arena()
    {
        return (AssasinArena) getState(arenaIndex);
    }
    private void setPlayer(Assasin assasin) {
        arena().setAssasin(assasin);
    }

    void addGuard(Guard guard)
    {
        arena().addGuard(guard);
    }

    void addObstacle(Polygon2D obstacle)
    {
        arena().addObstacle(obstacle);
    }

    void setTarget(Vector2D target) {
        arena().setTarget(target);
    }

    public static void main(String[] args) {
        AssasinGame game = new AssasinGame();

        game.setTarget(new Vector2D(800,200));
        Assasin assasin = new ControlledAssasin("Ali", new StaticInfo(new Vector2D(190,200),0.0, OrientationType.VelocityBased));
        Guard guard1 = new Guard( new StaticInfo(new Vector2D(490,300),0, OrientationType.VelocityBased), game.getTargetPos());
        Guard guard2 = new Guard( new StaticInfo(new Vector2D(390,100),0,OrientationType.VelocityBased), game.getTargetPos());
        guard1.setPatrolBehavior(new FollowPath(Arrays.asList(new Vector2D(490,500),new Vector2D(490,100))));
        guard2.setPatrolBehavior(new FollowPath(Arrays.asList(new Vector2D(390,100),new Vector2D(390,500))));

        Polygon2D obstacle1 =  new SimplePolygon2D(new Point2D(300,100), new Point2D(310,100), new Point2D(310,450), new Point2D(300,450));
        Polygon2D obstacle2 =  new SimplePolygon2D(new Point2D(500,80), new Point2D(700,80), new Point2D(700,90), new Point2D(600,90));

        assasin.setNormalBehavior(SteeringBehavior.NoSteering);
        game.setPlayer(assasin);
        game.addGuard(guard1);
        game.addObstacle(obstacle1);
        game.addObstacle(obstacle2);
        Bootstrap.runAsApplication(game,DemoWidth,DemoHeight,false);

    }

    private Vector2D getTargetPos() {
        return arena().getTarget().pos;
    }


}
