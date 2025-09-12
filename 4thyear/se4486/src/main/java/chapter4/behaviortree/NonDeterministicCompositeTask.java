package chapter4.behaviortree;
import java.util.Collections;
import java.util.List;

public class NonDeterministicCompositeTask<E,I> implements Task<E,I> {

    CompositeTask<E,I> child;

    public NonDeterministicCompositeTask( CompositeTask<E, I> child) {
        super();
        this.child = child;
    }

    @Override
    public boolean run(E extData, I intData) {

        List<Task<E,I>> children = child.getChildren();
        Collections.shuffle(children);

        return  child.run(extData,intData);
    }
}
