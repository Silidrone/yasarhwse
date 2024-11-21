package chapter2.taggame.hw.pelin;

import chapter2.SteeringBehavior;
import chapter2.Acceleration;
import chapter2.AccelerationType;
import chapter2.Velocity;
import chapter2.taggame.TagArena;
import chapter2.taggame.TagPlayer;
import chapter2.taggame.TagSteeringEngine;
import chapter2.steering.Wander;
import math.geom2d.Vector2D;
import java.util.List;

public class PelinTagGameEngine implements TagSteeringEngine {

    @Override
    public SteeringBehavior getSteeringBehavior(TagPlayer player, TagArena arena) {

        return new TagGameBehavior(player, arena.getPlayers(), 5, 800, 600 );

    }
}
