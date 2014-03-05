public class Percolation {

	private int nsize = 0;

	private WeightedQuickUnionUF wqu = null;

	private int[] site1DimensionArray = null;

	private boolean isPercolated = false;

	/**
	 * O(N^2)
	 * 
	 * @param N
	 */
	public Percolation(final int N) {
		this.nsize = N;
		// create N-by-N grid, with all sites blocked
		int lengthOf1DArray = N * N;
		wqu = new WeightedQuickUnionUF(lengthOf1DArray);
		site1DimensionArray = new int[lengthOf1DArray];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				site1DimensionArray[j * N + i] = 0;
			}
		}
	}

	/**
	 * 
	 * @param i
	 *            : from 1 to N
	 * @param j
	 *            : From 1 to N
	 */
	public void open(int i, int j) {

		validate(i, j);

		int curIndex = (j - 1) * nsize + i - 1;
		// open site (row i, column j) if it is not already
		// int top = (j > 0 ? j - 1 : j) * n + i;
		// int bottom = (j < n - 1 ? j + 1 : j) * n + i;
		// int left = j * n + (i > 0 ? i - 1 : i);
		// int right = j * n + (i < n - 1 ? i + 1 : i);

		int top = (j - 2) * nsize + i - 1;
		int bottom = (j * nsize + i - 1);
		int left = (j - 1) * nsize + i - 2;
		int right = (j - 1) * nsize + i;

		if (j > 1 && isOpen(top)) {
			wqu.union(top, curIndex);
		}
		if (j < nsize && isOpen(bottom)) {
			wqu.union(bottom, curIndex);
		}
		if (i > 1 && isOpen(left)) {
			wqu.union(left, curIndex);
		}
		if (i < nsize && isOpen(right)) {
			wqu.union(right, curIndex);
		}

		site1DimensionArray[curIndex] = 1;

		if (isFull(i, j) && isConnectedToBottom(i, j)) {
			isPercolated = true;
		}
	}

	/**
	 * 
	 * @param i
	 *            : from 1 to N
	 * @param j
	 *            : from 1 to N
	 * @return
	 */
	public boolean isOpen(int i, int j) {
		// is site (row i, column j) open?
		validate(i, j);

		return site1DimensionArray[(j - 1) * nsize + i - 1] == 1;
	}

	private boolean isOpen(int index) {
		return site1DimensionArray[index] == 1;
	}

	private boolean isConnectedToBottom(int i, int j) {
		if (!isOpen(i, j)) {
			return false;
		}
		if (j == nsize) {
			return true;
		}
		int curIndex = (j - 1) * nsize + i - 1;
		int base = nsize * nsize - nsize;
		for (int k = 0; k < nsize; k++) {
			if (wqu.connected(curIndex, base + k)) {
				return true;
			}
		}
		return false;
	}

	/*
	 * O(N)
	 */
	public boolean isFull(int i, int j) {
		// is site (row i, column j) full?
		if (!isOpen(i, j)) {
			return false;
		}
		if (j == 1) {
			return true;
		}
		int curIndex = (j - 1) * nsize + i - 1;
		for (int k = 0; k < nsize; k++) {
			if (wqu.connected(curIndex, k)) {
				return true;
			}
		}
		return false;
	}

	public boolean percolates() {
		// does the system percolate?
		return isPercolated;
	}

	private void validate(int i, int j) {
		if (i < 1 || i > nsize || j < 1 || j > nsize)
			throw new IndexOutOfBoundsException();
	}

}