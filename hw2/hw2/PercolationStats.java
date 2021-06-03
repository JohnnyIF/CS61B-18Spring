package hw2;
import edu.princeton.cs.introcs.StdRandom;
import java.util.Random;

public class PercolationStats {
    private double[] nums;
    private Random random = new Random();
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        nums = new double[T];
        for (int i = 0; i < T; i += 1) {
            Percolation experiment = pf.make(N);
            while (!experiment.percolates()) {
                experiment.open(random.nextInt(N), random.nextInt(N));
            }
            nums[i] = (double) experiment.numberOfOpenSites();
        }
    }

    public double mean() {
        double sum = 0;
        for (double i : nums) {
            sum += i;
        }
        return sum / nums.length;
    }

    public double stddev() {
        double sum = 0;
        for (double i : nums) {
            sum += (i - mean()) * (i -mean());
        }
        return sum / (nums.length - 1);
    }

    public double confidenceLow() {
        return mean() - 1.96 * Math.sqrt(stddev() / nums.length);
    }
    public double confidenceHigh() {
        return mean() + 1.96 * Math.sqrt(stddev() / nums.length);
    }

}
