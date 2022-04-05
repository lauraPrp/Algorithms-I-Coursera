package org.laura.algorithms;

import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UnionFindTest {
    UnionFind uf;
@BeforeEach
void setUp() {
      uf = new UnionFind(10);
    // 1-2-5-6-7 3-8-9 4
    uf.union    (1,2);
    uf.union    (2, 5);
    uf.union(5, 6);
    uf.union(6, 7);
    uf.union(3, 8);
    uf.union(8, 9);

}


    @org.junit.jupiter.api.Test
    void shouldCheckConnected() {
        assertTrue(uf.connected(1, 2));
        assertTrue(uf.connected(1, 5));
        assertTrue(uf.connected(1, 6));
        assertTrue(uf.connected(1, 7));
        assertTrue(uf.connected(2, 5));
        assertTrue(uf.connected(2, 6));
        assertTrue(uf.connected(2, 7));
        assertTrue(uf.connected(3, 8));
        assertTrue(uf.connected(3, 9));
        assertFalse(uf.connected(4, 9));
        assertFalse(uf.connected(1, 3));
        assertFalse(uf.connected(1, 4));
        assertFalse(uf.connected(2, 3));
        assertFalse(uf.connected(2, 4));
        assertFalse(uf.connected(5, 3));
        assertFalse(uf.connected(5, 4));
        assertFalse(uf.connected(6, 3));
        assertFalse(uf.connected(6, 4));
        assertFalse(uf.connected(7, 3));
        assertFalse(uf.connected(7, 4));
        assertFalse(uf.connected(8, 1));
        assertFalse(uf.connected(8, 2));
        assertFalse(uf.connected(8, 5));
        assertFalse(uf.connected(8, 6));
        assertFalse(uf.connected(8, 7));
        assertFalse(uf.connected(9, 1));
        assertFalse(uf.connected(9, 2));
        assertFalse(uf.connected(9, 5));
        assertFalse(uf.connected(9, 6));
        assertFalse(uf.connected(9, 7));
    }

    @org.junit.jupiter.api.Test
    void union() {
        uf.union(1, 2);
        uf.union(2, 5);
        uf.union(5, 6);
        uf.union(6, 7);
        uf.union(3, 8);
        uf.union(8, 9);

        assertTrue(uf.connected(1, 5)); // true
        assertTrue(uf.connected(5, 7)); // true
        assertFalse(uf.connected(4, 9)); // false

        // 1-2-5-6-7
        // 3-8-9-4
        uf.union(9, 4);
        assertTrue(uf.connected(4, 9)); // true
    }
}