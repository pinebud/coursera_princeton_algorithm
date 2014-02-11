package coursera.princeton.algorithm.part1.week1;

public class UFQuickFind implements IUF<Integer, Integer> {
    Integer[] set = null;
    int size = 0;

    public void init(int size) {
    	System.out.println("QFind");
        this.size = size;
        set = new Integer[size];
        for (int i = 0; i < size; i++) {
            set[i] = i;
        } 
    }

    public void union(Integer a, Integer b) {
        Integer sa = set[a];
        Integer sb = set[b];
        if (sa != sb) {
            for (int i = 0; i < size; i++) {
                if (set[i] == sa) {
                    set[i] = sb;
                }
            }
        }
    }    

    public boolean connected(Integer a, Integer b) {
        return set[a] == set[b];
    }

    public Integer find(Integer a) {
        return set[a];
    }
}
