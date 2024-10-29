package chapter1.dxball;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Level1TileGenerator implements DXTileGenerator {
    private static final double INITIAL_X = 100;
    private static final double INITIAL_Y = 100;
    private static final int ROW_COUNT = 3;
    private static final Color TileColor= Color.red;
    private static final int TileWidth = 15;
    private static final int TileHeight = 10;
    private static final int TileSpace= 10;

    @Override
    public List generate(DXBallArea dxBallArea) {

        List<DXTile> arrList = new ArrayList<>();
        double sX = dxBallArea.topLeft().getX() + INITIAL_X;
        double sY = dxBallArea.topLeft().getY() + INITIAL_Y;

        double eX= dxBallArea.getWidth()- TileWidth - INITIAL_X;

        for (int r = 0; r < ROW_COUNT; ) {
            DXTile tile = new DXTile(TileColor,sX,sY, TileWidth, TileHeight );
            arrList.add(tile);
            sX += TileWidth + TileSpace;
            if (sX >eX)
            {
                sX = dxBallArea.topLeft().getX() + INITIAL_X;
                sY += TileHeight + TileSpace;
                r++;
            }
        }

        return arrList;
    }
}
