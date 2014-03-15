package coursera.princeton.algorithm.util;

import java.io.File;

import coursera.princeton.algorithm.part1.week3.assignment.Point;
import edu.princeton.cs.introcs.In;

public class PointsReader implements IReader<Point> {

    @Override
    public Point[] load(File file) {
        In in = new In(file);
        int len = in.readInt();
        Point[] points = new Point[len];
        for(int i=0;i<len;i++){
            int x = in.readInt();
            int y = in.readInt();
            points[i]=new Point(x,y);
        }
        return points;
    }

}
