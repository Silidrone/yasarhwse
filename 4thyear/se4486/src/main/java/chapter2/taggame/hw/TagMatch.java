package chapter2.taggame.hw;

import chapter2.OrientationType;
import chapter2.StaticInfo;
import chapter2.taggame.TagGame;
import chapter2.taggame.TagPlayer;
import chapter2.taggame.hw.cerensudeyetim.TSE_HW_ceren;
import chapter2.taggame.hw.efe.EfeEngine;
import chapter2.taggame.hw.muhammed.TSE_HW_Muhammed;
import chapter2.taggame.hw.pelin.PelinTagGameEngine;
import chapter2.taggame.hw.sinan.TSE_HW_Sinan;
import math.geom2d.Vector2D;
import org.newdawn.slick.Color;
import org.newdawn.slick.util.Bootstrap;
import chapter2.taggame.hw.gurur.TSE_HW_Gurur;

public class TagMatch {

    public static void main(String[] args) {
        TagGame game = new TagGame();

        TagPlayer tp1 = new TagPlayer("Gurur", new StaticInfo(new Vector2D(90,90),0.0, OrientationType.VelocityBased), Color.blue);
        TagPlayer tp2 = new TagPlayer("Sinan", new StaticInfo(new Vector2D(290,290),0.0, OrientationType.VelocityBased), Color.green);
        TagPlayer tp3 = new TagPlayer("Efe", new StaticInfo(new Vector2D(190,290),0.0, OrientationType.VelocityBased), Color.white);
        TagPlayer tp4 = new TagPlayer("Ceren", new StaticInfo(new Vector2D(390,290),0.0, OrientationType.VelocityBased), Color.magenta);
        TagPlayer tp5 = new TagPlayer("Pelin", new StaticInfo(new Vector2D(590,690),0.0, OrientationType.VelocityBased), Color.orange);
        TagPlayer tp6 = new TagPlayer("Muhamed", new StaticInfo(new Vector2D(490,190),0.0, OrientationType.VelocityBased), Color.yellow);

        tp1.setSteeringEngine(new TSE_HW_Gurur());
        tp2.setSteeringEngine(new TSE_HW_Sinan());
        tp3.setSteeringEngine(new EfeEngine());
        tp4.setSteeringEngine(new TSE_HW_ceren());
        tp5.setSteeringEngine(new PelinTagGameEngine());
        tp6.setSteeringEngine(new TSE_HW_Muhammed());
        game.addPlayer(tp1);
        game.addPlayer(tp2);
        game.addPlayer(tp3);
        game.addPlayer(tp4);
        game.addPlayer(tp5);
        game.addPlayer(tp6);

        Bootstrap.runAsApplication(game,TagGame.DemoWidth,TagGame.DemoHeight,false);
    }
}
