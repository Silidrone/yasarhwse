package chapter2.taggame;


import chapter2.MovementPanel;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import utils.RandomUtils;

import java.util.ArrayList;
import java.util.List;

public class TagArena extends MovementPanel {

    private static final double SCOREBOARD_WIDTH = 100;
    TagGameState state;

    List<TagPlayer> players;

    TagPlayer tagPlayer;


    long tagChangeTime;
    private long TagWarmUpTime = 3000;

    public TagArena() {
        super();
        this.players = new ArrayList<>();
    }

    public List<TagPlayer> getPlayers()
    {
        return players;
    }


    public void addPlayer(TagPlayer player)
    {
        players.add(player);
        add(player);

    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        super.init(gameContainer, stateBasedGame);

        state = TagGameState.WarmUp;
        int tag = RandomUtils.randomInt(players.size());

        setTag(players.get(tag),players.get(tag));

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        super.render(gameContainer, stateBasedGame, graphics);
        renderScores(gameContainer,stateBasedGame,graphics);
    }

    private void renderScores(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        double x = width-FRAME_WIDTH-SCOREBOARD_WIDTH;
        double y = FRAME_WIDTH;



        for (int p = 0; p < players.size(); p++) {
            graphics.setColor(players.get(p).getColor());
            graphics.drawString(players.get(p).name+ " : "+ players.get(p).score(), (float) x, (float) y);
            y+=20;
        }


    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int time) throws SlickException {
        super.update(gameContainer, stateBasedGame, time);

        if (state== TagGameState.WarmUp)
        {
            long now = System.currentTimeMillis();
            if (now-tagChangeTime>TagWarmUpTime)
            {
                players.forEach(TagPlayer::warmupFinished);
                state = TagGameState.InPlay;
            }
        }
        else {
            checkTagging();
        }
    }

    private  void checkTagging() {
        for (TagPlayer player:players)
        {
            if (player == tagPlayer)
                continue;

            if (tagPlayer.tagging(player)) {
                setTag(tagPlayer,player);
                break;
            }
        }

    }

    private void setTag(TagPlayer oldTag, TagPlayer newTag) {
        tagPlayer = newTag;
        state = TagGameState.WarmUp;
        tagChangeTime= System.currentTimeMillis();
        players.forEach((p)->{p.tagChanged(oldTag,tagPlayer);});
        players.forEach((p)->{
            System.out.println(p.name+" : " + p.tagCount);
        });
    }
}
