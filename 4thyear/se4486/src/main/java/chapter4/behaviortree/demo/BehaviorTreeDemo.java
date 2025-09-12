package chapter4.behaviortree.demo;


import chapter4.behaviortree.*;


import java.security.SecureRandom;
import java.util.*;

public class BehaviorTreeDemo {
    static Random rng = new SecureRandom();

    private static DemoExtData generateExternalData() {
        DemoExtData extData = new DemoExtData();
        extData.setDoorState(DemoExtData.DoorState.Locked);
        return extData;
    }

    private static DemoIntData generateInternalData() {
        return new DemoIntData(false);
    }

    private static Task<DemoExtData, DemoIntData> buildDemoBT() {
        DoorOpenTask doorOpen = new DoorOpenTask();
        MoveInTask moveIn = new MoveInTask();
        MoveToDoorTask moveToDoor = new MoveToDoorTask();
        OpenDoorTask openDoor = new OpenDoorTask();

        NonDeterministicCompositeTask<DemoExtData, DemoIntData> randomBarge = new NonDeterministicCompositeTask<>(new FirstActionSelector<>(Arrays.asList(new BargeDoorTask(), new FailTask())));

        CompositeTask<DemoExtData, DemoIntData> doorDecision = new Selector<>(Arrays.asList(doorOpen, openDoor, randomBarge));
        return new Sequence<>(Arrays.asList(moveToDoor, doorDecision, moveIn));
    }

    public static void main(String[] args) {
        Task<DemoExtData, DemoIntData> bt = buildDemoBT();
        DemoExtData extData = generateExternalData();
        DemoIntData intData = generateInternalData();

        bt.run(extData, intData);
    }


}
