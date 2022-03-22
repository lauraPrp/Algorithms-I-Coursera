package org.laura.algorithms;

import java.util.LinkedList;
import java.util.Queue;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Board {
    private int[][] initialBoard;
    private int[][] goalBoard;
    private int manhattan;
    private int hamming;
    private final int n;
    private boolean hammingCalculated;
    private boolean manhattanCalculated;

    public Board(int[][] blocks) {
        n = blocks.length;
        initialBoard = copyBlocks(blocks);
        manhattan = 0;
        hamming = 0;
        manhattanCalculated = false;
        hammingCalculated = false;
        goalBoard = new int[n][n];
        createGoalBlocks();
    }

    private void createGoalBlocks() {
        int counter = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == n - 1 && j == n - 1) {
                    goalBoard[i][j] = 0;
                    break;
                }
                goalBoard[i][j] = counter;
                counter++;
            }
        }
    }

    public int dimension() {
        return n;
    }

    public int hamming() {
        if (hammingCalculated) return hamming;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if ((initialBoard[i][j] != goalBoard[i][j]) && (initialBoard[i][j] != 0)) hamming++;
            }
        }

        hammingCalculated = true;
        return hamming;
    }

    public int manhattan() {
        if (manhattanCalculated) return manhattan;

        /*@todo:needs refactoring*/
        boolean flag = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (initialBoard[i][j] != goalBoard[i][j] || initialBoard[i][j] != 0) {
                    // Getting the goalBoard coordinates x and y
                    int goalX = 0;
                    int goalY = 0;
                    for (int k = 0; k < n; k++) {
                        for (int l = 0; l < n; l++) {
                            if (initialBoard[i][j] == goalBoard[k][l]) {
                                goalX = k;
                                goalY = l;
                                flag = true;
                                break;
                            }
                        }
                        if (flag) break;
                    }
                    manhattan = manhattan + Math.abs(i - goalX);
                    manhattan = manhattan + Math.abs(j - goalY);
                    flag = false;
                }
            }
        }

        manhattanCalculated = true;
        return manhattan;
    }

    public boolean isGoal() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (initialBoard[i][j] != goalBoard[i][j]) return false;
            }
        }
        return true;
    }

    public Board twin() {
        int[][] twinBlocks;
        twinBlocks = copyBlocks(this.initialBoard);
        int x1 = -1;
        int y1 = -1;
        int x2 = -1;
        int y2 = -1;
        int firstBlock = -1;
        boolean firstBlockFound = false;
        int secondBlock = -1;
        boolean secondBlockFound = false;

        // finding non-zero x1, y1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (twinBlocks[i][j] != 0) {
                    firstBlock = twinBlocks[i][j];
                    x1 = i;
                    y1 = j;
                    firstBlockFound = true;
                    break;
                }
            }
            if (firstBlockFound) break;
        }

        // finding 2nd non-zero block that is different from firstBlock;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (twinBlocks[i][j] != 0 && twinBlocks[i][j] != firstBlock) {
                    secondBlock = twinBlocks[i][j];
                    x2 = i;
                    y2 = j;
                    secondBlockFound = true;
                    break;
                }
            }
            if (secondBlockFound) break;
        }


        twinBlocks[x1][y1] = secondBlock;
        twinBlocks[x2][y2] = firstBlock;

        return new Board(twinBlocks);
    }

    public boolean equals(Object object) {
        if (object == null) return false;

        if (!(object.getClass().equals(Board.class))) return false;

        Board that = (Board) object;
        if (this.dimension() != that.dimension()) return false;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.initialBoard[i][j] != that.initialBoard[i][j]) return false;
            }
        }
        return true;
    }

    public Iterable<Board> neighbors() {
        Queue<Board> queue = new LinkedList<>();

        int x = 0;
        int y = 0;

        boolean foundZero = false;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (initialBoard[i][j] == 0) {
                    x = i;
                    y = j;
                    foundZero = true;
                    break;
                }
            }
            if (foundZero) break;
        }

        // Exchanging with upper block
        if (y - 1 >= 0) {
            int[][] copy = copyBlocks(this.initialBoard);
            copy[x][y - 1] = this.initialBoard[x][y];
            copy[x][y] = this.initialBoard[x][y - 1];
            Board firstNeighbor = new Board(copy);
            queue.add(firstNeighbor);
        }

        // Exchanging with lower block
        if (y + 1 < n) {
            int[][] copy = copyBlocks(this.initialBoard);
            copy[x][y + 1] = this.initialBoard[x][y];
            copy[x][y] = this.initialBoard[x][y + 1];
            Board secondNeighbor = new Board(copy);
            queue.add(secondNeighbor);
        }

        // Exchanging with left block
        if (x - 1 >= 0) {
            int[][] copy = copyBlocks(this.initialBoard);
            copy[x - 1][y] = this.initialBoard[x][y];
            copy[x][y] = this.initialBoard[x - 1][y];
            Board thirdNeighbor = new Board(copy);
            queue.add(thirdNeighbor);
        }

        // Exchanging with right block
        if (x + 1 < n) {
            int[][] copy = copyBlocks(this.initialBoard);
            copy[x + 1][y] = this.initialBoard[x][y];
            copy[x][y] = this.initialBoard[x + 1][y];
            Board fourthNeighbor = new Board(copy);
            queue.add(fourthNeighbor);
        }

        return queue;
    }

    private int[][] copyBlocks(int[][] original) {
        int[][] copy = new int[original.length][original.length];
        for (int i = 0; i < n; i++) {
            System.arraycopy(original[i], 0, copy[i], 0, n);
        }
        return copy;
    }

    public String toString() {
        StringBuilder output = new StringBuilder(n + "\n");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                output.append(initialBoard[i][j]).append(" ");
            }
            output.append('\n');
        }
        return output.toString();
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                blocks[i][j] = StdIn.readInt();
            }
        }

        Board board = new Board(blocks);
        StdOut.println(board.hamming());
        blocks[0][0] = 1;
        blocks[2][1] = 5;
        StdOut.println(board.hamming());
    }
}