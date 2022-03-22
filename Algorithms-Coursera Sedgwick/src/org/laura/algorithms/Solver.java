package org.laura.algorithms;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private int moves;
    private boolean solvable;
    private SearchNode node;
    private SearchNode solutionNode;

    public Solver(Board board) {
        if (board == null) {
            throw new NullPointerException("no argument passed");
        }
        MinPQ<SearchNode> mp = new MinPQ<>();
        this.node = new SearchNode(board, new SearchNode(board, null));
        SearchNode start = node;
        SearchNode nodeTwin = new SearchNode(board.twin(), null);
        mp.insert(node);
        mp.insert(nodeTwin);
        while (!node.board.isGoal()) {
            node = mp.delMin();
            for (Board b : node.board.neighbors()) {
                if (node.previous == null || !b.equals(node.previous.board)) {
                    SearchNode next = new SearchNode(b, node);
                    mp.insert(next);
                }
            }

        }

        moves = node.moves;
        solutionNode = node;

        if (start.board != solution().iterator().next()) {
            solutionNode = nodeTwin;
            solvable = false;
        } else if (node.board.isGoal()) {
            solutionNode = node;
            solvable = true;
        } else {
            solutionNode = null;
            solvable = false;
        }
    }

    private class SearchNode implements Comparable<SearchNode> {
        private Board board;
        private SearchNode previous;
        private int moves;
        private int priority;

        public SearchNode(Board board, SearchNode previous) {
            this.board = board;
            if (previous == null) {
                this.previous = null;
                this.moves = 0;
            } else {

                this.moves = previous.moves + 1;

                this.previous = previous;
            }
            this.priority = board.manhattan() + moves;

        }

        @Override
        public int compareTo(SearchNode that) {
            if (this.priority == that.priority) {
                return this.board.hamming() - that.board.hamming();
            }
            return this.priority - that.priority;
        }
    }

    public boolean isNotSolvable() {
        return !solvable;
    }

    public int moves() {
        if (isNotSolvable()) {
            return -1;
        } else {
            return moves - 1;
        }
    }

    public Iterable<Board> solution() {
        if (isNotSolvable()) {
            return null;
        }
        Stack<Board> st = new Stack<>();
        if (solutionNode.previous == null) {
            st.push(solutionNode.board);
        } else {
            while (solutionNode.previous != null) {
                st.push(solutionNode.board);
                solutionNode = solutionNode.previous;
            }
        }

        return st;
    }

    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution in console
        if (solver.isNotSolvable())
            StdOut.println("Impossible to solve");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);

        }

    }

}