package chapter2;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public abstract class BasicRenderable implements Renderable {

    Color color;

    public BasicRenderable(Color color) {
        this.color = color;
    }

    @Override
    public void render(StaticInfo staticInfo, Graphics graphics) {
        Color c = graphics.getColor();

        graphics.setColor(color);

        _render(staticInfo,graphics);

        graphics.setColor(c);
    }

    protected abstract void _render(StaticInfo staticInfo, Graphics graphics);

    public Color getColor() {
        return color;
    }
}
