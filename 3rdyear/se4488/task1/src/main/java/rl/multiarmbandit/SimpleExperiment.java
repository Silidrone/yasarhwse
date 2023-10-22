package rl.multiarmbandit;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.HistogramDataset;

/**
 * * The simple experiment class that contains only one player.
 */
public class SimpleExperiment extends BanditExperiment {

    BanditPlayer player;
    double[] rewards;


    public SimpleExperiment(MultiArmBandit bandit, BanditPlayer player, String chartFileName) {
        super(bandit,chartFileName);

        this.player=player;
    }

    /**
     *  Performs experiment as follows:
     *          - initialize the bandit machine and player
     *          - Make the player roll the machine rollCount times
     *          - Record the rewards of each roll and feed the player accordingly
     *
     * @param rollCount
     */
    protected void perform(int rollCount)  {
        bandit.reset();
        player.init(bandit.armCount());
        for (int i = 0; i < rollCount; i++) {
            int a = player.play();
            double reward = bandit.roll(a);
            rewards[i] += reward;
            player.feedReward(a,reward);
        }
    }



    protected JFreeChart createChart()  {
        return ChartFactory.createHistogram("Reward Distribution",
                "Reward","Frequency",
                createDataSet());
    }

    private HistogramDataset createDataSet() {
        HistogramDataset dataset = new HistogramDataset();
        dataset.addSeries("Reward",rewards,20);
        return dataset;
    }

    protected void init(int rollCount) {
        rewards = new double[rollCount];
    }

}
