public class Percolation {
    private int nsize = 0;
    private WeightedQuickUnionUF wqu = null;
    private int[] site1DimensionArray = null;
    private boolean isPercolated = false;
    private final int BLOCKED = 0;
    private final int OPEN = 1;
    private final int FULL = 2;
    private final int CONNECT_TOBOTTOM = 4;

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
                site1DimensionArray[j * N + i] = BLOCKED;
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
        // open site (row i, column j) if it is not already

        if (isOpen(row, col)) {
            return;
        }

        validate(row, col);

        int curIndex = (row - 1) * nsize + col - 1;
        site1DimensionArray[curIndex] = OPEN;

        // int top = (j > 0 ? j - 1 : j) * n + i;
        // int bottom = (j < n - 1 ? j + 1 : j) * n + i;
        // int left = j * n + (i > 0 ? i - 1 : i);
        // int right = j * n + (i < n - 1 ? i + 1 : i);

        int top = (row - 2) * nsize + col - 1;
        int bottom = (row * nsize + col - 1);
        int left = (row - 1) * nsize + col - 2;
        int right = (row - 1) * nsize + col;

        boolean isFull = false;
        boolean isConnectToBottom = false;
        if (row == 1) {
            isFull = true;
        }
        if (row == nsize) {
            isConnectToBottom = true;
        }

        if (row > 1 && isOpen(top)) {
            int status = percolate(top, curIndex);
            if (status == FULL) {
                isFull = true;
            } else if (status == CONNECT_TOBOTTOM) {
                isConnectToBottom = true;
            }
        }
        if (row < nsize && isOpen(bottom)) {
            int status = percolate(bottom, curIndex);
            if (status == FULL) {
                isFull = true;
            } else if (status == CONNECT_TOBOTTOM) {
                isConnectToBottom = true;
            }
        }
        if (col > 1 && isOpen(left)) {
            int status = percolate(left, curIndex);
            if (status == FULL) {
                isFull = true;
            } else if (status == CONNECT_TOBOTTOM) {
                isConnectToBottom = true;
            }
        }
        if (col < nsize && isOpen(right)) {
            int status = percolate(right, curIndex);
            if (status == FULL) {
                isFull = true;
            } else if (status == CONNECT_TOBOTTOM) {
                isConnectToBottom = true;
            }
        }

        int groupId = wqu.find(curIndex);
        if (isFull) {
            site1DimensionArray[groupId] = FULL;
        } else if (isConnectToBottom) {
            site1DimensionArray[groupId] = CONNECT_TOBOTTOM;
        }

        if (isFull && isConnectToBottom) {
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

        return site1DimensionArray[(row - 1) * nsize + col - 1] != BLOCKED;
    }

    private boolean isOpen(int index) {
        return site1DimensionArray[index] != BLOCKED;
    }

    /*
     * O(N)
     */
    public boolean isFull(int row, int col) {
        // is site (row i, column j) full?

        validate(row, col);

        if (!isOpen(row, col)) {
            return false;
        }

        int curIndex = (row - 1) * nsize + col - 1;

        return site1DimensionArray[wqu.find(curIndex)] == FULL;
    }

    public boolean percolates() {
        // does the system percolate?
        return isPercolated;
    }

    private void validate(int row, int col) {
        if (row < 1 || row > nsize || col < 1 || col > nsize)
            throw new IndexOutOfBoundsException();
    }

    private int percolate(int openedSite, int closedSite) {
        int groupId = wqu.find(openedSite);
        wqu.union(openedSite, closedSite);
        return site1DimensionArray[groupId];
    }

}