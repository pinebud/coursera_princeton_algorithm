package coursera.princeton.algorithm.part1.week3;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import coursera.princeton.algorithm.part1.week3.assignment.Point;
import coursera.princeton.algorithm.util.Constants;
import coursera.princeton.algorithm.util.IReader;
@ContextConfiguration("testContext-week3.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class PointTest {
    
    @Autowired(required=true)
    private IReader<Point> reader;

    @Test
    public void testSlopeTo(){
        Point p = new Point(1,1);
        Point p1 = new Point(2,2);
        Point p_horizontal = new Point(2,1);
        Point p_vertical = new Point(1,2);
        Assert.assertEquals((double)1.0, p.slopeTo(p1), 0.00001);
        Assert.assertEquals((double)0.0, p.slopeTo(p_horizontal), 0.00001);
        Assert.assertEquals(Double.POSITIVE_INFINITY, p.slopeTo(p_vertical), 0.00001);
        Assert.assertEquals(Double.NEGATIVE_INFINITY, p.slopeTo(p), 0.00001);
        
        Assert.assertTrue(Double.POSITIVE_INFINITY>Double.MAX_VALUE);
        Assert.assertTrue(Double.NEGATIVE_INFINITY<Double.MIN_VALUE);
        
      //Double.MIN_VALUE is the smallest POSITIVE value
        Assert.assertFalse(Double.MIN_VALUE<0);
    }
    
    @Test
    public void testSlopeTo2(){
        Point p = new Point(1,0);
        Point p1 = new Point(4,4);
        Assert.assertEquals((double)1.33333, p.slopeTo(p1), 0.00001);
    }
    
    @Test
    public void testSlopeComparator(){
        Point[] points = reader.load(Constants.MY_INPUT_FILE);
        Point invokingPoint = new Point(0,0);  
        Arrays.sort(points, invokingPoint.SLOPE_ORDER);
        for(Point p:points){
            System.out.println(p.toString()+"; slope to (0,0) is "+invokingPoint.slopeTo(p));
        }
    }
    
    @Test
    public void testCompareTo(){
        Point[] expectedSortedPoints = reader.load(Constants.MY_SORTED_INPUT_FILE);
        Point[] points = reader.load(Constants.MY_INPUT_FILE);
        Arrays.sort(points);
        for(Point p:points){
            System.out.println(p.toString());
        }
        Assert.assertArrayEquals(expectedSortedPoints, points);
    }
}
