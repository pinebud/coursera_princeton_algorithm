package coursera.princeton.algorithm.part1.week1;

import static org.junit.Assert.*;
import junit.framework.Assert;
import java.net.URL;
import java.net.URLClassLoader;
 
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class ISetTest {

    @Test
    public void test() {
        ApplicationContext context = new FileSystemXmlApplicationContext("src/main/java/week1-appContext.xml");
        ISet set = ((ISet) context.getBean("vedioSet"));
        set.init(10);
        set.union(4, 3);
        set.union(3, 8);
        set.union(6, 5);
        set.union(9, 4);
        set.union(2, 1);
        Assert.assertTrue(set.connected(8, 9));
        Assert.assertFalse(set.connected(0, 5));
        set.union(5, 0);
        set.union(7, 2);
        set.union(6, 1);

        Integer s1 = (Integer) set.find(0);
        Assert.assertEquals(s1,set.find(1));
        Assert.assertEquals(s1,set.find(2));
        Assert.assertEquals(s1,set.find(5));
        Assert.assertEquals(s1,set.find(6));
        Assert.assertEquals(s1,set.find(7));
        
        Integer s2 = (Integer) set.find(3);
        Assert.assertEquals(s2,set.find(4));
        Assert.assertEquals(s2,set.find(8));
        Assert.assertEquals(s2,set.find(9));   
     }

}
