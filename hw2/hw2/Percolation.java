package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] sites;
    private int width;
    private int vtop;
    private int openedSites = 0;
    private WeightedQuickUnionUF WQUUF;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IndexOutOfBoundsException("Illegal size");
        }
        sites = new boolean[N][N];
        width = N;
        WQUUF = new WeightedQuickUnionUF((width * width) + 2);
        vtop = width * width;

    }

    public void open(int row, int col) {
        if (row < 0 || row >= width || col < 0 || col > width) {
            throw new IndexOutOfBoundsException("Illegal size");
        }
        sites[row][col] = true;
        checkNeighbourAndConnect(row, col);
        openedSites++;
    }

    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= width || col < 0 || col > width) {
            throw new IndexOutOfBoundsException("Illegal size");
        }
        return sites[row][col];
    }

    public boolean isFull(int row, int col) {
        return  WQUUF.connected(vtop, xyTo1D(row, col));
    }

    public int numberOfOpenSites() {
        return openedSites;
    }

    public boolean percolates() {
        for (int i = 0; i < width; i++) {
            if (WQUUF.connected(vtop, xyTo1D(width - 1, i))) {
                return true;
            }
        }
        return false;
    }

    private int xyTo1D(int row, int col) {
        return row * width + col;
    }

    private void checkNeighbourAndConnect(int row, int col) {
        int current1D = xyTo1D(row, col);
        if (row == 0) {
            WQUUF.union(current1D, vtop);
        }

        int topRow = row - 1;
        int rightCol = col + 1;
        int bottomRow = row + 1;
        int leftCol = col - 1;

        if (topRow >= 0 && isOpen(topRow, col)) {
            WQUUF.union(current1D, xyTo1D(topRow, col));
        }
        if (rightCol < width && isOpen(row, rightCol)) {
            WQUUF.union(current1D, xyTo1D(row, rightCol));
        }
        if (bottomRow < width && isOpen(bottomRow, col)) {
            WQUUF.union(current1D, xyTo1D(bottomRow, col));
        }
        if (leftCol >= 0 && isOpen(row, leftCol)) {
            WQUUF.union(current1D, xyTo1D(row, leftCol));
        }
    }
}

