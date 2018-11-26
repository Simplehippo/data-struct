package union_find;

//路径压缩最小高度树
public class UnionFind6 implements UF{
    private int[] parent;
    private int[] rank;

    public UnionFind6(int size) {
        parent = new int[size];
        rank = new int[size];

        for(int i=0; i<parent.length; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

    }


    @Override
    public void union(int p, int q) {
        if(p < 0 || p >= parent.length || q < 0 || q >= parent.length) {
            throw new IllegalArgumentException("p or q is out index!");
        }

        int pRoot = find(p);
        int qRoot = find(q);

        if(rank[pRoot] < rank[qRoot]) {
            parent[pRoot] = qRoot;
        } else if(rank[pRoot] > rank[qRoot]) {
            parent[qRoot] = pRoot;
        } else {
            parent[qRoot] = pRoot;
            rank[pRoot] ++;
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    private int find(int p) {
        if(p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("p is out index!");
        }

        if(p != parent[p]) {
            parent[p] = find(parent[p]);
        }

        return parent[p];
    }

    @Override
    public int getSize() {
        return parent.length;
    }
}
