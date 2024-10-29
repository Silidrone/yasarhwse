package chapter2;



import chapter2.base.GameEntity;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class MovementPanel extends BasicGameState {

    private static final Color MeasureLineColor = Color.lightGray;
    private static final float MeasureLineLength = 3;

    public static final float FRAME_WIDTH = 30;
    private static final Color FrameColor = Color.black;
    private static final Color PanelColor = Color.darkGray;
    private static final long WARMUPTIME = 5000;

    long startTime;

    protected List<GameEntity> entities;

    protected double width;
    protected double height;


    public MovementPanel() {
        entities = new ArrayList<>();
    }

    public int getID() {
        return 0;
    }

    public Rectangle2D getBoundaries() {
        return new Rectangle2D.Double(0,0,width,height);
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

        width = gameContainer.getWidth()-2*FRAME_WIDTH;
        height = gameContainer.getHeight()-2*FRAME_WIDTH;

        entities.forEach((e)->e.init(gameContainer,stateBasedGame,getBoundaries()));

        startTime = System.currentTimeMillis();
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {

        renderBackground(gameContainer,graphics);

        drawMeasureLines(graphics);

        // Adjusting offset caused by the frame. and reversing y-axis
        graphics.translate(FRAME_WIDTH, (float) height + FRAME_WIDTH);
        graphics.scale(1, -1);

        entities.forEach(e->e.render(gameContainer,stateBasedGame,graphics));

        graphics.scale(1, -1);
        graphics.translate(-FRAME_WIDTH, (float) -height - FRAME_WIDTH);

    }



    protected void renderBackground(GameContainer gameContainer, Graphics graphics) {

        int w = gameContainer.getWidth();
        int h = gameContainer.getHeight();



        graphics.setColor(FrameColor);
        graphics.fillRect(0, 0, w, h);
        graphics.setColor(PanelColor);
        graphics.fillRect(FRAME_WIDTH, FRAME_WIDTH, (float) width, (float) height);


    }


    private void drawMeasureLines(Graphics graphics) {
        graphics.setColor(MeasureLineColor);

        for (int i = 0; i < width; i += 50) {
            drawXLine(graphics,i);
            drawYLine(graphics,i);
        }
    }

    private void drawYLine(Graphics graphics, int i) {
        graphics.drawLine((float)FRAME_WIDTH-MeasureLineLength, (float) height + FRAME_WIDTH-i ,(float) (FRAME_WIDTH+MeasureLineLength),(float) height + FRAME_WIDTH-i );
        graphics.drawString(""+i,FRAME_WIDTH-graphics.getFont().getWidth(""+i)-2*MeasureLineLength,(float) height + FRAME_WIDTH - i-graphics.getFont().getHeight("i")/2);
    }

    private void drawXLine(Graphics graphics, int i) {
        graphics.drawLine(i+ FRAME_WIDTH,(float) (height+FRAME_WIDTH-MeasureLineLength),i+FRAME_WIDTH,(float)(height+FRAME_WIDTH+MeasureLineLength));
        graphics.drawString(""+i,i+ FRAME_WIDTH-graphics.getFont().getWidth(""+i)/2,(float) height + FRAME_WIDTH + 2*MeasureLineLength);
    }



    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int time) throws SlickException {
        if (System.currentTimeMillis()-startTime<WARMUPTIME)
            return;

        entities.forEach((e)->e.update(gameContainer,stateBasedGame,time));
    }


    public void add(GameEntity entity) {
        entities.add(entity);
    }
}
