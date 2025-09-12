package chapter4.decisiontree.demo.simple;

import chapter4.decisiontree.Action;

public class Search extends Action<SimpleGameExtData,SimpleGameIntData> {

    @Override
    public Action<SimpleGameExtData, SimpleGameIntData> makeDecision(SimpleGameExtData extData, SimpleGameIntData intData) {

        System.out.println("Search!");
        
        return super.makeDecision(extData, intData);
    }
}
