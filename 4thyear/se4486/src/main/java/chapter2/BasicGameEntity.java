package chapter2;


import chapter2.base.GameEntity;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.geom.Rectangle2D;

public abstract class BasicGameEntity  implements GameEntity {

    protected StaticInfo staticInfo;
    protected Renderable body;


    public BasicGameEntity(StaticInfo staticInfo, Renderable body) {
        this.staticInfo = staticInfo;
        this.body = body;
    }

    public void setOrientation(double orientation)
    {
        staticInfo.setOrientation(orientation);
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame, Rectangle2D boundary) {

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {

        body.render(staticInfo, graphics);


    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, float time) {

    }

    public StaticInfo getStaticInfo() {
        return staticInfo;
    }
}
