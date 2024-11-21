package chapter3.pathfinding.visibility;

import base.GameEntity;
import base.SimpleGame;
import base.SimplePanel;
import chapter3.pathfinding.Connection;
import chapter3.pathfinding.Graph;
import chapter3.pathfinding.Path;
import chapter3.pathfinding.WorldRepresentation;
import chapter3.pathfinding.alg.PathfindingAlgorithm;

import chapter3.pathfinding.alg.astar.hw2.AStarHW;
import chapter3.pathfinding.alg.astar.hw2.VisibilityGraphHW;
import chapter3.pathfinding.alg.dijkstra.Dijkstra;
import chapter3.pathfinding.gui.PathFindingPanel;
import math.geom2d.Point2D;
import math.geom2d.polygon.Polygon2D;
import math.geom2d.polygon.SimplePolygon2D;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.util.Bootstrap;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PolygonWorld implements WorldRepresentation<Point2D>, GameEntity {

    private static final  float DotRadius= 3;
    private static final Color DotColor= Color.black;
    private static final Color BG_COLOR = Color.white;
    private static final Color CON_COLOR = Color.blue;
    private static final double CLICK_THRESHOLD = 5;
    private static final Color SELECTION_COLOR = Color.red;
    private static final float SELECTION_RADIUS = 5;
    private static final Color TEXT_COLOR = Color.black;

    Graph2D graph;

    private List<Path<Point2D>> paths;

    private Point2D start;
    private Point2D end;

    private Point2D selected;

    float width;
    float height;
    private Color[] PolyColors= {Color.green,Color.yellow,Color.red,Color.pink, Color.magenta, Color.cyan};
    private Color[] PathColors = new Color[]{Color.black, Color.red, Color.magenta,Color.green,Color.yellow};
    private boolean renderConnections = false;


    public PolygonWorld(Graph2D graph, float width, float height) {
        this.graph = graph;
        this.width = width;
        this.height = height;
    }

    @Override
    public Point2D quantize(java.awt.geom.Point2D p) {
        throw new NotImplementedException();
    }

    @Override
    public java.awt.geom.Point2D localize(Point2D point2D) {
        return new java.awt.geom.Point2D.Double(point2D.x(),point2D.y());
    }

    @Override
    public void addPath(Path<Point2D> path) {
        paths.add(path);
    }

    @Override
    public Point2D getStart() {
        return start;
    }

    @Override
    public Point2D getEnd() {
        return end;
    }

    @Override
    public void removePaths() {
        paths.clear();
    }

    @Override
    public void keyPressed(int key, char c) {
        if (key== Input.KEY_ENTER)
            renderConnections = !renderConnections;
        else if (key== Input.KEY_S && selected!=null)
            setStart(selected);
        else if (key== Input.KEY_E && selected!=null)
            setEnd(selected);
    }

    private void setEnd(Point2D selected) {
        end = selected;
    }

    private void setStart(Point2D selected) {
        start = selected;
    }

    @Override
    public void mouseClicked(int button, int x, int y, int clickCount) {
        if (button == Input.MOUSE_LEFT_BUTTON)
        {
            Point2D mp = new Point2D(x- SimplePanel.FRAME_WIDTH,y-SimplePanel.FRAME_WIDTH);
            for (Point2D p: graph.getNodes())
            {
                if (p.almostEquals(mp,CLICK_THRESHOLD))
                {
                    select(p);
                    return;
                }
            }
        }
    }

    private void select(Point2D p) {
        if (selected != null && selected.almostEquals(p,CLICK_THRESHOLD))
            return;

        selected = p;
    }

    @Override
    public Graph<Point2D> getGraph() {
        return graph;
    }

    @Override
    public void update(PathfindingAlgorithm<Point2D> algorithm) {

    }

    @Override
    public void reset() {
        start = null;
        end = null;
        selected = null;
    }

    @Override
    public boolean isVisible(Point2D head, Point2D end) {
        return GeomUtils.isVisible(head,end,graph.getPolygons());
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame, Rectangle2D boundary) {
        selected = null;
        start = null;
        end= null;
        paths = new ArrayList<>();
        renderConnections = false;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) {
        gameContainer.getGraphics().setAntiAlias(true);
        gameContainer.getGraphics().setLineWidth(0.3f);
        graphics.setColor(BG_COLOR);
        graphics.fillRect(0,0,width,height);

        renderPolygons(graphics,graph.getPolygons());
        if (renderConnections)
            renderConnections(graphics,graph.getConnections());
        graph.getNodes().forEach((p)->renderPoint(graphics,p));
        renderSpecialNodes(graphics);
        renderPaths(graphics);
    }

    private void renderPaths(Graphics graphics) {
        for (int p = 0; p < paths.size(); p++) {
            graphics.setColor(PathColors[p%PathColors.length]);
            renderPath(graphics,paths.get(p));
        }
    }

    private void renderPath(Graphics graphics, Path<Point2D> path) {
        List<Connection<Point2D>> connections = path.path();
        for(Connection<Point2D> connection:connections)
        {
            renderConnection(graphics,connection);
        }
    }

    private void renderConnection(Graphics graphics, Connection<Point2D> connection) {

        float lw = graphics.getLineWidth();
        graphics.setLineWidth(3.0f);
        graphics.drawLine((float) connection.from().getX(),(float)connection.from().getY(),(float)connection.to().getX(),(float)connection.to().getY());
        graphics.setLineWidth(lw);
    }

    private void renderSpecialNodes(Graphics graphics) {
        if (selected!=null) {
            float cx = (float) (selected.x() - SELECTION_RADIUS);
            float cy = (float) (selected.y() - SELECTION_RADIUS);
            graphics.setColor(SELECTION_COLOR);
            graphics.drawOval(cx, cy, 2 * SELECTION_RADIUS, 2 * SELECTION_RADIUS);
        }
        graphics.setColor(TEXT_COLOR);
        if (start != null)
        {
            float x= (float) (start.x()-graphics.getFont().getWidth("S")/2.0);
            float y= (float) (start.y()-graphics.getFont().getHeight("S")*2.0);

            graphics.drawString("S", x, y);
        }
        if (end != null)
        {
            float x= (float) (end.x()-graphics.getFont().getWidth("E")/2.0);
            float y= (float) (end.y()-graphics.getFont().getHeight("E")*2.0);
            graphics.drawString("E", x, y);
        }
    }

    private void renderConnections(Graphics graphics, List<? extends Connection<Point2D>> connections) {
        graphics.setColor(CON_COLOR);
        HashMap<Point2D, List<Point2D>> lines = new HashMap<>();
        for (Connection<Point2D> lc:connections)
        {
            lines.putIfAbsent(lc.from(),new ArrayList<>());
            lines.putIfAbsent(lc.to(),new ArrayList<>());
            if (!lines.get(lc.to()).contains(lc.from())) {
                graphics.drawLine((float) lc.from().x(), (float) lc.from().y(), (float) lc.to().x(), (float) lc.to().y());
                lines.get(lc.from()).add(lc.to());
            }
        }
    }

    private void renderPolygons(Graphics graphics, List<Polygon2D> polygons) {
        int pc=0;
        for (Polygon2D p:polygons)
        {
            Color color = PolyColors[(pc++)%PolyColors.length];
            renderPolygon(graphics, p, color);
        }
    }

    private void renderPolygon(Graphics graphics, Polygon2D p, Color color) {
        float vertices[] = new float[p.vertexNumber()*2];
        for (int i = 0; i < p.vertexNumber(); i++) {
            vertices[2*i]= (float) p.vertex(i).x();
            vertices[2*i+1]= (float) p.vertex(i).y();
        }
        graphics.setColor(color);
        graphics.fill(new Polygon(vertices));

        //p.vertices().forEach((v)->renderPoint(graphics,v));

    }

    private void renderPoint(Graphics graphics, Point2D p) {
        graphics.setColor(DotColor);
        float x = (float) (p.x()-DotRadius);
        float y = (float) (p.y()-DotRadius);
        graphics.fillOval(x,y,2*DotRadius,2*DotRadius);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, float time) {

    }

    public static void main(String[] args) {
        int width = 1000;
        int height = 800;

        List<Polygon2D> polygon2DS = Arrays.asList(
                new SimplePolygon2D(new Point2D(100,100), new Point2D(100,50), new Point2D(150,50)),
                new SimplePolygon2D(new Point2D(300,300), new Point2D(450,300), new Point2D(450,450), new Point2D(300,450)),
                new SimplePolygon2D(new Point2D(600,100), new Point2D(500,250), new Point2D(550,350), new Point2D(650,300), new Point2D(750,150)));


        List<Point2D> point2DS = Arrays.asList( new Point2D(400,10),
                new Point2D(20,400),
                new Point2D(10,10),
                new Point2D(750,350));

        Graph2D vg = new VisibilityGraphHW(point2DS,polygon2DS);

        PathfindingAlgorithm<Point2D> dijkstra =  new Dijkstra<>();
        PathfindingAlgorithm<Point2D> astarhw =  new AStarHW<>((g,n,t)->0);

        PolygonWorld pw = new PolygonWorld(vg,width- 2*SimplePanel.FRAME_WIDTH,height-2*SimplePanel.FRAME_WIDTH);
        PathFindingPanel<Point2D> pathFindingPanel = new PathFindingPanel<>(pw, Arrays.asList(dijkstra));

        SimpleGame demo = new SimpleGame("Path Finding Demo", pathFindingPanel);
        Bootstrap.runAsApplication(demo,width,height,false);
    }
}
