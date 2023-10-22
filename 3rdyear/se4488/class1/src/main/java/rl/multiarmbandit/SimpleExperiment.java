package rl.multiarmbandit;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.HistogramDataset;

/**
 * TODO-4 : The simple experiment class that contains only one player.
 */
public class SimpleExperiment extends BanditExperiment {

    BanditPlayer player;
    double[] rewards;


    public SimpleExperiment(MultiArmBandit bandit, BanditPlayer player, String chartFileName) {
        super(bandit,chartFileName);

        this.player=player;
    }

    /**
     * TODO-4.1 : Implement perform method as follows:
     *          - initialize the bandit machine and player
     *          - Make the player roll the machine rollCount times
     *          - Record the rewards of each roll and feed the player accordingly
     *
     * @param rollCount
     */
    protected void perform(int rollCount)  {
        /* your code here*/
        player.init(bandit.armCount());
        bandit.reset();
        bandit.print();

        for (int roll = 0; roll < rollCount; roll++) {
            int arm = player.play();
            double r = bandit.roll(arm);
            player.feedReward(arm,r);
            rewards[roll] = r;
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
