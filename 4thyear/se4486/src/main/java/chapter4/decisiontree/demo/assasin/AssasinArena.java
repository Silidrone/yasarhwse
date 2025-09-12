package chapter4.decisiontree.demo.assasin;


import chapter2.MovementPanel;
import chapter2.Velocity;
import chapter4.decisiontree.demo.assasin.npc.Guard;
import chapter4.decisiontree.demo.assasin.npc.decisiontree.action.GuardAction;
import chapter4.decisiontree.demo.assasin.npc.decisiontree.GuardExtData;
import chapter4.decisiontree.demo.assasin.npc.GuardState;
import math.geom2d.Point2D;
import math.geom2d.Vector2D;
import math.geom2d.conic.Circle2D;
import math.geom2d.polygon.Polygon2D;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AssasinArena extends MovementPanel {

    private static final double ATTACK_DISTANCE = 5;
    private static final double ASSASIN_DISTANCE = 25;
    Assasin assasin;

    List<Guard> guards;
    List<Polygon2D> obstacles;
    private Target target;
    private boolean assasinated =false;

    public AssasinArena() {
        super();
        guards = new ArrayList<>();
        obstacles = new ArrayList<>();
    }

    public void setAssasin(Assasin assasin)
    {
        this.assasin= assasin;
        add(assasin);
    }

    public void addObstacle(Polygon2D obstacle)
    {
        obstacles.add(obstacle);
        add(new Obstacle(obstacle));
    }

    public void addGuard(Guard guard)
    {
        guards.add(guard);
        add(guard);
    }
    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        super.init(gameContainer, stateBasedGame);
        assasinated = false;

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        super.render(gameContainer, stateBasedGame, graphics);



    }




    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int time) throws SlickException {

        if (assasinated)
            return;

        super.update(gameContainer, stateBasedGame, time);

        GuardExtData npcData = generateNPCData(time);
        for (Guard g: guards)
        {
            GuardAction action = g.updateState(npcData);
            processAction(g,action);
        }

        checkCollisions();

        checkAssasination();
    }

    private void checkAssasination() {
        if (assasin.getStaticInfo().getPos().minus(target.pos).norm()<ASSASIN_DISTANCE)
        {
            assasinated = true;
            System.out.println("ASSASINATED!");
        }
    }

    private void processAction(Guard g, GuardAction action) {
        if ( action.getState()== GuardState.Attack)
        {
            if (g.pos().minus(assasin.getStaticInfo().getPos()).norm()<ATTACK_DISTANCE)
                assasin.terminate();
        }
    }

    private GuardExtData generateNPCData(int time) {

        List<Vector2D> guardsPos = guards.stream().map(Guard::pos).collect(Collectors.toList());
        Vector2D assasinPos = assasin.getStaticInfo().getPos();


        return new GuardExtData(assasinPos,guardsPos,obstacles,time);
    }


    private void checkCollisions() {
        checkAssasinCollision();
        guards.forEach(this::checkGuardCollision);
    }

    private void checkGuardCollision(Guard g) {
        boolean collided = false;
        for (Polygon2D o: obstacles)
        {
            collided = checkCollision(g.nextPosition(1), o);
            if (collided)
                break;
        }
        if (collided) {
            g.setVelocity(Velocity.NoVelocity());
            //System.out.println("GUARD COLLISION!");
        }
    }

    private void checkAssasinCollision() {
        boolean collided = false;
        for (Polygon2D o: obstacles)
        {
            collided = checkCollision(assasin.nextPosition(1) , o);
            if (collided)
                break;
        }
        if (collided) {
            assasin.setVelocity(Velocity.NoVelocity());
            //System.out.println("COLLISION!");
        }
    }

    private boolean checkCollision(Point2D pos, Polygon2D o) {
        Circle2D circle2D = new Circle2D(pos,Assasin.PLAYER_RADIUS);
        return CollisionUtils.polyCircle(o.vertices().toArray(new Point2D[0]), (float) circle2D.center().x(), (float) circle2D.center().y(), (float) circle2D.radius());
    }


    public void setTarget(Vector2D target) {
        remove(this.target);
        this.target =new Target(target);
        add(this.target);
    }
}
