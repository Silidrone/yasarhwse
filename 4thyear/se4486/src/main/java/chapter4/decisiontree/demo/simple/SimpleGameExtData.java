package chapter4.decisiontree.demo.simple;

import math.geom2d.Vector2D;

public class SimpleGameExtData {
    boolean isPlayerVisible ;       // Is the player currently visible?
    Vector2D playerPos;             // Position (in meters)
    double  time;                    // current time
    double  lastAlertTime; // time of the last alert

    public SimpleGameExtData(boolean isPlayerVisible, Vector2D playerPos, double time, double lastAlertTime) {
        this.isPlayerVisible = isPlayerVisible;
        this.playerPos = playerPos;
        this.time = time;
        this.lastAlertTime = lastAlertTime;
    }
}
