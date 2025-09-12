package chapter4.statemachine.demo;


import chapter4.statemachine.Action;
import chapter4.statemachine.State;
import chapter4.statemachine.Transition;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dindar.oz on 10/17/2017.
 */
public class DemoState implements State<DemoExtData,DemoIntData> {

    String name;

    List<String> actionTexts;
    List<String> entryTexts;
    List<String> exitTexts;

    List<Transition<DemoExtData,DemoIntData>> transitions;

    public DemoState(String name) {
        this.name = name;
    }

    public void setActionTexts(List<String> actionTexts) {
        this.actionTexts = actionTexts;
    }

    public void setEntryTexts(List<String> entryTexts) {
        this.entryTexts = entryTexts;
    }

    public void setExitTexts(List<String> exitTexts) {
        this.exitTexts = exitTexts;
    }

    public void setTransitions(List<Transition<DemoExtData,DemoIntData>> transitions) {
        this.transitions = transitions;
    }

    @Override
    public List<Action<DemoExtData,DemoIntData>> getActions() {
        return actionTexts.stream().map(DemoAction::new).collect(Collectors.toList());
    }

    @Override
    public List<Action<DemoExtData,DemoIntData>> getEntryActions() {
        return entryTexts.stream().map(DemoAction::new).collect(Collectors.toList());
    }

    @Override
    public List<Action<DemoExtData,DemoIntData>> getExitActions() {
        return exitTexts.stream().map(DemoAction::new).collect(Collectors.toList());
    }

    @Override
    public List<Transition<DemoExtData,DemoIntData>> getTransitions() {
        return transitions;
    }

    @Override
    public String toString() {
        return "[ STATE-" +name+ "]";
    }
}
