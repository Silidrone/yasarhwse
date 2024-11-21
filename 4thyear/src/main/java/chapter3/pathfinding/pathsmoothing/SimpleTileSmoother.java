package chapter3.pathfinding.pathsmoothing;

import chapter3.pathfinding.*;

import chapter3.pathfinding.tilemap.Tile;

import java.util.Collections;

public class SimpleTileSmoother implements PathSmoother<Tile> {
    @Override
    public Path<Tile> smooth(WorldRepresentation<Tile> world, Path<Tile> path) {
        if (path.path().isEmpty())
            return new ListPath<>(Collections.emptyList(),0);
        ListPath<Tile> smooth= new ListPath<>();
        smooth.addConnection(path.path().get(0));

        for (int i = 1; i < path.path().size() ; i++) {
            Tile head = smooth.getLast().from();
            Connection<Tile> current= path.path().get(i);
            if (world.isVisible(head,current.to()))
            {
                Connection<Tile> last =smooth.remove(smooth.size()-1);

                double distance = world.localize(head).distance(world.localize(current.to()));
                smooth.addConnection(new SimpleConnection<>(head,current.to(),distance));
            }
            else {
                smooth.addConnection(current);

            }
        }

        return smooth;
    }
}
