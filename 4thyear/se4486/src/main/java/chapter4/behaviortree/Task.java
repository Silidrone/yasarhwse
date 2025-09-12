package chapter4.behaviortree;

public interface Task<E,I> {

    boolean run(E extData, I intData);

}
