package chapter2.taggame;


import chapter2.OrientationType;
import chapter2.StaticInfo;
import chapter2.SteeringBehavior;
import chapter2.steering.Wander;
import chapter2.taggame.hw.muhammed.TSE_HW_Muhammed;
import math.geom2d.Vector2D;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Bootstrap;


class WanderEngine implements TagSteeringEngine
{
    @Override
    public SteeringBehavior getSteeringBehavior(TagPlayer player, TagArena arena) {
        return new Wander();
    }
}

public class TagGame extends StateBasedGame {
    public static final int DemoWidth = 1000;
    public static final int DemoHeight = 800;
    private static final String TAGGAME = "Tag Game";
    private final int tagArenaIndex=0;

    public TagGame() {
        super(TAGGAME);
        addState(new TagArena());
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        enterState(tagArenaIndex);
    }

    public TagArena getArena()
    {
        return   (TagArena) getState(tagArenaIndex);
    }

    public void addPlayer(TagPlayer player) {
        TagArena arena = (TagArena) getState(tagArenaIndex);

        arena.addPlayer(player);
    }

    public static void main(String[] args) {
        TagGame game = new TagGame();

        TagPlayer tp1 = new TagPlayer("Ali", new StaticInfo(new Vector2D(90,90),0.0, OrientationType.VelocityBased), Color.blue);
        TagPlayer tp2 = new TagPlayer("Can", new StaticInfo(new Vector2D(290,290),0.0, OrientationType.VelocityBased), Color.green);
        TagPlayer tp3 = new TagPlayer("Murat", new StaticInfo(new Vector2D(190,290),0.0, OrientationType.VelocityBased), Color.yellow);
        TagPlayer tp4 = new TagPlayer("Sevgi", new StaticInfo(new Vector2D(390,290),0.0, OrientationType.VelocityBased), Color.magenta);

        tp1.setSteeringEngine(new TSE_HW_Muhammed());
        tp2.setSteeringEngine(new TSE_HW_Muhammed());
        tp3.setSteeringEngine(new TSE_HW_Muhammed());
        tp4.setSteeringEngine(new TSE_HW_Muhammed());
        game.addPlayer(tp1);
        game.addPlayer(tp2);
        game.addPlayer(tp3);
        game.addPlayer(tp4);

        Bootstrap.runAsApplication(game,DemoWidth,DemoHeight,false);

    }


}
