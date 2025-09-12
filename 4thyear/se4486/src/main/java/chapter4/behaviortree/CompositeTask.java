package chapter4.behaviortree;

import java.util.ArrayList;
import java.util.List;

public abstract class CompositeTask<E,I> implements Task<E,I> {
    List<Task<E,I>> children;

    public CompositeTask() {
        children = new ArrayList<>();
    }

    public CompositeTask(List<Task<E,I>> children) {
        this.children = children;
    }

    public void addChild(Task<E,I> task)
    {
        children.add(task);
    }

    protected List<Task<E, I>> getChildren() {
        return children;
    }
}
