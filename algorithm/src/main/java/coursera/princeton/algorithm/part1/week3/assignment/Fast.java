package coursera.princeton.algorithm.part1.week3.assignment;

import java.util.Arrays;

import coursera.princeton.algorithm.util.Constants;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdDraw;

public class Fast {

    public static void main(String[] args) {
        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

        // In in = new In(Constants.MY_INPUT_FILE);
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            points[i] = p;
            p.draw();
        }
        for (int i = 0; i < N - 1; i++) {
            Point p = points[i];
            Arrays.sort(points, i + 1, N, p.SLOPE_ORDER);
            int consecutive = 0;
            for (int j = i + 1; j < N - 1; j++) {
                if (p.slopeTo(points[j]) == p.slopeTo(points[j + 1])) {
                    consecutive++;
                } else {
                    if (consecutive >= 2) {
                        // Is the current line segment is the subsegment of
                        // others?
                        boolean isSubSegment = false;
                        for (int m = 0; m < i; m++) {
                            if (p.slopeTo(points[m]) == p.slopeTo(points[j])) {
                                isSubSegment = true;
                                break;
                            }
                        }
                        if (!isSubSegment) {
                            // Output and draw the line segment
                            System.out.print(p);
                            for (int k = j - consecutive; k <= j; k++) {
                                System.out.print(" -> " + points[k]);
                                p.drawTo(points[k]);
                            }
                            System.out.println();
                        }
                    }

                    consecutive = 0; // reset consecutive
                }
            }
        }
        StdDraw.show();
    }
}
