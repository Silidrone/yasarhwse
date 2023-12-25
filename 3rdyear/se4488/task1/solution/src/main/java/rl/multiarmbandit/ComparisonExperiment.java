package rl.multiarmbandit;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * * Represent a comparison experiment between different Bandit Players
 * * The experiments are repeated runCount times and the average is taken
 *
 * * This class can also perform experiment for non-stationary cases
 * * by changeing the machine at every changeInterval rolls
 */
public class ComparisonExperiment extends BanditExperiment{

    List<BanditPlayer> players;
    Map<String,double[]> rewards;

    int runCount;


    // * The interval that the machine remains stationary
    // * Default = 0 which means the machine is stationary from start to end
    int changeInterval;

    public ComparisonExperiment(MultiArmBandit bandit, int runCount, String chartFileName) {
        super(bandit,chartFileName);

        players = new ArrayList<>();
        this.runCount = runCount;
    }

    public void addPlayer(BanditPlayer player)
    {
        players.add(player);
    }

    public void setChangeInterval(int changeInterval) {
        this.changeInterval = changeInterval;
    }

    protected void perform(int rollCount)  {

        for (int r = 1; r <= runCount; r++) {
            performOneRun(r,rollCount);
        }

        bandit.print();
        for (BanditPlayer player:players)
            player.printStats();

    }



    protected JFreeChart createChart()  {

        return ChartFactory.createXYLineChart(
                "Rewards versus Time step",  // title
                "Step",                      // x-axis label
                "Reward",                    // y-axis label
                createDataSet());
     }

    private XYDataset createDataSet() {
        XYSeriesCollection dataset = new XYSeriesCollection();
        for (String pName:rewards.keySet())
        {
            double[] rewards = this.rewards.get(pName);
            XYSeries series = new XYSeries(pName);
            for (int pr = 0; pr < rewards.length; pr++) {
                series.add((pr+1),rewards[pr]);
            }
            dataset.addSeries(series);
        }
        return dataset;
    }

    private void performOneRun(int run ,int rollCount ) {
        bandit.reset();
        initPlayers();
        for (int i = 0; i < rollCount; i++) {

            // * The bandit machine changes at each changeInterval
            if (changeInterval>0 && (i+1)%changeInterval==0)
                bandit.reset();

            for (BanditPlayer player:players)
            {
                int a = player.play();
                double reward = bandit.roll(a);
                double[] playerRewards = rewards.get(player.getName());
                playerRewards[i] += (1.0/run)*(reward-playerRewards[i]);
                player.feedReward(a,reward);
            }
        }
    }

    private void initPlayers() {
        for (BanditPlayer player:players)
            player.init(bandit.armCount());
    }

    protected void init(int rollCount) {

        rewards = new HashMap<>();
        for (BanditPlayer player:players)
        {
            rewards.put(player.getName(), new double[rollCount]);
        }
    }

}
