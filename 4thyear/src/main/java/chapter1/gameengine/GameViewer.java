package chapter1.gameengine;

public interface GameViewer<G extends Game>  {
    void render(G game);

    void dispose();

    void init(G game);

    void setTitle(String title);

    void addController(GameController<G> controller);
}
