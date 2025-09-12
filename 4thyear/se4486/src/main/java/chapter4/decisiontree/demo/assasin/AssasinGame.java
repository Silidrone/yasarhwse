package chapter4.decisiontree.demo.assasin;

import chapter2.OrientationType;
import chapter2.StaticInfo;
import chapter2.SteeringBehavior;
import chapter2.steering.FollowPath;
import chapter4.decisiontree.demo.assasin.npc.Guard;
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
    private static final int DemoHeight = 800;
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
        Assasin assasin = new ControlledAssasin("Ali", new StaticInfo(new Vector2D(190,200),0.0, OrientationType.VelocityBased));
        Guard guard1 = new Guard( new StaticInfo(new Vector2D(490,300),0, OrientationType.VelocityBased));
        guard1.setPatrolBehavior(new FollowPath(Arrays.asList(new Vector2D(490,700),new Vector2D(490,100))));
        Polygon2D obstacle =  new SimplePolygon2D(new Point2D(300,100), new Point2D(330,100), new Point2D(330,650), new Point2D(300,650));
        assasin.setNormalBehavior(SteeringBehavior.NoSteering);
        game.setPlayer(assasin);
        game.addGuard(guard1);
        game.addObstacle(obstacle);
        game.setTarget(new Vector2D(850,200));
        Bootstrap.runAsApplication(game,DemoWidth,DemoHeight,false);
    }
}
