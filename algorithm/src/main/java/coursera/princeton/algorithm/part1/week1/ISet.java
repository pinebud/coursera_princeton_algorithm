package coursera.princeton.algorithm.part1.week1;

public interface ISet<T, S> {
	public void init(T[] input);
	public void union(T a, T b);
	public boolean connected(T a, T b);
	public S find(T a);
}
