package rl.multiarmbandit;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * * The base experiment class for different type of experiments on
 * * MultiArmBandit machine
 *
 * *
 */
public abstract class BanditExperiment {


    MultiArmBandit bandit;

    private final String chartFileName;


    public BanditExperiment(MultiArmBandit bandit, String chartFileName) {
        this.bandit = bandit;
        this.chartFileName = chartFileName;
    }

    public  void performExperiment(int rollCount) throws IOException {
        init(rollCount);

        perform(rollCount);

        saveChart();
    }

    protected abstract void init(int rollCount);

    protected abstract void perform(int rollCount);


    private void saveChart() throws IOException {

        JFreeChart chart  = createChart();

        int width = 640;    /* Width of the image */
        int height = 480;   /* Height of the image */

        File chartFile = new File( chartFileName );

        ChartUtils.saveChartAsJPEG(chartFile ,chart, width ,height);
    }

    protected abstract JFreeChart createChart();


}
