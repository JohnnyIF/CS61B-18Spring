package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF grid;
    private boolean[] status;
    private int N;
    private int numOfOpenSites = 0;
    private int upper, down;
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        grid = new WeightedQuickUnionUF(N * N + 2);
        status = new boolean[N * N];
        for (int i = 0; i < 25; i += 1) {
            status[i] = false;
        }
        this.N = N;
        upper = N * N;
        down = upper + 1;
    }
    private int posConvert(int row, int col) {
        return row * N + col;
    }
    private boolean checkBound(int row, int col) {
        return row < N && row >= 0 && col < N && col >= 0;
    }
    private void boundVal(int row, int col) {
        if (row < 0 || row > N - 1 || col < 0 || col > N - 1) {
            throw new IndexOutOfBoundsException();
        }
    }

    public boolean isOpen(int row, int col) {
        boundVal(row, col);
        return status[posConvert(row, col)];
    }
    public void connect(int row, int col, int index) {
        if (!checkBound(row, col)) {
            return;
        }
        if (!isOpen(row, col)) {
            return;
        }
        grid.union(posConvert(row, col), index);
    }

    public void open(int row, int col) {
        boundVal(row, col);
        int pos = posConvert(row, col);
        status[pos] = true;
        if (row == 0) {
            grid.union(pos, upper);
        }
        if (row == N - 1) {
            grid.union(pos, down);
        }
        connect(row - 1, col, pos);
        connect(row + 1, col, pos);
        connect(row, col + 1, pos);
        connect(row, col - 1, pos);

        numOfOpenSites += 1;

    }
    public boolean isFull(int row, int col) {
        boundVal(row, col);
        return grid.connected(posConvert(row, col), upper);
    }
    public int numberOfOpenSites() {
        return numOfOpenSites;
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(5);

    }

    public boolean percolates() {
        return grid.connected(upper, down);
    }
}
