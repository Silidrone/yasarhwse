package chapter4.behaviortree;


import java.util.ArrayList;
import java.util.List;

public class Selector<E,I> extends CompositeTask<E,I> {

    public Selector() {
        children = new ArrayList<>();
    }

    public Selector(List<Task<E, I>> children) {
        super(children);
    }

    @Override
    public boolean run(E extData, I intData) {
        for (Task<E,I> task:children)
        {
            if (task.run(extData,intData))
                return true;
        }

        return false;
    }
}
