package chapter4.decisiontree.demo.assasin.npc.decisiontree;

import chapter4.decisiontree.demo.assasin.npc.GuardState;
import math.geom2d.Vector2D;

public class GuardIntData {
    double health;
    Vector2D pos;
    GuardState state;


    Vector2D lastAssasinPos;

    public GuardIntData(double health, Vector2D pos, GuardState state , Vector2D lastAssasinPos) {
        this.health = health;
        this.pos = pos;
        this.lastAssasinPos = lastAssasinPos;
        this.state = state;
    }

    public Vector2D getPos() {
        return pos;
    }

    public double getHealth() {
        return health;
    }

    public GuardState getState() {
        return state;
    }

    public Vector2D getLastAssasinPos() {
        return lastAssasinPos;
    }

    public void setLastAssasinPos(Vector2D lastAssasinPos) {
        this.lastAssasinPos = lastAssasinPos;
    }

}
