public class Percolation {
    enum Status {
        BLOCKED, OPEN, FULL, CONNECT_TOBOTTOM, PERCOLATED
    }

    private int nsize = 0;
    private WeightedQuickUnionUF wqu = null;
    private Status[] site1DimensionArray = null;
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
        site1DimensionArray = new Status[lengthOf1DArray];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                site1DimensionArray[j * N + i] = Status.BLOCKED;
            }
        }
    }

    /**
     * Open site (row, column) if it is not already
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
        site1DimensionArray[curIndex] = Status.OPEN;

        if (row == 1) {
            site1DimensionArray[curIndex] = Status.FULL;
        }
        if (row == nsize) {
            if (site1DimensionArray[curIndex] == Status.FULL) {
                // In case N = 1
                site1DimensionArray[curIndex] = Status.PERCOLATED;
            } else {
                site1DimensionArray[curIndex] = Status.CONNECT_TOBOTTOM;
            }
        }

        if (row > 1) {
            int top = (row - 2) * nsize + col - 1;
            percolate(top, curIndex);
        }
        if (row < nsize) {
            int bottom = row * nsize + col - 1;
            percolate(bottom, curIndex);
        }
        if (col > 1) {
            int left = (row - 1) * nsize + col - 2;
            percolate(left, curIndex);
        }
        if (col < nsize) {
            int right = (row - 1) * nsize + col;
            percolate(right, curIndex);
        }

        int groupId = wqu.find(curIndex);
        site1DimensionArray[groupId] = site1DimensionArray[curIndex];

        if (site1DimensionArray[curIndex] == Status.PERCOLATED) {
            isPercolated = true;
        }
    }

    /**
     * Is site (row, column) open?
     * 
     * @param row
     *            : from 1 to N
     * @param col
     *            : from 1 to N
     * @return
     */
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return site1DimensionArray[(row - 1) * nsize + col - 1] != Status.BLOCKED;
    }

    private boolean isOpen(int index) {
        return site1DimensionArray[index] != Status.BLOCKED;
    }

    /**
     * Is site (row, column) full ?
     * 
     * @param row
     * @param col
     * @return
     */
    public boolean isFull(int row, int col) {
        validate(row, col);
        if (!isOpen(row, col)) {
            return false;
        }
        int curIndex = (row - 1) * nsize + col - 1;
        Status status = site1DimensionArray[wqu.find(curIndex)];
        return status == Status.FULL || status == Status.PERCOLATED;
    }

    public boolean percolates() {
        // does the system percolate?
        return isPercolated;
    }

    private void validate(int row, int col) {
        if (row < 1 || row > nsize || col < 1 || col > nsize)
            throw new IndexOutOfBoundsException();
    }

    /**
     * Before the grid is percolated, all group's status can only be OPEN, FULL
     * or CONNECT_TOBOTTOM
     * 
     * @param adjcentSite
     * @param centerSite
     * @return true if percolated, false otherwise.
     */
    private void percolate(int adjcentSite, int centerSite) {
        if (!isOpen(adjcentSite)) {
            return;
        }
        int groupId = wqu.find(adjcentSite);
        Status groupStatus = site1DimensionArray[groupId];
        // the status for the group can only be
        // OPEN, FULL or CONNECT_TOBOTTOM
        Status centerSiteStatus = site1DimensionArray[centerSite];
        if ((groupStatus == Status.FULL 
                && centerSiteStatus == Status.CONNECT_TOBOTTOM)
                || (groupStatus == Status.CONNECT_TOBOTTOM 
                && centerSiteStatus == Status.FULL)) {
            site1DimensionArray[centerSite] = Status.PERCOLATED;
        } else if (centerSiteStatus == Status.OPEN) {
            site1DimensionArray[centerSite] = site1DimensionArray[groupId];
        }
        wqu.union(adjcentSite, centerSite);
    }

}