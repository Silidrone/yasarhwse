package chapter2.taggame.hw.sinan;

import chapter2.SteeringBehavior;
import chapter2.taggame.TagArena;
import chapter2.taggame.TagPlayer;

public abstract class TagGameBehavior implements SteeringBehavior {

    protected TagPlayer player;
    protected TagArena arena;
    protected  double maxAcceleration;

    public TagGameBehavior(TagPlayer player, TagArena arena, double maxAcceleration) {
        this.maxAcceleration = maxAcceleration;
        this.player = player;
        this.arena = arena;
    }
}