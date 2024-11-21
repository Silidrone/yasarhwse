package chapter1;

import chapter1.gameengine.Game;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public interface GameEntity<G extends Game> {

    Point2D topLeft();
    Rectangle2D boundingRect();

    void init( G game );
    void update(G game, long time);
    void render(G game, Graphics g);

}
