package coursera.princeton.algorithm.part1.week1;

public class UFQuickUnion implements IUF<Integer, Integer> {
    Integer[] set = null;
    int size = 0;

    public void init(int size) {
        System.out.println("QuickUnion");
        this.size = size;
        set = new Integer[size];
        for (int i = 0; i < size; i++) {
            set[i] = i;
        }
    }

    // O(tree height)
    public void union(Integer a, Integer b) {
        Integer setA = find(a);
        Integer setB = find(b);
        set[setB] = setA;
    }

    // O(tree height)
    public boolean connected(Integer a, Integer b) {
        return root(a) == root(b);
    }
     
    // O(tree height)
    public Integer find(Integer a) {
        return root(a);
    }

    private Integer root(Integer a) {
        while (set[a] != a) {
            a = set[a];
        }
        return a;
    }
}

