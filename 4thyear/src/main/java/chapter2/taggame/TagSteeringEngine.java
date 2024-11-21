package chapter2.taggame;

import chapter2.SteeringBehavior;

@FunctionalInterface
public interface TagSteeringEngine {
    SteeringBehavior getSteeringBehavior(TagPlayer player, TagArena arena);

}
