package org.laura.algorithms;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {

    private LineSegment[] segments;

    /*@todo:refactor to simplify*/
    public FastCollinearPoints(Point[] points) {

        checkNulls(points);

        Point[] sortedPointsClone = points.clone();
        Arrays.sort(sortedPointsClone);
        for (int i = 0; i < sortedPointsClone.length - 1; i++) {
            if (sortedPointsClone[i].compareTo(sortedPointsClone[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }

        List<LineSegment> segmentsLst = new ArrayList<>();
        List<Double> slopes = new ArrayList<>();
        List<Point> startPoints = new ArrayList<>();
        Point[] pointsSort = sortedPointsClone.clone();

        for (int i = 0; i < sortedPointsClone.length - 3; i++) {
            Arrays.sort(pointsSort, sortedPointsClone[i].slopeOrder());
            List<Point> line = null;
            int n = 0;
            int count = 0;

            while (n < pointsSort.length - 2) {
                double slope = sortedPointsClone[i].slopeTo(pointsSort[n]);

                if (count == 0) {
                    line = new ArrayList<>();
                    line.add(pointsSort[n]);
                    count++;
                }

                while (n < pointsSort.length - 1
                        && slope == sortedPointsClone[i].slopeTo(pointsSort[n + 1])) {
                    line.add(pointsSort[++n]);
                    count++;
                }

                if (count >= 3) {

                    line.add(sortedPointsClone[i]);
                    Point[] lineArray = new Point[line.size()];
                    int j = 0;
                    for (Point p : line) {
                        lineArray[j++] = p;
                    }
                    Arrays.sort(lineArray);

                    boolean existFlag = false;
                    for (int k = 0; k < startPoints.size(); k++) {
                        if (startPoints.get(k).compareTo(lineArray[0]) == 0
                                && slopes.get(k) == slope) {
                            existFlag = true;
                            break;
                        }
                    }

                    if (!existFlag) {
                        segmentsLst.add(new LineSegment(lineArray[0], lineArray[lineArray.length - 1]));
                        slopes.add(slope);
                        startPoints.add(lineArray[0]);
                    }

                }

                count = 0;
                n++;
            }
        }

        segments = new LineSegment[segmentsLst.size()];
        int i = 0;
        for (LineSegment ls : segmentsLst) {
            segments[i++] = ls;
        }

    }


    public LineSegment[] segments() {
        return segments;
    }

    private void checkNulls(Point[] points) {
        if (points == null) {
            throw new NullPointerException("The array is null.");
        }
        for (Point p : points) {
            if (p == null) {
                throw new NullPointerException(
                        "The array contains null element.");
            }
        }
    }

    /**
     * client provided by Princeton University.
     */
    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}