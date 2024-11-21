package chapter2.taggame.hw.efe;

import chapter2.SteeringBehavior;
import chapter2.taggame.TagArena;
import chapter2.taggame.TagPlayer;
import chapter2.taggame.TagSteeringEngine;

public class EfeEngine implements TagSteeringEngine
{

    @Override
    public SteeringBehavior getSteeringBehavior(TagPlayer player, TagArena arena) {
        return new EfeTagMasterv2(player,arena.getPlayers(),5,800,600,1000);
    }
}