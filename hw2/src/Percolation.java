import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {

    boolean[][] sites;
    int length;
    WeightedQuickUnionUF pUF;
    int count;
    public Percolation(int N) {

        sites = new boolean[N][N];
        length = N;
        pUF = new WeightedQuickUnionUF(N * N + 2);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sites[i][j] = false;
            }
        }
        for (int i = 1; i <= N; i++) {
            pUF.union(i, 0);
        }
        for (int i = N * N; i >= N * N - N; i--) {
            pUF.union(i, N * N + 1);
        }
        count = 0;
    }

    public void open(int row, int col) {

        if (row > length - 1 || col > length - 1  || row < 0 || col < 0) {
            throw new IndexOutOfBoundsException();
        }
        sites[row][col] = true;
        count += 1;
        if (isOpen(row - 1, col)) {
            pUF.union(length * row + col + 1, 1 + length * (row - 1) + col);
        }
        if (isOpen(row + 1, col)) {
            pUF.union(length * row + col + 1, 1 + length * (row + 1) + col);
        }
        if (isOpen(row, col - 1)) {
            pUF.union(length * row + col + 1, 1 + length * row + col - 1);
        }
        if (isOpen(row, col + 1)) {
            pUF.union(length * row + col + 1, 1 + length * row + col + 1);
        }

    }

    public boolean isOpen(int row, int col) {
        if (row > length - 1 || col > length - 1 || row < 0 || col < 0) {
            return false;
        }
        return sites[row][col];
    }

    public boolean isFull(int row, int col) {
        if (row > length - 1 || col > length - 1 || row < 0 || col < 0) {
            throw new IndexOutOfBoundsException();
        }
        if (!sites[row][col]) {
            return false;
        }
        return pUF.connected(length * row + col + 1, 0);
    }

    public int numberOfOpenSites() {
        return count;
    }

    public boolean percolates() {
        return pUF.connected(length * length + 1, 0);
    }

}
