import rl.tasks.grid2D.Grid2DModel;

public class GridFactory {

    static Grid2DModel createTestGrid()
    {
        Grid2DModel grid2D = new Grid2DModel(3,4);
        grid2D.setTerminal(0,3,5);
        grid2D.setTerminal(1,3,-10);
        grid2D.setObstacle(1,1);
        return grid2D;
    }
}
