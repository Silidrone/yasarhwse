package chapter3.pathfinding.gui;


import base.GameEntity;
import base.SimpleGame;
import base.SimplePanel;
import chapter3.pathfinding.Path;
import chapter3.pathfinding.WorldRepresentation;
import chapter3.pathfinding.alg.PathfindingDemo;
import chapter3.pathfinding.alg.dijkstra.Dijkstra;
import chapter3.pathfinding.alg.PathfindingAlgorithm;
import chapter3.pathfinding.pathsmoothing.PathSmoother;
import chapter3.pathfinding.tilemap.Tile;
import chapter3.pathfinding.tilemap.TileMap;
import org.newdawn.slick.Input;
import org.newdawn.slick.util.Bootstrap;

import java.util.Arrays;
import java.util.List;

public class PathFindingPanel<T> extends SimplePanel {

    WorldRepresentation<T> worldRepresentation;
    List<PathfindingAlgorithm<T>> pathFinders;
    PathSmoother<T> pathSmoother;


    PathfindingDemo<T> demoAlgorithm;
    boolean demoIsOn = false;

    public PathFindingPanel(WorldRepresentation<T> worldRepresentation, List<PathfindingAlgorithm<T>> pathFinders) {
        this.worldRepresentation = worldRepresentation;
        this.pathFinders = pathFinders;
        addEntity((GameEntity) worldRepresentation);
    }

    void setPathSmoother(PathSmoother<T> smoother)
    {
        this.pathSmoother = smoother;
    }

    @Override
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);

        if (key == Input.KEY_D && !demoIsOn)
        {
            startDemo();
            worldRepresentation.keyPressed(key,c);
        }
        if (key == Input.KEY_ESCAPE && demoIsOn)
        {
            stopDemo();
        }
        if (key == Input.KEY_RIGHT && demoIsOn)
        {
            demoAlgorithm.step();
            worldRepresentation.update(demoAlgorithm);
            demoIsOn =  !demoAlgorithm.isOver();
            if (!demoIsOn) {
                worldRepresentation.addPath(demoAlgorithm.getPath());
                if (pathSmoother!=null)
                {
                    Path<T> smoothPath = pathSmoother.smooth(worldRepresentation, demoAlgorithm.getPath());
                    worldRepresentation.addPath(smoothPath);
                }
            }
        }
        
        if (key == Input.KEY_SPACE)
        {
            findPath();
        }
        if (key == Input.KEY_X)
        {
            worldRepresentation.removePaths();
            if (demoAlgorithm!= null)
                demoAlgorithm.reset();
            worldRepresentation.reset();
        }

        worldRepresentation.keyPressed(key,c);


    }



    private void stopDemo() {
        demoIsOn = false;
    }

    private void startDemo() {
        if (pathFinders.get(0) instanceof PathfindingDemo) {
            demoAlgorithm = (PathfindingDemo<T>) pathFinders.get(0);
            demoAlgorithm.initDemo(worldRepresentation.getGraph(), worldRepresentation.getStart(), worldRepresentation.getEnd());
            demoIsOn = true;
        }
    }

    private void findPath() {
        T s = worldRepresentation.getStart();
        T e = worldRepresentation.getEnd();
        if (s==null || e==null)
            return;

        for ( PathfindingAlgorithm<T> pathFinder: pathFinders) {
            Path<T> path = pathFinder.findPath(worldRepresentation.getGraph(), s, e);
            if (path!= null)
                worldRepresentation.addPath(path);
        }


    }

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        super.mouseClicked(button, x, y, clickCount);

        worldRepresentation.mouseClicked(button,x,y,clickCount);
    }

    public static void main(String[] args) {

        TileMap tileMap = new TileMap(20,27,37);
        tileMap.putObstacle(5,5);
        tileMap.putObstacle(25,13);
        PathfindingAlgorithm<Tile> dijkstra =  new Dijkstra<>();

        PathFindingPanel<Tile> pathFindingPanel = new PathFindingPanel<>(tileMap, Arrays.asList(dijkstra));


        SimpleGame demo = new SimpleGame("Path Finding Demo", pathFindingPanel);


        Bootstrap.runAsApplication(demo,800,600,false);

    }
}
