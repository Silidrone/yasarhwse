package chapter4.decisiontree.demo.assasin.npc;

import org.newdawn.slick.Color;

public enum GuardState {
    Patrol (Color.blue), Chase(Color.magenta), Attack(Color.red), RunAway(Color.white);

    GuardState(Color c)
    {
        this.color = c;
    }

    Color color;
}
