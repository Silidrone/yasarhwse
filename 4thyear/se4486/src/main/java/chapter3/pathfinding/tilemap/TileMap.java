package chapter3.pathfinding.tilemap;


import base.GameEntity;
import base.SimplePanel;
import chapter3.pathfinding.Connection;
import chapter3.pathfinding.Graph;
import chapter3.pathfinding.Path;
import chapter3.pathfinding.WorldRepresentation;
import chapter3.pathfinding.alg.PathfindingAlgorithm;
import chapter3.pathfinding.alg.PathfindingDemo;
import chapter3.pathfinding.grid.GridGraph;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class TileMap implements WorldRepresentation<Tile>, GameEntity {

    GridGraph<Tile> graph;
    Point2D topLeft;
    Tile tiles[][];

    float tileSize;
    private List<Path<Tile>> paths;
    private static final  float DotRadius= 5;
    private Tile start;
    private Tile end;




    Tile selected;
    private Color[] PathColors = new Color[]{Color.black, Color.red, Color.magenta,Color.green,Color.yellow};

    public TileMap(float tileSize, int rowCount, int colCount) {
        this.topLeft = new Point2D.Float(0,0);
        this.tileSize = tileSize;

        paths = new ArrayList<>();

        createTiles(rowCount,colCount);

    }


    public TileMap(Point2D topLeft, float tileSize, int rowCount, int colCount) {
        this.topLeft = topLeft;
        this.tileSize = tileSize;

        createTiles(rowCount,colCount);
    }



    private void createTiles(int rowCount, int colCount) {
        tiles = new Tile[rowCount][colCount];

        for (int r = 0; r < rowCount; r++) {
            for (int c = 0; c < colCount; c++) {
                float dx = (float) (topLeft.getX()+ tileSize*c);
                float dy = (float) (topLeft.getY()+ tileSize*r);

                tiles[r][c] = new Tile(TileType.Empty, new Point2D.Float(dx,dy),r,c,tileSize);
            }
        }
        graph = new GridGraph<>(tiles,tileSize);
    }



    @Override
    public Tile quantize(Point2D p) {

        int row = (int) ((p.getY()-topLeft.getY()- SimplePanel.FRAME_WIDTH)/tileSize);
        int col = (int) ((p.getX()-topLeft.getX()- SimplePanel.FRAME_WIDTH)/tileSize);


        return graph.getNode(row,col);
    }

    @Override
    public Point2D localize(Tile gridNode) {
        return tiles[gridNode.getRow()][gridNode.getCol()].center();
    }

    @Override
    public void addPath(Path path) {
        paths.add(path);
    }

    @Override
    public Tile getStart() {
        return start;
    }

    @Override
    public Tile getEnd() {
        return end;
    }

    @Override
    public void removePaths() {
        paths.clear();
    }

    @Override
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_D)
        {
            select(null);
        }
        if (key == Input.KEY_S)
        {
            if (selected != null)
            {
                setStart(selected);
            }
        }
        if (key == Input.KEY_E)
        {
            if (selected != null)
            {
                setEnd(selected);
            }
        }

        if (key == Input.KEY_O)
        {
            if (selected != null)
            {
                if (tiles[selected.getRow()][selected.getCol()].getType()== TileType.Empty) {
                    putObstacle(selected.getRow(),selected.getCol());
                }
                else if (tiles[selected.getRow()][selected.getCol()].getType()== TileType.Obstacle) {
                    removeObstacle(selected.getRow(),selected.getCol());
                }
            }
        }
        if ((key == Input.KEY_UP || key == Input.KEY_DOWN||key == Input.KEY_LEFT||key == Input.KEY_RIGHT) && selected!=null)
            moveSelected(key);
    }

    private void moveSelected(int key) {
        Tile target= null;
        if (key == Input.KEY_UP)
            target = graph.upOf(selected);
        else if (key == Input.KEY_DOWN)
            target = graph.downOf(selected);
        else if (key == Input.KEY_LEFT)
            target = graph.leftOf(selected);
        else if (key == Input.KEY_RIGHT)
            target = graph.rightOf(selected);

        if (target!=null)
            select(target);


    }




    private void setStart(Tile tile) {
        if (tile.getType() == TileType.Obstacle || tile.getType()== TileType.End )
            return;

        if (start != null)
            tiles[start.getRow()][start.getCol()].setType(TileType.Empty);

        start = tile;
        tile.setType(TileType.Start);
    }
    private void setEnd(Tile tile) {
        if (tile.getType() == TileType.Obstacle || tile.getType()== TileType.Start )
            return;

        if (end != null)
            tiles[end.getRow()][end.getCol()].setType(TileType.Empty);

        end = tile;
        tile.setType(TileType.End);

    }


    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        if (button == Input.MOUSE_LEFT_BUTTON)
        {
            Tile tile = quantize(new Point2D.Float(x,y));

            select(tile);
        }
    }

    @Override
    public Graph<Tile> getGraph() {
        return graph;
    }


    @Override
    public void update(PathfindingAlgorithm<Tile> algorithm) {
        PathfindingDemo<Tile> demo = (PathfindingDemo<Tile>) algorithm;
        for (Tile t: demo.openList())
            if (t.getType()==TileType.Empty || t.getType()==TileType.Visited)
                t.setType(TileType.OpenList);
        for (Tile t:demo.closedList())
            if (t.getType()==TileType.Empty || t.getType()==TileType.OpenList || t.getType()==TileType.Visited)
                t.setType(TileType.ClosedList);
        demo.current().setType(TileType.Visited);
    }

    @Override
    public void reset() {
        for (int r = 0; r < tiles.length; r++) {
            for (int c = 0; c < tiles[r].length; c++) {
                if (tiles[r][c].getType()!= TileType.Obstacle)
                    tiles[r][c].setType(TileType.Empty);
            }
        }
    }

    @Override
    public boolean isVisible(Tile head, Tile end) {
        Point2D p1 = head.center();
        Point2D p2 = end.center();

        int rmin = Math.min(head.getRow(), end.getRow());
        int rmax = Math.max(head.getRow(), end.getRow());
        int cmin = Math.min(head.getCol(), end.getCol());
        int cmax = Math.max(head.getCol(), end.getCol());

        for (int r = rmin; r <=rmax ; r++) {
            for (int c = cmin; c <= cmax; c++) {
                if (tiles[r][c].tileType==TileType.Obstacle)
                {
                    Rectangle2D rect = tiles[r][c].getRect();
                    Line2D line = new Line2D.Double(p1,p2);
                    if (line.intersects(rect))
                        return false;
                }
            }
        }
        return true;
    }

    private void select(Tile tile) {
        if (selected!= null)
            tiles[selected.getRow()][selected.getCol()].setSelected(false);
        selected = tile;
        if (selected!=null)
            selected.setSelected(true);

    }

    public void putObstacle(int row, int col)
    {
      // Check here there is no start and end

        tiles[row][col].setType(TileType.Obstacle);
        graph.disableNode(row,col);
    }

    public void removeObstacle(int row, int col)
    {
        if (tiles[row][col].getType()== TileType.Obstacle) {
            tiles[row][col].setType(TileType.Empty);
            graph.enableNode(row, col);
        }
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame, Rectangle2D boundary) {
        for (int r = 0; r < tiles.length; r++) {
            for (int c = 0; c < tiles[0].length; c++) {
                tiles[r][c].init(gameContainer,stateBasedGame,boundary);
            }
        }
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        for (int r = 0; r < tiles.length; r++) {
            for (int c = 0; c < tiles[0].length; c++) {
                tiles[r][c].render(gameContainer,stateBasedGame,graphics);
            }
        }




        renderPaths(graphics);
    }


    private void renderPaths(Graphics graphics) {
        for (int p = 0; p < paths.size(); p++) {
            graphics.setColor(PathColors[p%PathColors.length]);
            renderPath(graphics,paths.get(p));
        }
    }

    private void renderPath(Graphics graphics, Path path) {

        List<Connection> connections = path.path();
        for(Connection<Tile> connection:connections)
        {
            renderConnection(graphics,connection);
        }
    }

    private void renderConnection(Graphics graphics, Connection<Tile> connection) {
        Point2D start = localize( connection.from());
        Point2D end = localize( connection.to());

        //graphics.setColor(Color.black);

        float sx = (float) (start.getX()-DotRadius);
        float sy = (float) (start.getY()-DotRadius);

        float ex = (float) (end.getX()-DotRadius);
        float ey = (float) (end.getY()-DotRadius);

        graphics.fillOval(sx,sy,2*DotRadius, 2*DotRadius);
        graphics.fillOval(ex,ey,2*DotRadius, 2*DotRadius);

        float lw = graphics.getLineWidth();
        graphics.setLineWidth(3.0f);
        graphics.drawLine((float) start.getX(),(float)start.getY(),(float)end.getX(),(float)end.getY());
        graphics.setLineWidth(lw);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, float time) {
        for (int r = 0; r < tiles.length; r++) {
            for (int c = 0; c < tiles[0].length; c++) {
                tiles[r][c].update(gameContainer,stateBasedGame,time);
            }
        }
    }

    public double getTileSize() {
        return tileSize;
    }
}
