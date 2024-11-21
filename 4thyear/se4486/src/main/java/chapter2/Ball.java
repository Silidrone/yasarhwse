package chapter2;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class Ball extends BasicRenderable {

    private final float DefaultRadius = 10;
    float radius= DefaultRadius;

    public Ball(Color color) {
        super(color);
    }

    public Ball(Color color, float radius) {
        super(color);
        this.radius = radius;
    }

    @Override
    protected void _render(StaticInfo staticInfo, Graphics graphics) {

        graphics.fillOval( (float)staticInfo.pos.x()-radius, (float) staticInfo.pos.y()-radius, 2*radius,2*radius);


        float x = (float)staticInfo.pos.x();
        float y = (float) staticInfo.pos.y();

        float ex = (float) (x + radius*Math.cos(staticInfo.orientation));
        float ey = (float) (y + radius*Math.sin(staticInfo.orientation));


        graphics.setColor(Color.black);

        graphics.drawLine(x,y,ex,ey);



    }

    @Override
    public double getRadius() {
        return radius;
    }


}