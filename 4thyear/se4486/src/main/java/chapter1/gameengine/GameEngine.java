package chapter1.gameengine;

public interface GameEngine<G extends Game> {
    void start(G game, GameViewer<G> gameViewer);

}
