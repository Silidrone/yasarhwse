package chapter4.behaviortree.demo;

import chapter4.behaviortree.Task;

public class BurnDoorTask implements Task<DemoExtData,DemoIntData> {
    @Override
    public boolean run(DemoExtData extData, DemoIntData intData) {

        System.out.println("Burning the door!");
        extData.setDoorState(DemoExtData.DoorState.Open);

        return true;
    }
}
