package chapter4.behaviortree.demo;

import chapter4.behaviortree.Task;

public class Inverter<E,I> implements Task<E,I> {


    private Task<E,I> child;

    public Inverter(Task<E, I> child) {
        this.child = child;
    }

    @Override
    public boolean run(E extData, I intData) {
        return ! child.run(extData,intData);
    }
}
