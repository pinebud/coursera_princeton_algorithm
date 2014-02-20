

public class PercolationStats {
	
	private double[] results = null;
	
	public PercolationStats(int N, int T) {
		results = new double[T];
	    int totalSites = N*N;
		// perform T independent computational experiments on an N-by-N grid
		for(int t=0;t<T;t++){
			int cntOfOpenSites = 0;
			Percolation perc = new Percolation(N);
			while(!perc.percolates()){
				int rdm_i = StdRandom.uniform(N);
				int rdm_j = StdRandom.uniform(N);
				if(!perc.isOpen(rdm_i, rdm_j)){
					perc.open(rdm_i,rdm_j);
					cntOfOpenSites++;
				}
			}
			results[t] = cntOfOpenSites/totalSites;
		}
	}

	public double mean() {
		// sample mean of percolation threshold
		return StdStats.mean(results);
	}

	public double stddev() {
		// sample standard deviation of percolation threshold
		return StdStats.stddev(results);
	}

	public double confidenceLo() {
		// returns lower bound of the 95% confidence interval
		return 0;
	}

	public double confidenceHi() {
		// returns upper bound of the 95% confidence interval
		return 0;
	}

	public static void main(String[] args) {
		// test client, described below
		PercolationStats instance = new PercolationStats(10,10);
		StdOut.println(instance.mean());
		StdOut.println(instance.stddev());
	}
}
