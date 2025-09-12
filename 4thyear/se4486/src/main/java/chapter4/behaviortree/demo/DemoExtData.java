package chapter4.behaviortree.demo;

/**
 * Created by dindar.oz on 10/26/2017.
 */
public class DemoExtData {

    public enum DoorState { Open, Closed, Locked};


    DoorState doorState = DoorState.Open;

    public void setDoorState(DoorState doorState) {
        this.doorState = doorState;
    }

    public boolean isDoorOpen() {
        return (doorState==DoorState.Open);
    }

    public boolean isDoorLocked() {
        return doorState == DoorState.Locked;
    }


}
