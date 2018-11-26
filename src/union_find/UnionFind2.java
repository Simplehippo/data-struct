package union_find;

//树形结构
public class UnionFind2 implements UF{
    private int[] parent;

    public UnionFind2(int size) {
        parent = new int[size];

        for(int i=0; i<parent.length; i++) {
            parent[i] = i;
        }

    }


    @Override
    public void union(int p, int q) {
        if(p < 0 || p >= parent.length || q < 0 || q >= parent.length) {
            throw new IllegalArgumentException("p or q is out index!");
        }

        int pRoot = find(p);
        int qRoot = find(q);

        parent[pRoot] = qRoot;
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
