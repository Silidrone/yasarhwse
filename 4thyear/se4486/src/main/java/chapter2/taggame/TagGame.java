package chapter2.taggame;


import chapter2.OrientationType;
import chapter2.StaticInfo;
import math.geom2d.Vector2D;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Bootstrap;

import java.util.*;

public class TagGame extends StateBasedGame {
    private static final int DemoWidth = 1600;
    private static final int DemoHeight = 1200;
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

    public static Vector2D generateRandomPosition() {
        Random random = new Random();
        double x = random.nextDouble() * (DemoWidth - 50);
        double y = random.nextDouble() * (DemoHeight - 50);
        return new Vector2D(x, y);
    }

    public static void addRandomPlayers(int n, TagSteeringEngine tagSteeringEngine, TagGame game) {
        Set<Color> usedColors = new HashSet<>();

        Random random = new Random();
        while (usedColors.size() < n) {
            usedColors.add(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
        }

        Object[] colors = usedColors.toArray();

        for (int i = 0; i < n; i++) {

            TagPlayer player = new TagPlayer("P" + (i + 1),
                    new StaticInfo(generateRandomPosition(), 0.0, OrientationType.VelocityBased),
                    (Color) colors[i]);

            player.setSteeringEngine(tagSteeringEngine);
            game.addPlayer(player);
        }
    }

    public static void main(String[] args) {
        TagGame game = new TagGame();

        addRandomPlayers(6, new TSE_HW(), game);
        
        Bootstrap.runAsApplication(game,DemoWidth,DemoHeight,false);

    }


}
