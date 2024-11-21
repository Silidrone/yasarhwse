package chapter3.pathfinding.tilemap;

import org.newdawn.slick.Color;

public enum  TileType {

    Empty, Obstacle, Start, End, OpenList, ClosedList, Visited;

    public Color toColor() {

        switch (this)
        {
            case Empty : return Color.white;
            case Obstacle: return Color.black;
            case Start: return Color.blue;
            case End: return Color.orange;
            case OpenList:return Color.green;
            case ClosedList:return Color.cyan;
            case Visited:return Color.red;
        }

        return Color.white;
    }
}
