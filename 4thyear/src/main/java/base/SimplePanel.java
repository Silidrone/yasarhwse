package base;


import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimplePanel extends BasicGameState {

    public static final float FRAME_WIDTH = 30;
    private static final Color FrameColor = Color.black;
    private static final Color PanelColor = Color.darkGray;



    List<GameEntity> entityList;
    protected double width;
    protected double height;



    long startTime;



    public SimplePanel() {
        this.entityList = new ArrayList();
    }

    public SimplePanel(GameEntity... ttt) {
        entityList = new ArrayList<>();
        entityList.addAll(Arrays.asList( ttt));
    }

    public void addEntity(GameEntity entity)
    {
        entityList.add(entity);
    }


    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        width = gameContainer.getWidth()-2*FRAME_WIDTH;
        height = gameContainer.getHeight()-2*FRAME_WIDTH;
        for (GameEntity e:entityList
             ) {
            e.init(gameContainer,stateBasedGame,getBounds());
        }
        startTime = System.currentTimeMillis();

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {

            renderBackground(gameContainer, stateBasedGame, graphics);

            graphics.translate(FRAME_WIDTH, FRAME_WIDTH);


            renderEtities(gameContainer, stateBasedGame, graphics);

    }

    protected void renderBackground(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {

            int w = gameContainer.getWidth();
            int h = gameContainer.getHeight();



            graphics.setColor(FrameColor);
            graphics.fillRect(0, 0, w, h);
            graphics.setColor(PanelColor);
            graphics.fillRect(FRAME_WIDTH, FRAME_WIDTH, (float) width, (float) height);


     }


    protected void renderEtities(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        for (GameEntity e : entityList
             ) {
            e.render(gameContainer,stateBasedGame,graphics);
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int time) throws SlickException {
        for (GameEntity e : entityList
        ) {
            e.update(gameContainer,stateBasedGame,time); // set to 1 for debug purposes
        }
    }

    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(FRAME_WIDTH,FRAME_WIDTH,width,height);
    }


}
