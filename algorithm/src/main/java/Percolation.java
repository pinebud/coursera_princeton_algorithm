
public class Percolation {
	private int N = 0;
	
	private WeightedQuickUnionUF wqu = null;
	
	private int[] site1DimensionArray = null;
	
	private boolean isPercolated = false;
	
	/*
	 * O(N^2)
	 */
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
		int top = (j>0?j-1:j)*N+i;
		int bottom = (j<N-1?j+1:j)*N+i;
		int left = j*N+(i>0?i-1:i);
		int right = j*N+(i<N-1?i+1:i);
		
		if(isOpen(top))
			wqu.union(top,curIndex);
		if(isOpen(bottom))
			wqu.union(bottom,curIndex);
		if(isOpen(left))
			wqu.union(left,curIndex);
		if(isOpen(right))
			wqu.union(right,curIndex);

		site1DimensionArray[curIndex] = 1;

		if(isFull(i,j)&&isConnectedToBottom(i,j)){			
			isPercolated = true;
		}
	}

	public boolean isOpen(int i, int j) {
		// is site (row i, column j) open?
		return site1DimensionArray[j*N+i] == 1;
	}
	
	private boolean isOpen(int index){
		return site1DimensionArray[index] == 1;
	}
	
	private boolean isConnectedToBottom(int i, int j){
		if(!isOpen(i,j))
			return false;
		if(j==N-1)
			return true;
		int curIndex = j*N+i;
		int base = N*N-N;
		for(int k=0;k<N;k++){
			if(wqu.connected(curIndex, base+k))
				return true;
		}
		return false;
	}

	/*
	 * O(N)
	 */
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
