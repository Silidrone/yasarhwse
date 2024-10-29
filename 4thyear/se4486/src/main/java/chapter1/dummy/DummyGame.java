package chapter1.dummy;

import chapter1.gameengine.Game;

import java.security.SecureRandom;
import java.util.Random;

public class DummyGame implements Game {

    public static final int DELTA = 10;
    Random rng = new SecureRandom();

    int x,y;
    private int maxX;
    private int maxY;


    public DummyGame(int maxX, int maxY) {
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    @Override
    public boolean isGameOver() {
        return (x<0) || (y<0) || (x>maxX)|| (y>maxY) ;
    }

    @Override
    public void init() {
        x = rng.nextInt(maxX);
        y = rng.nextInt(maxY);
    }

    @Override
    public void update(long time) {

    }

    @Override
    public String getTitle() {
        return "Dummy Game 0.1";
    }

    public void up() {
        y--;
    }

    public void down() {
        y++;
    }
}
