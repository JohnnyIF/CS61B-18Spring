package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private double[] nums;
    private int T;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        this.T = T;
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        nums = new double[T];
        for (int i = 0; i < T; i += 1) {
            Percolation experiment = pf.make(N);
            while (!experiment.percolates()) {
                experiment.open(StdRandom.uniform(N), StdRandom.uniform(N));
            }
            nums[i] = (double) experiment.numberOfOpenSites() / (N * N);
        }
    }

    public double mean() {
        return StdStats.mean(nums);
    }

    public double stddev() {
        return StdStats.stddev(nums);
    }

    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(T);
    }
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(T);
    }

}
