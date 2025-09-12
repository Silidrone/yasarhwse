package chapter4.statemachine.demo;


import chapter4.statemachine.Condition;

public class DemoCondition1 implements Condition<DemoExtData,DemoIntData> {

    int value;
    public DemoCondition1(int val) {
        value = val;
    }

    @Override
    public boolean test(DemoExtData extData, DemoIntData intData)
    {
        return ( value== extData.intVal);
    }
}
