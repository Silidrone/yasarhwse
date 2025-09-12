package chapter5.boardgames.player;


import chapter5.boardgames.base.Board;
import chapter5.boardgames.base.Move;
import org.newdawn.slick.Image;

public interface Player {
    Image getLogo();
    String getName();
    PlayerType getType();
    void init(Board board);

    boolean hasMove();

    Move getMove();


}
