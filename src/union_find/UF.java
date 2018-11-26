package union_find;

public interface UF {

    void union(int p, int q);
    boolean isConnected(int p, int q);
    int getSize();
}
