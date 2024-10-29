package chapter2;

import org.newdawn.slick.Graphics;

public interface Renderable {
    void render(StaticInfo staticInfo, Graphics graphics);

    double getRadius();
}
