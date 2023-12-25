package rl.multiarmbandit;

public record State(double[] values, long timeSteps, int[] updateTimes) {
    public State(double[] values) {
        this(values, 0L, null);
    }
}
