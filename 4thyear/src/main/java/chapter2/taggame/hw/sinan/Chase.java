package chapter2.taggame.hw.sinan;

import chapter2.Acceleration;
import chapter2.StaticInfo;
import chapter2.Velocity;
import chapter2.steering.Seek;
import chapter2.taggame.TagArena;
import chapter2.taggame.TagPlayer;
import math.geom2d.Vector2D;
import java.util.List;

public class Chase extends TagGameBehavior {
    private Seek seek;
    public Chase(TagPlayer player, TagArena arena, double maxAcceleration) {
        super(player, arena, maxAcceleration);
        seek = new Seek(new Vector2D(0,0),maxAcceleration);
    }

    private TagPlayer findClosestPlayer(){
        List<TagPlayer> players = arena.getPlayers();
        TagPlayer closestPlayer = null;
        for (int i = 0; i < players.size(); i++) {
            TagPlayer currPlayer = players.get(i);
            if(!currPlayer.isTag()){
                if(closestPlayer == null){
                    closestPlayer = currPlayer;
                }else {
                    closestPlayer = getCloserPlayer(closestPlayer, currPlayer);
                }
            }
        }
        return closestPlayer;
    }

    private TagPlayer getCloserPlayer(TagPlayer closestPlayer, TagPlayer currPlayer){
        double closestDistance = findDistance(closestPlayer);
        double currDistance = findDistance(currPlayer);
        if(currDistance < closestDistance){
            closestPlayer = currPlayer;
        }
        return closestPlayer;
    }

    private double findDistance(TagPlayer otherPlayer) {
        Vector2D myPos = player.getStaticInfo().getPos();
        Vector2D otherPos = otherPlayer.getStaticInfo().getPos();
        return otherPos.minus(myPos).norm();
    }

    @Override
    public Acceleration getSteering(StaticInfo staticInfo, Velocity velocity) {
        TagPlayer closestPlayer = findClosestPlayer();
        seek.setTarget(closestPlayer.getStaticInfo().getPos());
        return seek.getSteering(staticInfo,velocity);
    }
}