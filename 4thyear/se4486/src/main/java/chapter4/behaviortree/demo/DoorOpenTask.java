package chapter4.behaviortree.demo;


import chapter4.behaviortree.Task;


/**
 * Created by dindar.oz on 10/26/2017.
 */
public class DoorOpenTask implements Task<DemoExtData, DemoIntData> {
    @Override
    public boolean run(DemoExtData extData, DemoIntData intData) {
        if (extData.isDoorOpen()) {
            System.out.println("Door is open!");
            return true;
        }
        System.out.println("Door is not open!");
        return false;
    }
}
