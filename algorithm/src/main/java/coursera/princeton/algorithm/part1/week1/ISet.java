package coursera.princeton.algorithm.part1.week1;

/*
 * API of union find, T is the 
 */
public interface ISet<T, S> {	
    public void init(int size);
    public void union(T a, T b);
    public boolean connected(T a, T b);
    public S find(T a);
}
