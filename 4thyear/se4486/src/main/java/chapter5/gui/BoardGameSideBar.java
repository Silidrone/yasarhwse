package chapter5.gui;


import base.GameEntity;
import chapter5.boardgames.player.Player;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.Font;
import java.awt.geom.Rectangle2D;

public class BoardGameSideBar implements GameEntity {

    BoardGame boardGame;

    Rectangle2D boundary;
    private static Image DefaultLogo;
    private static Image TurnImage;
    private Color TextColor = Color.white;
    private Color BackgroundColor = Color.gray;
    private Color LineColor = Color.green;

    Image logos[];

    TrueTypeFont font;

    public BoardGameSideBar(BoardGame boardGame) {
        this.boardGame = boardGame;

    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame, Rectangle2D boundary) {
        float delta = (float) (boundary.getMaxX()-boundary.getMaxY());
        this.boundary = new Rectangle2D.Float((float) boundary.getMaxX()-delta-(float) boundary.getMinX(),0,delta, (float) ((float) boundary.getHeight()));
        logos = new Image[boardGame.players.size()];
        font = new TrueTypeFont(new Font("Verdana", Font.PLAIN, 20),false);

        try {
            DefaultLogo = new Image("./res/black_rook.png");
            TurnImage = new Image("./res/black_pawn.png");

            for (int i = 0; i < boardGame.players.size(); i++) {
                logos[i] = boardGame.players.get(i).getLogo();
            }
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        renderPlayers(graphics);
    }

    private void renderPlayers(Graphics graphics) {
        float x = (float) ((float) boundary.getMinX()+ 0.2*boundary.getWidth());
        float y = (float) boundary.getMaxY();
        float height = (float) (boundary.getHeight()/boardGame.players.size());

        for (int i = 0; i < boardGame.players.size(); i++) {
            Player p = boardGame.players.get(i);

          renderPlayer(graphics,p,x,y-(i+1)*height,height, logos[i]);


        }

    }

    private void renderPlayer(Graphics graphics, Player p, float x, float y, float height, Image playerImage) {

        graphics.setColor(BackgroundColor);
        graphics.fillRect((float) boundary.getMinX(),y, (float) boundary.getWidth(),height);
        graphics.setColor(LineColor);
        graphics.drawRect((float) boundary.getMinX(),y, (float) boundary.getWidth(),height);


        float imageTop = (float) (y + height*0.1);
        Image logo = playerImage==null ? DefaultLogo:playerImage;

        logo.draw((float) (x+ boundary.getWidth()*0.1),imageTop, (float) (height*0.3/logo.getHeight()));

        graphics.setColor(TextColor);


        graphics.setFont(font);
        graphics.drawString(p.getName(),x, y+(float) (0.4*height));


        if (boardGame.currentPlayer()==p)
        {
            TurnImage.draw(x, (float) (y+0.7*height), (float) (0.2*height/TurnImage.getHeight()));
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, float time) {

    }
}
