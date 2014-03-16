package coursera.princeton.algorithm.part1.week3.assignment;

import coursera.princeton.algorithm.util.Constants;
import edu.princeton.cs.introcs.In;
import edu.princeton.cs.introcs.StdDraw;

public class Brute {
    public static void main(String[] args) {
        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

//      In in = new In(Constants.MY_INPUT_FILE);
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

        // display to screen all at once
        // StdDraw.show(0);

        for (int i = 0; i < N - 3; i++) {
            Point p = points[i];
            for (int j = i + 1; j < N - 2; j++) {
                Point q = points[j];
                for (int k = j + 1; k < N - 1; k++) {
                    Point r = points[k];
                    for (int l = k + 1; l < N; l++) {
                        Point s = points[l];
                        double slope_pq = p.slopeTo(q);
                        double slope_pr = p.slopeTo(r);
                        double slopw_ps = p.slopeTo(s);
                        if (slope_pq == slope_pr && slope_pq == slopw_ps) {
                            System.out.println(p + " -> " + q + " -> " + r
                                    + " -> " + s);
                        }
                    }
                }
            }
        }

    }

}
