package chapter4.behaviortree.demo;


import chapter4.behaviortree.Task;


/**
 * Created by dindar.oz on 10/26/2017.
 */
public class MoveToDoorTask implements Task<DemoExtData, DemoIntData> {
    @Override
    public boolean run(DemoExtData extData, DemoIntData intData) {
        System.out.println("Moving to the door...");
        return true;
    }
}
