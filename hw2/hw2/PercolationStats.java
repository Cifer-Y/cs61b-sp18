package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private int trial;
    private double mean;
    private double[] xTrials;
    private double stddev;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        trial = T;
        int col, row;
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        xTrials = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation temp = pf.make(N);
            while (!temp.percolates()) {
                col = StdRandom.uniform(N) + 1;
                row = StdRandom.uniform(N) + 1;
                temp.open(row, col);
            }
            xTrials[i] = (double) temp.numberOfOpenSites() / (N * N);
        }
        mean = StdStats.mean(xTrials);
        stddev = StdStats.stddev(xTrials);
    }

    public double mean() {
        return mean;
    }
    public double stddev() {
        return stddev;
    }

    public double confidenceLow() {
        return mean - 1.96 * stddev / Math.sqrt(trial);
    }

    public double confidenceHigh() {
        return mean + 1.96 * stddev / Math.sqrt(trial);
    }
}
