package org.laura.algorithms;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.ArrayList;
import java.util.List;

public class KdTree {
    private Node root;
    private int size;

    private static class Node {
        private final Point2D p;
        private final RectHV rect;
        private Node lb;
        private Node rt;
        private Direction direction;

        public Node(Point2D p, RectHV rect, Node lb, Node rt, Direction direction) {
            this.p = p;
            this.rect = rect;
            this.lb = lb;
            this.rt = rt;
            this.direction = direction;
        }

        public enum Direction {
            VERTICAL, HORIZONTAL
        }
    }

    public boolean isEmpty() {
        return (this.root == null);
    }

    public int size() {
        return this.size;
    }

    public void insert(Point2D p) {
        root = insert(root, p, Node.Direction.VERTICAL, 0.0, 0.0, 1.0, 1.0);
    }

    private Node insert(Node node, Point2D p, Node.Direction direction,
                        double xMin, double yMin, double xMax, double yMax) {
        if (node == null) {
            if (contains(p)) {
                return null;
            }
            size++;
            RectHV rectHV = new RectHV(xMin, yMin, xMax, yMax);
            return new Node(p, rectHV, null, null, direction);
        }

        if (node.direction == Node.Direction.VERTICAL) {
            if (p.x() < node.p.x())
                node.lb = insert(
                        node.lb,
                        p,
                        Node.Direction.HORIZONTAL,
                        node.rect.xmin(),
                        node.rect.ymin(),
                        node.p.x(), // xMax
                        node.rect.ymax()
                );
            else
                node.rt = insert(
                        node.rt,
                        p,
                        Node.Direction.HORIZONTAL,
                        node.p.x(), // xMin
                        node.rect.ymin(),
                        node.rect.xmax(),
                        node.rect.ymax()
                );
        } else // Node.Direction.HORIZONTAL
        {
            if (p.y() < node.p.y())
                node.lb = insert(
                        node.lb,
                        p,
                        Node.Direction.VERTICAL,
                        node.rect.xmin(),
                        node.rect.ymin(),
                        node.rect.xmax(),
                        node.p.y() // yMax
                );
            else
                node.rt = insert(
                        node.rt,
                        p,
                        Node.Direction.VERTICAL,
                        node.rect.xmin(),
                        node.p.y(), // yMin
                        node.rect.xmax(),
                        node.rect.ymax()
                );
        }

        return node;
    }

    public boolean contains(Point2D p) {
        return contains(root, p);
    }

    private boolean contains(Node node, Point2D p) {
        if (node == null)
            return false;

        if (node.p.x() == p.x() && node.p.y() == p.y()) {
            return true;
        } else if (node.direction == Node.Direction.VERTICAL) {
            if (p.x() < node.p.x())
                return contains(node.lb, p);
            else
                return contains(node.rt, p);
        } else {
            if (p.y() < node.p.y())
                return contains(node.lb, p);
            else
                return contains(node.rt, p);
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        List<Point2D> points = new ArrayList<>();
        return range(root, rect, points);
    }

    private List<Point2D> range(Node node, RectHV rect, List<Point2D> points) {
        if (node == null) return points;
        if (rect.contains(node.p)) points.add(node.p);

        if (node.lb != null && node.lb.rect.intersects(rect)) range(node.lb, rect, points);
        if (node.rt != null && node.rt.rect.intersects(rect)) range(node.rt, rect, points);

        return points;
    }

    public Point2D nearestPoint(Point2D p) {
        if (isEmpty()) return null;
        return nearestPoint(root, p, root.p);
    }

    private Point2D nearestPoint(Node node, Point2D p, Point2D nearestNeighbor) {
        if (node == null) return nearestNeighbor;

        if (node.p.distanceSquaredTo(p) < p.distanceSquaredTo(nearestNeighbor)) {
            nearestNeighbor = node.p;
        }

        if (node.direction == Node.Direction.VERTICAL) {
            if (p.x() < node.p.x()) {
                nearestNeighbor = checkLeftSubTree(node, p, nearestNeighbor);

            } else {
                nearestNeighbor = checkRightSubTree(node, p, nearestNeighbor);
            }
        } else {
            if (p.y() > node.p.y()) {
                nearestNeighbor = checkLeftSubTree(node, p, nearestNeighbor);
            } else {
                nearestNeighbor = checkRightSubTree(node, p, nearestNeighbor);
            }
        }

        return nearestNeighbor;
    }

    private Point2D checkLeftSubTree(Node node, Point2D p, Point2D nearestNeighbor) {
        nearestNeighbor = nearestPoint(node.lb, p, nearestNeighbor);
        if (node.rt != null && node.rt.rect.distanceSquaredTo(p) < nearestNeighbor.distanceSquaredTo(p)) {
            nearestNeighbor = nearestPoint(node.rt, p, nearestNeighbor);
        }
        return nearestNeighbor;
    }

    private Point2D checkRightSubTree(Node node, Point2D p, Point2D nearestNeighbor) {
        // Checking right subtree first
        nearestNeighbor = nearestPoint(node.rt, p, nearestNeighbor);
        if (node.lb != null && node.lb.rect.distanceSquaredTo(p) < nearestNeighbor.distanceSquaredTo(p)) {
            nearestNeighbor = nearestPoint(node.lb, p, nearestNeighbor);
        }
        return nearestNeighbor;
    }

    public static void main(String[] args) {
        KdTree kdTree = new KdTree();
        Point2D point2D1 = new Point2D(0.7, 0.2);
        Point2D point2D2 = new Point2D(0.5, 0.4);
        Point2D point2D3 = new Point2D(0.2, 0.3);
        Point2D point2D4 = new Point2D(0.4, 0.7);
        Point2D point2D5 = new Point2D(0.9, 0.6);

        kdTree.insert(point2D1);
        kdTree.insert(point2D2);
        kdTree.insert(point2D3);
        kdTree.insert(point2D4);
        kdTree.insert(point2D5);
    }
}
