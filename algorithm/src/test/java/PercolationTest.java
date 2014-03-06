import junit.framework.Assert;

import org.junit.Test;


public class PercolationTest {

    @Test
    public void testIsFull(){
        Percolation perc = new Percolation(3);
        perc.open(1, 3);
        Assert.assertTrue(perc.isFull(1, 3));
        perc.open(3, 1);
        Assert.assertFalse(perc.isFull(3,1));
        perc.open(2, 2);
        Assert.assertFalse(perc.isFull(2,2));
        perc.open(2, 1);
        Assert.assertFalse(perc.isFull(2,1));
        perc.open(2, 3);
        Assert.assertTrue(perc.isFull(2,3));
        Assert.assertTrue(perc.isFull(2,2));
        Assert.assertTrue(perc.isFull(2,1));
        Assert.assertTrue(perc.isFull(3,1));
        Assert.assertTrue(perc.percolates());
    }
    
    @Test
    public void test2(){
        Percolation perc = new Percolation(6);
        perc.open(1, 6);
    }
}
