import java.util.Arrays;

public class Brute {
    public static void main(String[] args) {
        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

        // In in = new In(
        // coursera.princeton.algorithm.util.Constants.INPUT6_FILE);
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
                        double slopepq = p.slopeTo(q);
                        double slopepr = p.slopeTo(r);
                        double slopwps = p.slopeTo(s);
                        if (slopepq == slopepr && slopepq == slopwps) {
                            Point[] lineSegment = new Point[4];
                            lineSegment[0] = p;
                            lineSegment[1] = q;
                            lineSegment[2] = r;
                            lineSegment[3] = s;
                            Arrays.sort(lineSegment);
                            for (int h = 0; h < 3; h++) {
                                System.out.print(lineSegment[h] + " -> ");
                            }
                            System.out.println(lineSegment[3]);
                            lineSegment[0].drawTo(lineSegment[3]);
                        }
                    }
                }
            }
        }

    }

}
