import java.util.Arrays;

//import junit.framework.Assert;
//import org.junit.Test;

public class Fast {

    public static void main(String[] args) {
        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);

//         In in = new In(
//         coursera.princeton.algorithm.util.Constants.INPUT6_FILE);
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
            Arrays.sort(points, 0, i, p.SLOPE_ORDER);
            int consecutive = 0;
            for (int j = i + 1; j < N; j++) {
                if (j < N - 1
                        && p.slopeTo(points[j]) == p.slopeTo(points[j + 1])) {
                    consecutive++;
                } else {
                    if (consecutive >= 2) {
                        // Is the current line segment is the subsegment of
                        // others?
                        int isSubSegment = binarySearchBySlope(points, 0, i, p.slopeTo(points[j]));
                        if (isSubSegment==-1) {
                            // Output and draw the line segment
                            int segSize = consecutive + 2;
                            Point[] lineSegment = new Point[segSize];
                            lineSegment[0] = p;
                            for (int k = j - consecutive, h = 1; k <= j; k++, h++) {
                                lineSegment[h] = points[k];
                            }
                            Arrays.sort(lineSegment);
                            for (int h = 0; h < segSize - 1; h++) {
                                System.out.print(lineSegment[h] + " -> ");
                            }
                            System.out.println(lineSegment[segSize - 1]);
                            lineSegment[0].drawTo(lineSegment[segSize - 1]);
                        }
                    }

                    consecutive = 0; // reset consecutive
                }
            }
        }
    }
    
    /**
     * 
     * @param points: target array be search
     * @param low   : included
     * @param high  : excluded
     * @param slope : to be searched
     * @return
     */
    private static int binarySearchBySlope(Point[] points, int low, int high, double slope){
        Point p = points[high];
        int lo = low;
        int hi = high;
        while(lo<hi) {
            int pos = lo+(hi-lo)/2;
            double currentSlope = p.slopeTo(points[pos]);
            if (currentSlope == slope) {
                return pos;
            }else if(currentSlope>slope){
                hi= pos;
            }else{
                lo = pos+1;
            }
        }
        return -1;
    }
    
//    @Test
//    public void test(){
//        Point[] points = new Point[]{
//                new Point(0,0),
//                new Point(5,1),
//                new Point(5,2),
//                new Point(5,3),
//                new Point(5,4),
//                new Point(5,5),
//                new Point(5,6),
//                new Point(5,7),
//                new Point(0,0)
//        };
//        Assert.assertEquals(2, binarySearchBySlope(points, 0, 8, (double)0.4));
//        Assert.assertEquals(2, binarySearchBySlope(points, 1, 8, (double)0.4));
//        Assert.assertEquals(0, binarySearchBySlope(points, 0, 8, Double.NEGATIVE_INFINITY));
//        Assert.assertEquals(7, binarySearchBySlope(points, 0, 8, (double)1.4));
//        Assert.assertEquals(7, binarySearchBySlope(points, 1, 8, (double)1.4));
//    }
} 
