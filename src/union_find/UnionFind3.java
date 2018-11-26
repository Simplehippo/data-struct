package union_find;

//基于size优化
public class UnionFind3 implements UF{
    private int[] parent;
    private int[] sz;

    public UnionFind3(int size) {
        parent = new int[size];
        sz = new int[size];

        for(int i=0; i<parent.length; i++) {
            parent[i] = i;
            sz[i] = 1;
        }

    }


    @Override
    public void union(int p, int q) {
        if(p < 0 || p >= parent.length || q < 0 || q >= parent.length) {
            throw new IllegalArgumentException("p or q is out index!");
        }

        int pRoot = find(p);
        int qRoot = find(q);

        if(sz[pRoot] < sz[qRoot]) {
            parent[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } else {
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
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

        while(p != parent[p]) {
            p = parent[p];
        }

        return parent[p];
    }

    @Override
    public int getSize() {
        return parent.length;
    }
}
