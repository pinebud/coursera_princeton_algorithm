package coursera.princeton.algorithm.part1.week1;

public class WeightedQuickUnionWithPathCompression extends WeightedQuickUnion {

	@Override
	public void union(Integer a, Integer b) {
		System.out.println("With Path Compression");
		Integer rootA = root(a);
		Integer rootB = root(b);
		if(rootA==rootB) return;
		if(treeSizeArray[rootA]<treeSizeArray[rootB]){ //let rootB as the new root
			Integer ele = a;
			while(ele!=rootA){//path compression
				Integer parent = set[ele];
				set[ele]=rootB;
				ele = parent;
			}
			treeSizeArray[rootB] = treeSizeArray[rootA]+treeSizeArray[rootB];
		}else{// let rootA as the new root
			Integer ele = b;
			while(ele!=rootB){//path compression
				Integer parent = set[ele];
				set[ele]=rootA;
				ele = parent;
			}
			treeSizeArray[rootA] = treeSizeArray[rootA]+treeSizeArray[rootB];
		}		
	}

}
