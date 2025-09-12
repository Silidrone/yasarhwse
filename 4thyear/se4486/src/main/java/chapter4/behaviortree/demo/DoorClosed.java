package chapter4.behaviortree.demo;

import chapter4.behaviortree.Task;

public class DoorClosed implements Task<DemoExtData,DemoIntData> {
    @Override
    public boolean run(DemoExtData extData, DemoIntData intData) {
        System.out.println("Is door unlocked!");

        if (extData.isDoorLocked())
            System.out.println("Door is not unLocked");

        return extData.doorState== DemoExtData.DoorState.Closed;

    }
}
