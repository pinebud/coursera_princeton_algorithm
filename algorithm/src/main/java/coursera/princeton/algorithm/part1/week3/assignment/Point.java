package coursera.princeton.algorithm.part1.week3.assignment;
/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/
import java.util.Comparator;

import edu.princeton.cs.introcs.StdDraw;

public class Point implements Comparable<Point> {
    private final int x;
    private final int y;

    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {

        @Override
        public int compare(Point o1, Point o2) {
            double slope1 = slopeTo(o1);
            double slope2 = slopeTo(o2);            
            if(slope1<slope2){
                return -1;
            }else if(slope1>slope2){
                return 1;
            }else{
                return 0;
            }
        }

    }; // compare points by slope to this point

    public Point(int x, int y) {
        // construct the point (x, y)
        this.x = x;
        this.y = y;
    }

    public void draw() {
        // draw this point
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        // draw the line segment from this point to that point
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public String toString() { // string representation
        return "(" + x + ", " + y + ")";
    }

    public int compareTo(Point that) {
        // is this point lexicographically
        // smaller than that point?
        if (y < that.y) {
            return -1;
        } else if (y > that.y) {
            return 1;
        } else {
            if (x < that.x) {
                return -1;
            } else if (x > that.x) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public double slopeTo(Point that) { 
        // the slope between this point and that point
        if(x==that.x){
            if(y==that.y)
                return Double.NEGATIVE_INFINITY; //degenerate
            else
                return Double.POSITIVE_INFINITY; //Vertical
        }else{
            return (that.y-y)/(that.x-x);
        }
    }
    
    @Override
    public boolean equals(Object o){
        if(o instanceof Point){
            Point p = (Point)o;
            return p.x == x && p.y==y;
        }
        return false;
    }
}
