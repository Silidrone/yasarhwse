package chapter4.behaviortree.demo;


import chapter4.behaviortree.Task;


/**
 * Created by dindar.oz on 10/26/2017.
 */
public class DoorLockedTask implements Task<DemoExtData, DemoIntData> {
    @Override
    public boolean run(DemoExtData extData,DemoIntData intData) {
        if (extData.isDoorLocked()) {
            System.out.println("Door is locked!");
            return true;
        }
        System.out.println("Door is not locked!");
        return false;
    }
}
