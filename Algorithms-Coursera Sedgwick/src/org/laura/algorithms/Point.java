package org.laura.algorithms;

import java.util.Comparator;

import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point>
{
    private int x;
    private int y;

    final private Comparator<Point> SLOPE_ORDER = new bySlope();

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void draw()
    {
        StdDraw.point(this.x, this.y);
    }

    public void drawTo(Point that)
    {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }

    public int compareTo(Point that)
    {
        if (this.y < that.y)
            return -1;
        else if (this.y == that.y && this.x < that.x)
            return -1;
        else if (this.y == that.y && this.x > that.x)
            return 1;
        else if (this.y > that.y)
            return 1;
        else
            return 0;
    }

    public double slopeTo(Point that)
    {
        //Slope of horizontal line
        if (this.y == that.y && this.x != that.x)
            return +0.0;

            //Slope of vertical line
        else if (this.x == that.x && this.y != that.y)
            return Double.POSITIVE_INFINITY;

            //Slope of degenerate line
        else if (this.y == that.y && this.x == that.x)
            return Double.NEGATIVE_INFINITY;

        else
            return (double) (that.y - this.y) / (that.x - this.x);
    }

    public Comparator<Point> slopeOrder()
    {
        return SLOPE_ORDER;
    }

    private class bySlope implements Comparator<Point>
    {
        public int compare(Point p, Point q)
        {
            double slopeToP = Point.this.slopeTo(p);
            double slopeToQ = Point.this.slopeTo(q);

            if (slopeToP < slopeToQ)
                return -1;
            else if (slopeToP == slopeToQ)
                return +0;
            else
                return 1;
        }
    }

    public static void main(String[] args)
    {
        Point o = new Point(0, 0);

        Point p = new Point(0, 0);
        Point q = new Point(3, 3);

        double slopeOP = o.slopeTo(p);
        double slopeOQ = o.slopeTo(q);

        System.out.println("slopeOP: " + slopeOP);
        System.out.println("slopeOQ: " + slopeOQ);
    }

}