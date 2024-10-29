package chapter1.gameengine;

import chapter1.gameengine.swing.JFrameContainer;

import java.awt.*;

public abstract class JFrameGameViewer<G extends Game> implements GameViewer<G> {

    JFrameContainer container;
    protected int width;
    protected int height;


    public JFrameGameViewer(G game, int width, int height) {
        this.width = width;
        this.height = height;
        container = new JFrameContainer(game.getTitle(),width, height);
    }

    @Override
    public void render(G game) {
        Graphics g =  container.getGraphics();

        _renderGame(g,game);

        container.disposeGraphics();
    }

    protected abstract void _renderGame(Graphics g, G game);

    @Override
    public void dispose() {
        container.dispose();
    }

    @Override
    public void init(G game) {
        container.init();
    }

    public void setTitle(String  title)
    {
        container.setTitle(title);
    }

    public void addController(GameController controller)
    {
        container.addMouseListener(controller);
        container.addKeyListener(controller);
        container.addMouseMotionListener(controller);
    }
}
