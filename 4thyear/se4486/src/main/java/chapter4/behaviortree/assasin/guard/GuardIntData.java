package chapter4.behaviortree.assasin.guard;


import chapter2.StaticInfo;
import chapter2.SteeringBehavior;
import math.geom2d.Vector2D;

public class GuardIntData {


    public int lastAssasinHeard;
    double health;
    StaticInfo staticInfo;

    Vector2D lastAssasinPos;
    int lastAssasinSeen;
    GuardState state;
    SteeringBehavior steeringBehavior;

    public GuardIntData(double health, StaticInfo staticInfo, Vector2D lastAssasinPos, int lastAssasinSeen, int lastAssasinHeard, GuardState state) {
        this.health = health;
        this.staticInfo = staticInfo;

        this.lastAssasinPos = lastAssasinPos;
        this.lastAssasinSeen = lastAssasinSeen;
        this.lastAssasinHeard = lastAssasinHeard;
        this.state = state;
        this.steeringBehavior = SteeringBehavior.NoSteering;

    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public StaticInfo getStaticInfo() {
        return staticInfo;
    }

    public void setStaticInfo(StaticInfo staticInfo) {
        this.staticInfo = staticInfo;
    }

    public Vector2D getLastAssasinPos() {
        return lastAssasinPos;
    }

    public void setLastAssasinPos(Vector2D lastAssasinPos) {
        this.lastAssasinPos = lastAssasinPos;
    }

    public int getLastAssasinSeen() {
        return lastAssasinSeen;
    }

    public void setLastAssasinSeen(int lastAssasinSeen) {
        this.lastAssasinSeen = lastAssasinSeen;
    }

    public GuardState getState() {
        return state;
    }

    public void setState(GuardState state) {
        this.state = state;
    }

    public SteeringBehavior getSteeringBehavior() {
        return steeringBehavior;
    }

    public void setSteeringBehavior(SteeringBehavior steeringBehavior) {
        this.steeringBehavior = steeringBehavior;
    }


}
