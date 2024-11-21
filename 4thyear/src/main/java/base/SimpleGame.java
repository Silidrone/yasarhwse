package base;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


public class SimpleGame extends StateBasedGame {
    public SimpleGame(String name, BasicGameState mainState) {
        super(name);
        addState(mainState);
    }

    @Override
    public void initStatesList(GameContainer gameContainer) throws SlickException {
        enterState(0);
    }


}
