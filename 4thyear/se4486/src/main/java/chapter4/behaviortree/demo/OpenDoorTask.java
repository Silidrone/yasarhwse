package chapter4.behaviortree.demo;


import chapter4.behaviortree.Task;


/**
 * Created by dindar.oz on 10/26/2017.
 */
public class OpenDoorTask implements Task<DemoExtData, DemoIntData> {
    @Override
    public boolean run(DemoExtData extData, DemoIntData intData) {
        if(extData.isDoorLocked()) {
            if(!intData.hasKey()) {
                System.out.println("I don't have a key to unlock the door!");
                return false;
            } else {
                System.out.println("Unlocking the door!");
            }
        }
        System.out.println("Opening the door!");
        extData.setDoorState(DemoExtData.DoorState.Open);
        return true;
    }
}
