package coursera.princeton.algorithm.part1.week1;
import junit.framework.Assert;

import org.junit.Test;

import coursera.princeton.algorithm.part1.week1.assignment.Percolation;

public class PercolationTest {

    @Test
    public void testIsFull() {
        Percolation perc = new Percolation(3);
        perc.open(1, 3);
        Assert.assertTrue(perc.isFull(1, 3));
        perc.open(3, 1);
        Assert.assertFalse(perc.isFull(3, 1));
        perc.open(2, 2);
        Assert.assertFalse(perc.isFull(2, 2));
        perc.open(2, 1);
        Assert.assertFalse(perc.isFull(2, 1));
        perc.open(2, 3);
        Assert.assertTrue(perc.isFull(2, 3));
        Assert.assertTrue(perc.isFull(2, 2));
        Assert.assertTrue(perc.isFull(2, 1));
        Assert.assertTrue(perc.isFull(3, 1));
        Assert.assertTrue(perc.percolates());
    }

    /*
     * Test N = 1
     */
    @Test
    public void testCornerCase() {
        Percolation perc = new Percolation(1);
        perc.open(1, 1);
        Assert.assertTrue(perc.percolates());
    }
}
