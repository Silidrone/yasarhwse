package chapter4.behaviortree.assasin.guard;

import org.newdawn.slick.Color;

public enum GuardState {
    Patrol (Color.white), Chase(Color.magenta), Attack(Color.red), Guarding(Color.blue);

    GuardState(Color c)
    {
        this.color = c;
    }

    Color color;
}
