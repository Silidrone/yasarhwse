package chapter1.gameengine;




public class SimpleGameEngine<G extends Game> implements GameEngine<G> {

    private final long UpdateInterval=10;

    G game;
    GameViewer<G> viewer;

    private long startTime;
    private long elapsed;
    private long lastUpdate;
    private long renderCount;
    private int updateCount;
    private long lastStatsUpdate;
    private long lastRenderCount;
    private long lastUpdateCount;


    @Override
    public void start(G game, GameViewer<G> gameViewer) {
        this.game = game;
        this.viewer = gameViewer;

        Thread gameThread = new Thread(this::run);
        gameThread.start();
    }

    private void run() {
        init();

        while( !game.isGameOver())
        {
            update();
            render();

            updateStats();
        }

        dispose();

    }

    private void updateStats() {
        long now = System.currentTimeMillis();

        if (now- lastStatsUpdate>1000) // One second
        {
            int fps = (int) (renderCount-lastRenderCount);
            int ups = (int) (updateCount- lastUpdateCount);
            lastUpdateCount = updateCount;
            lastRenderCount = renderCount;

            String title = game.getTitle() + "   |  FPS: "+ fps + "  UPS: "+ ups;
            viewer.setTitle(title);

            lastStatsUpdate = now;
        }
    }

    private void update() {
        long now = System.currentTimeMillis();
        elapsed += now-lastUpdate;

        lastUpdate = now;

        while (elapsed> UpdateInterval)
        {
            game.update(UpdateInterval);
            elapsed-=UpdateInterval;
            updateCount++;
        }


    }

    private void render() {
        viewer.render(game);
        renderCount++;
    }

    private void dispose() {
        viewer.dispose();
    }

    private void init() {
        game.init();
        viewer.init(game);

        startTime = System.currentTimeMillis();
        lastUpdate = startTime;
        lastStatsUpdate = startTime;

        renderCount=0;
        elapsed =0;
        updateCount =0;


    }



}
