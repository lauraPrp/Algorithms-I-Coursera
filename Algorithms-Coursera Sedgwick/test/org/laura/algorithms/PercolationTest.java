package org.laura.algorithms;

import org.junit.jupiter.api.Test;
import org.laura.algorithms.Percolation;

import static org.junit.jupiter.api.Assertions.assertTrue;

 class PercolationTest {
    @Test
     void isOpen() throws Exception {
        Percolation percolation = new Percolation(4);
        assertTrue(!percolation.isOpen(1, 3));
        percolation.open(1, 3);
        assertTrue(percolation.isOpen(1, 3));
    }

    @Test
     void isFull() throws Exception {
        Percolation percolation = new Percolation(4);

        assertTrue(!percolation.isFull(1, 3));
        percolation.open(1, 3);
        assertTrue(percolation.isFull(1, 3));
        percolation.open(2, 4);
        assertTrue(!percolation.isFull(2, 4));
        percolation.open(3, 4);
        assertTrue(!percolation.isFull(3, 4));
        percolation.open(4, 4);
        assertTrue(!percolation.isFull(4, 4));
        percolation.open(1, 4);
        assertTrue(percolation.isFull(1, 4));
        assertTrue(percolation.isFull(2, 4));
        assertTrue(percolation.isFull(3, 4));
        assertTrue(percolation.isFull(4, 4));

    }

    @Test
     void percolates() throws Exception {
        Percolation percolation = new Percolation(4);
        assertTrue(!percolation.percolates());
        percolation.open(1, 3);
        percolation.open(2, 4);
        percolation.open(3, 4);
        percolation.open(4, 4);
        assertTrue(!percolation.percolates());
        percolation.open(1, 4);
        assertTrue(percolation.percolates());
    }

    @Test
     void numberOfOpenSites() throws Exception {
        Percolation percolation = new Percolation(4);
        percolation.open(1, 3);
        percolation.open(2, 4);
        percolation.open(3, 4);
        percolation.open(4, 4);
        percolation.open(1, 4);
        assertTrue(percolation.numberOfOpenSites() == 5);
    }

}