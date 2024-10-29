package chapter2.taggame;

import chapter2.SteeringBehavior;

public interface TagSteeringEngine {
    SteeringBehavior getSteeringBehavior(TagPlayer player, TagArena arena);
}
