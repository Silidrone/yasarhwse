package chapter1.gameengine;

public interface Game {

    boolean isGameOver();

    void init();
    void update(long time);

    String getTitle();
}
