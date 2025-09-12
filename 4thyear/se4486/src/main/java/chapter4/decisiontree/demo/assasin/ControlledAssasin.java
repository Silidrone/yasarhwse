package chapter4.decisiontree.demo.assasin;


import chapter2.StaticInfo;
import chapter2.Velocity;
import math.geom2d.Vector2D;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

public class ControlledAssasin extends Assasin {

    public ControlledAssasin(String name, StaticInfo staticInfo) {
        super(name,staticInfo);
    }


    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, float time) {
        super.update(gameContainer, stateBasedGame,time);

        if (!terminated)
            applyControls(gameContainer);
    }

    private void applyControls(GameContainer gameContainer) {

        int x , y ;
        x = gameContainer.getInput().isKeyDown(Input.KEY_LEFT) ? -3 : 0;
        x = gameContainer.getInput().isKeyDown(Input.KEY_RIGHT) ? 3 : x;

        y = gameContainer.getInput().isKeyDown(Input.KEY_UP) ? 3 :  0;
        y = gameContainer.getInput().isKeyDown(Input.KEY_DOWN) ? -3 : y;


        setVelocity(new Velocity(new Vector2D(x,y),0));
    }

}
