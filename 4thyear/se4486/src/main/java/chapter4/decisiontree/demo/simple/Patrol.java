package chapter4.decisiontree.demo.simple;

import chapter4.decisiontree.Action;

public class Patrol extends Action<SimpleGameExtData,SimpleGameIntData> {

    @Override
    public Action<SimpleGameExtData, SimpleGameIntData> makeDecision(SimpleGameExtData extData, SimpleGameIntData intData) {

        System.out.println("Patrol!");
        
        return super.makeDecision(extData, intData);
    }
}
