package segment;

public interface SegmentTree<E> {

    E query(int queryL, int queryR);

    void update(int index, E e);

}
