package coursera.princeton.algorithm.part1.week1;

import static org.junit.Assert.*;
import junit.framework.Assert;
import java.net.URL;
import java.net.URLClassLoader;
 
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.List;

@ContextConfiguration("UFBasicTest-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class UFBasicTest {

    @Autowired(required = false)
    private List<IUF> unionFindMethods;

    @Test
    public void basicFunctionTest() {
        if (unionFindMethods == null) return;

        for (IUF ufm : unionFindMethods) {
          ufm.init(10);

          ufm.union(4, 3);
          ufm.union(3, 8);
          ufm.union(6, 5);
          ufm.union(9, 4);
          ufm.union(2, 1);
          Assert.assertTrue(ufm.connected(8, 9));
          Assert.assertFalse(ufm.connected(0, 5));

          ufm.union(5, 0);
          ufm.union(7, 2);
          ufm.union(6, 1);

          Integer s1 = (Integer) ufm.find(0);
          Assert.assertEquals(s1,ufm.find(1));
          Assert.assertEquals(s1,ufm.find(2));
          Assert.assertEquals(s1,ufm.find(5));
          Assert.assertEquals(s1,ufm.find(6));
          Assert.assertEquals(s1,ufm.find(7));
        
          Integer s2 = (Integer) ufm.find(3);
          Assert.assertEquals(s2,ufm.find(4));
          Assert.assertEquals(s2,ufm.find(8));
          Assert.assertEquals(s2,ufm.find(9));
        }
        
     }

    /*
     * TODO: Write 2 more cases 
     * 
1 2 3 4 5 6

N=6£¬ log6 = 2

Case 1:
u(1,3) u(1,4), u(2,5), u(1,5), u(1,6)

WQUnion
 1
3 4 2 5 6

WQUnionwithPathCompression
1
3 4 2 6
     5


Case 2:
u(1,3) u(1,4), u(2,5), u(2,6), u(1,2)
 1
3 4  2
    5 6
     */
}
