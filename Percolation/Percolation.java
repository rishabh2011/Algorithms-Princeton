import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF wuf;
    private boolean[] openSites;
    private int numberOfOpenSites;
    private int rowSize;
    private int gridSize;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Size of grid cannot be less than or equal to 0");
        }
        // two extra sites for virtual top and virtual bottom (0, n*n - 1)
        wuf = new WeightedQuickUnionUF((n * n) + 2);
        openSites = new boolean[(n * n) + 2];
        numberOfOpenSites = 0;
        rowSize = n;
        gridSize = n * n;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row < 1 || col < 1 || row > rowSize || col > rowSize) {
            throw new IllegalArgumentException("Row/Col arguments must be within 1 to n range");
        }

        int siteIndex = convert2Dto1D(row, col);
        if (!openSites[siteIndex]) {
            // Connect the center site with its adjacent sites in the grid
            int leftSiteIndex = siteIndex - 1;
            int rightSiteIndex = siteIndex + 1;
            int upperSiteIndex = siteIndex - rowSize;
            int lowerSiteIndex = siteIndex + rowSize;

            // connect to left neighbour
            if (leftSiteIndex % rowSize != 0 && openSites[leftSiteIndex]) {
                wuf.union(siteIndex, leftSiteIndex);
            }

            // connect to right neighbour
            if (rightSiteIndex != (rowSize * row) + 1 && openSites[rightSiteIndex]) {
                wuf.union(siteIndex, rightSiteIndex);
            }

            // connect to upper neighbour
            if (upperSiteIndex > 0 && openSites[upperSiteIndex]) {
                wuf.union(siteIndex, upperSiteIndex);
            } else if (upperSiteIndex <= 0) {
                // connect to virtual top
                wuf.union(siteIndex, 0);
            }

            // connect to lower neighbour
            if (lowerSiteIndex <= gridSize && openSites[lowerSiteIndex]) {
                wuf.union(siteIndex, lowerSiteIndex);
            } else if (lowerSiteIndex > gridSize) {
                // connect to virtual bottom
                wuf.union(siteIndex, gridSize + 1);
            }

            openSites[siteIndex] = true;
            ++numberOfOpenSites;
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row < 1 || col < 1 || row > rowSize || col > rowSize) {
            throw new IllegalArgumentException("Row/Col arguments must be within 1 to n range");
        }

        int siteIndex = convert2Dto1D(row, col);
        return openSites[siteIndex];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row < 1 || col < 1 || row > rowSize || col > rowSize) {
            throw new IllegalArgumentException("Row/Col arguments must be within 1 to n range");
        }

        int siteIndex = convert2Dto1D(row, col);
        // site connected to top row open site
        return wuf.find(siteIndex) == wuf.find(0);
    }

    private int convert2Dto1D(int row, int col) {
        return ((row - 1) * rowSize + col);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return wuf.find(0) == wuf.find(gridSize + 1);
    }

}
