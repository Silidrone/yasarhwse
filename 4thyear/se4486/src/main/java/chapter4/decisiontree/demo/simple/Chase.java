package chapter4.decisiontree.demo.simple;

import chapter4.decisiontree.Action;

public class Chase extends Action<SimpleGameExtData,SimpleGameIntData> {

    @Override
    public Action<SimpleGameExtData, SimpleGameIntData> makeDecision(SimpleGameExtData extData, SimpleGameIntData intData) {

        System.out.println("Chase!");
        
        return super.makeDecision(extData, intData);
    }
}
