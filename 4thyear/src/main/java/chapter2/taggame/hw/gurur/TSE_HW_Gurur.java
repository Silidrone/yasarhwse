package chapter2.taggame.hw.gurur;

import chapter2.SteeringBehavior;
import chapter2.taggame.TagArena;
import chapter2.taggame.TagPlayer;
import chapter2.taggame.TagSteeringEngine;

/**
 * TODO: Implement a tag steering engine generating the steering behaviors for a tag player
 *      ! Note that you should implement SteeringBehavior classes to be generated in this class
 *      ! The implemented steering behaviors are supposed to be returning Dynamic Accelearitons.
 *      ! NOT Kinematic.
 */
public class TSE_HW_Gurur implements TagSteeringEngine {
    @Override
    public SteeringBehavior getSteeringBehavior(TagPlayer player, TagArena arena) {
        if(player.isTag()){
            return new Polis(player, arena);
        }
        else return new Hirsiz(player, arena);
    }
}
