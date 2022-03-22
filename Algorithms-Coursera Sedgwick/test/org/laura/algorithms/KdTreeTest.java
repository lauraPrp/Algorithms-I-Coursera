package org.laura.algorithms;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class KdTreeTest {
    @Test
    void testInsert() {
        KdTree kdTree = new KdTree();
        Point2D point2D1 = new Point2D(1, 1);
        Point2D point2D2 = new Point2D(1, 2);

        kdTree.insert(point2D1);
        kdTree.insert(point2D2);

        assertTrue(kdTree.contains(point2D1));
        assertTrue(kdTree.contains(point2D2));
    }

    @Test
    void testDuplicatePoint() {
        KdTree kdTree = new KdTree();
        Point2D point2D1 = new Point2D(1, 1);
        Point2D point2D2 = new Point2D(1, 1);

        kdTree.insert(point2D1);
        kdTree.insert(point2D2);

        assertTrue(kdTree.contains(point2D1));
        assertEquals(1, kdTree.size());
    }

    @Test
    void testRangeSearch_AllPointsInside() {
        KdTree kdTree = new KdTree();
        Point2D point2D1 = new Point2D(0.1, 0.1);
        Point2D point2D2 = new Point2D(0.1, 0.2);
        Point2D point2D3 = new Point2D(0.1, 0.3);

        kdTree.insert(point2D1);
        kdTree.insert(point2D2);
        kdTree.insert(point2D3);

        RectHV rectHV = new RectHV(0, 0, 1, 1);
        Iterable<Point2D> pointsInRange = kdTree.range(rectHV);
        List<Point2D> pointsList = new ArrayList<>();
        pointsInRange.forEach(pointsList::add);
        assertEquals(3, pointsList.size());
    }

    @Test
    void testRangeSearch_OnePointOutside() {
        KdTree kdTree = new KdTree();
        Point2D point2D1 = new Point2D(0.1, 0.1);
        Point2D point2D2 = new Point2D(0.1, 0.2);
        Point2D point2D3 = new Point2D(0.1, 0.3);

        kdTree.insert(point2D1);
        kdTree.insert(point2D2);
        kdTree.insert(point2D3);

        RectHV rectHV = new RectHV(0, 0, 1, 0.2);
        Iterable<Point2D> pointsInRange = kdTree.range(rectHV);
        List<Point2D> pointsList = new ArrayList<>();
        pointsInRange.forEach(pointsList::add);
        assertEquals(2, pointsList.size());
    }

    @Test
    void testNearestNeighbor() {
        KdTree kdTree = new KdTree();
        Point2D point2D1 = new Point2D(0.1, 0.9);
        Point2D point2D2 = new Point2D(0.5, 0.5);
        Point2D point2D3 = new Point2D(0.9, 0.1);

        kdTree.insert(point2D1);
        kdTree.insert(point2D2);
        kdTree.insert(point2D3);

        Point2D point1 = kdTree.nearestPoint(new Point2D(0.1, 0.8));
        assertEquals(point1, point2D1);
        Point2D point2 = kdTree.nearestPoint(new Point2D(0.5, 0.6));
        assertEquals(point2, point2D2);
        Point2D point3 = kdTree.nearestPoint(new Point2D(0.8, 0.1));
        assertEquals(point3, point2D3);
    }

    @Test
    void testNearestNeighbor_2() {
        KdTree kdTree = new KdTree();
        Point2D brutePoint = new Point2D(0.259123, 0.938153);
        Point2D kdTreePoint = new Point2D(0.740877, 0.938153);

        kdTree.insert(brutePoint);
        kdTree.insert(kdTreePoint);

        Point2D nearestPoint = kdTree.nearestPoint(new Point2D(0.279296875, 0.9140625));

        assertTrue(nearestPoint.x() == brutePoint.x() && nearestPoint.y() == brutePoint.y());
    }
}
