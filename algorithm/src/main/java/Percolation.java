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
     * @param row
     *            : from 1 to N
     * @param column
     *            : from 1 to N
     */
    public void open(int row, int col) {

        if (isOpen(row, col)) {
            return;
        }

        validate(row, col);

        int curIndex = (row - 1) * nsize + col - 1;
        // open site (row i, column j) if it is not already

        // int top = (j > 0 ? j - 1 : j) * n + i;
        // int bottom = (j < n - 1 ? j + 1 : j) * n + i;
        // int left = j * n + (i > 0 ? i - 1 : i);
        // int right = j * n + (i < n - 1 ? i + 1 : i);

        int top = (row - 2) * nsize + col - 1;
        int bottom = (row * nsize + col - 1);
        int left = (row - 1) * nsize + col - 2;
        int right = (row - 1) * nsize + col;

        if (row > 1 && isOpen(top)) {
            wqu.union(top, curIndex);
        }
        if (row < nsize && isOpen(bottom)) {
            wqu.union(bottom, curIndex);
        }
        if (col > 1 && isOpen(left)) {
            wqu.union(left, curIndex);
        }
        if (col < nsize && isOpen(right)) {
            wqu.union(right, curIndex);
        }

        site1DimensionArray[curIndex] = 1;

        if (isFull(row, col) && isConnectedToBottom(row, col)) {
            isPercolated = true;
        }
    }

    /**
     * 
     * @param row
     *            : from 1 to N
     * @param col
     *            : from 1 to N
     * @return
     */
    public boolean isOpen(int row, int col) {
        // is site (row i, column j) open?
        validate(row, col);

        return site1DimensionArray[(row - 1) * nsize + col - 1] == 1;
    }

    private boolean isOpen(int index) {
        return site1DimensionArray[index] == 1;
    }

    private boolean isConnectedToBottom(int row, int col) {
        if (!isOpen(row, col)) {
            return false;
        }
        if (row == nsize) {
            return true;
        }
        int curIndex = (row - 1) * nsize + col - 1;
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
    public boolean isFull(int row, int col) {

        validate(row, col);

        // is site (row i, column j) full?
        if (!isOpen(row, col)) {
            return false;
        }
        if (row == 1) {
            return true;
        }
        int curIndex = (row - 1) * nsize + col - 1;
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

    private void validate(int row, int col) {
        if (row < 1 || row > nsize || col < 1 || col > nsize)
            throw new IndexOutOfBoundsException();
    }

}