package chapter2.taggame.hw.sinan;

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
public class TSE_HW_Sinan implements TagSteeringEngine {
    @Override
    public SteeringBehavior getSteeringBehavior(TagPlayer player, TagArena arena) {
        SteeringBehavior steeringBehavior;
        if(player.isTag()){
            steeringBehavior = new Chase(player, arena, 1.0);
        }else{
            steeringBehavior = new RunAway(player, arena, 1.0);
        }
        return steeringBehavior;
    }
}
