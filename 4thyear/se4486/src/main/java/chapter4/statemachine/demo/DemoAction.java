package chapter4.statemachine.demo;


import chapter4.statemachine.Action;

/**
 * Created by dindar.oz on 10/17/2017.
 */
public class DemoAction implements Action<DemoExtData,DemoIntData> {
    private String text;

    public DemoAction(String text) {
        this.text = text;
    }

    @Override
    public void perform( DemoExtData e, DemoIntData i) {
        System.out.println(text);
    }

    @Override
    public String toString() {
        return "Action-"+text;
    }
}
