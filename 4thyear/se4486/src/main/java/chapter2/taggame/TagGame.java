package chapter2.taggame;


import chapter2.OrientationType;
import chapter2.StaticInfo;
import chapter2.steering.Wander;
import math.geom2d.Vector2D;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Bootstrap;

public class TagGame extends StateBasedGame {
    private static final int DemoWidth = 2400;
    private static final int DemoHeight = 1800;
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

    private void addPlayer(TagPlayer player) {
        TagArena arena = (TagArena) getState(tagArenaIndex);

        arena.addPlayer(player);
    }

    public static void main(String[] args) {
        TagGame game = new TagGame();

        TagPlayer tp1 = new TagPlayer("Ali", new StaticInfo(new Vector2D(90,90),0.0, OrientationType.VelocityBased), Color.blue);
        TagPlayer tp2 = new TagPlayer("Can", new StaticInfo(new Vector2D(290,290),0.0, OrientationType.VelocityBased), Color.green);

       /*todo STEERING BEHAVIORS*/
        tp1.setSteeringEngine((p,a)-> new Wander());
        tp2.setSteeringEngine((p,a)-> new Wander());
        game.addPlayer(tp1);
        game.addPlayer(tp2);

        Bootstrap.runAsApplication(game,DemoWidth,DemoHeight,false);

    }


}
