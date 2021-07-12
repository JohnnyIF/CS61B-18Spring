package hw4.puzzle;
import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {

    private int [][] tiles;
    private int N;
    private final int BLANK = 0;

    public Board(int[][] tiles) {
        this.N = tiles.length;
        // be immutable
        this.tiles = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }

    public int tileAt(int i, int j) {
        if (i < 0 || i >= N || j < 0 || j >= N) {
            throw new IndexOutOfBoundsException();
        }
        return tiles[i][j];

    }
    public int size() {
        return N;

    }
    /**
     * Returns the neighbors of the current board
     *
     * @author http://joshh.ug/neighbors.html
     */
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }
    public int hamming() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tileAt(i, j) == BLANK) {
                    continue;
                }
                if (tileAt(i, j) != N * i + j + 1) {
                    count += 1;
                }
            }
        }
        return count;
    }
    public int manhattan() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tileAt(i, j) == BLANK) {
                    continue;
                }
                if (tileAt(i, j) != N * i + j + 1) {
                    count += getDiff(tileAt(i, j), i, j);
                }
            }
        }

        return count;
    }

    private int[] intToXY(int s) {
        return new int[] { (s - 1) / N, (s - 1) % N };
    }

    private int getDiff(int val, int i, int j) {
        int count = 0;
        int[] rightPos = intToXY(val);
        count += rightPos[0] > i ? rightPos[0] - i : i - rightPos[0];
        count += rightPos[1] > j ? rightPos[1] - j : j - rightPos[1];
        return count;
    }

    public int estimatedDistanceToGoal() {
        return manhattan();
    }
    public boolean equals(Object y) {
        Board board = (Board) y;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tileAt(i, j) != board.tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /** Returns the string representation of the board.
     * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
