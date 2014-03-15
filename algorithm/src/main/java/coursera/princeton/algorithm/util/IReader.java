/**
 * 
 */
package coursera.princeton.algorithm.util;

import java.io.File;

/**
 * @author jingning
 *
 */
public interface IReader<T> {
    public T[] load(File file);
}
