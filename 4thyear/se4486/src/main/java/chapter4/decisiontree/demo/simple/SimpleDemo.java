package chapter4.decisiontree.demo.simple;

import chapter4.decisiontree.DecisionTreeNode;
import math.geom2d.Vector2D;

public class SimpleDemo {


    /**
     * TODO: 1- Create a decision tree as follows:
     *  DT:
     *                VisibilityTest
     *                 /            \
     *                T              F
     *               /                \
     *         AtackableTest           AlarmExpired
     *            /     \                      /   \
     *           T       F                    T     F
     *          /         \                  /       \
     *       [Attack]   ChaseableTest    [Patrol]    [Search]
     *                  /        \
     *                 T          F
     *                /            \
     *            [Chase]        [Watch]
     *
     * TODO: 2 - How can we connect decision tests to strategy layer(Think about thresholds in tests)
     *
     * @param args
     */
    public static void main(String[] args) {

        DecisionTreeNode<SimpleGameExtData,SimpleGameIntData> root =
                new VisibilityTest(
                                new AttackabilityTest(
                                        new Attack(),
                                        new ChaseabilityTest(
                                                 new Chase(),
                                                 new Watch())),
                                new AllarmExpiredTest(
                                        new Patrol(),
                                        new Search()));


        SimpleGameExtData ed = new SimpleGameExtData(true, new Vector2D(20,20),100, 60);
        SimpleGameIntData id = new SimpleGameIntData(50,new Vector2D(10,10));

        root.makeDecision(ed,id);

    }
}
