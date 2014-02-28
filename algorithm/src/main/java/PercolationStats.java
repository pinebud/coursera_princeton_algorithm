

public class PercolationStats {
	
	private double[] results = null;
	
	private double mean = 0.0d;
	
	private double stddev = 0.0d;
	
	private double conflo = 0.0d;
	
	private double confhi = 0.0d;
	
	public PercolationStats(int N, int T) {
		results = new double[T];
	    double totalSites = N*N;
		// perform T independent computational experiments on an N-by-N grid
		for(int t=0;t<T;t++){
			long cntOfOpenSites = 0;
			Percolation perc = new Percolation(N);
			while(!perc.percolates()){
				int rdm_i = StdRandom.uniform(N);
				int rdm_j = StdRandom.uniform(N);
				if(!perc.isOpen(rdm_i, rdm_j)){
					perc.open(rdm_i,rdm_j);
					cntOfOpenSites++;
				}
			}
			results[t] = (double)cntOfOpenSites/totalSites;
		}
		mean = StdStats.mean(results);
		stddev = StdStats.stddev(results);
		conflo = mean-1.96*stddev/Math.sqrt(T);
		confhi = mean+1.96*stddev/Math.sqrt(T);
	}

	public double mean() {
		// sample mean of percolation threshold
		return mean;
	}

	public double stddev() {
		// sample standard deviation of percolation threshold
		return stddev;
	}

	public double confidenceLo() {
		// returns lower bound of the 95% confidence interval
		return conflo;
	}

	public double confidenceHi() {
		// returns upper bound of the 95% confidence interval
		return confhi;
	}

	public static void main(String[] args) {
		// test client, described below
		PercolationStats instance = new PercolationStats(200,100);
		StdOut.println("mean\t=\t"+instance.mean());
		StdOut.println("stddev\t=\t"+instance.stddev());
		StdOut.println("95% confidence interval\t=\t"+instance.confidenceLo()+","+instance.confidenceHi());
	}
}
