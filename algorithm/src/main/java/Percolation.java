

public class Percolation {
	private int N = 0;
	
	private WeightedQuickUnionUF wqu = null;
	
	private int[] site1DimensionArray = null;
	
	private boolean isPercolated = false;
	
	public Percolation(int N) {
		this.N = N;
		// create N-by-N grid, with all sites blocked
		int arrayLength = N*N;
		wqu = new WeightedQuickUnionUF(arrayLength);
		site1DimensionArray = new int[arrayLength];
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++){
				site1DimensionArray[j*N+i] = 0;
			}
		}
	}

	public void open(int i, int j) {
		int curIndex = j*N+i;
		// open site (row i, column j) if it is not already
		site1DimensionArray[curIndex] = 1;
		if(j==N-1&&isFull(i,j)){
			isPercolated = true;
		}
		int top = j>0?j-1:j;
		int bottom = j<N-1?j+1:j;
		int left = i>0?i-1:i;
		int right = i<N-1?i+1:i;
		
		wqu.union(top*N+i,curIndex);
		wqu.union(bottom*N+i,curIndex);
		wqu.union(j*N+left,curIndex);
		wqu.union(j*N+right,curIndex);
	}

	public boolean isOpen(int i, int j) {
		// is site (row i, column j) open?
		return site1DimensionArray[j*N+i] == 1;
	}

	public boolean isFull(int i, int j) {
		// is site (row i, column j) full?
		if(!isOpen(i,j))
			return false;
		if(j==0)
			return true;
		int curIndex = j*N+i;
		for(int k=0;k<N;k++){
			if(wqu.connected(curIndex, k))
				return true;
		}
		return false;
		
	}

	public boolean percolates() {
		// does the system percolate?
		return isPercolated;
	}
}
