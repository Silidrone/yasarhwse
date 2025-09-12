package chapter4.statemachine.demo;




/**
 * Created by dindar.oz on 10/17/2017.
 */
public class DemoExtData {
    int intVal;
    double floatVal;


    public DemoExtData(int intVal, double floatVal) {
        this.intVal = intVal;
        this.floatVal = floatVal;
    }

    public int intVal() {
        return intVal;
    }

    public void setIntVal(int intVal) {
        this.intVal = intVal;
    }

    public double floatVal() {
        return floatVal;
    }

    public void setFloatVal(double floatVal) {
        this.floatVal = floatVal;
    }
}
