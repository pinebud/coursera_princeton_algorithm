package coursera.princeton.algorithm.part1.week1;

public class WeightedQuickUnion implements IUF<Integer, Integer> {

	Integer[] set = null;
    int size = 0;
    Integer[] treeSizeArray = null;
    
    /**
     * N
     */
	@Override
	public void init(int size) {
		whoAmI();
		this.size = size;
        set = new Integer[size];
        treeSizeArray = new Integer[size];
        for (int i = 0; i < size; i++) {
            set[i] = i;
            treeSizeArray[i] = 1;
        }	
	}
	
	protected void whoAmI(){
		System.out.println("WQUnion");
	}

	/**
	 * 2logN
	 */
	@Override
	public void union(Integer a, Integer b) {
		Integer rootA = root(a);
		Integer rootB = root(b);
		if(rootA==rootB) return;
		if(treeSizeArray[rootA]<treeSizeArray[rootB]){
			set[rootA] = rootB;
			treeSizeArray[rootB] = treeSizeArray[rootA]+treeSizeArray[rootB];
		}else{
			set[rootB] = rootA;
			treeSizeArray[rootA] = treeSizeArray[rootA]+treeSizeArray[rootB];
		}
	}

	/**
	 * 2logN
	 */
	@Override
	public boolean connected(Integer a, Integer b) {
		return root(a)==root(b);
	}

	/**
	 * logN
	 */
	@Override
	public Integer find(Integer a) {
		return root(a);
	}
	
	/**
	 * logN
	 * @param a
	 * @return
	 */
	protected Integer root(Integer a){
		Integer r = set[a];
		while(r!=set[r]){
			r = set[r];
		}
		return r;
	}
}
