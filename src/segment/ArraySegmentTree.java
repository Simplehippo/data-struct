package segment;

public class ArraySegmentTree<E> implements SegmentTree<E> {

    private E[] tree;
    private E[] data;
    private Merge<E> merger;

    public ArraySegmentTree(E[] arr, Merge<E> merger) {
        if(arr == null || arr.length <= 0) {
            throw new IllegalArgumentException("arr can`t empty!");
        }
        this.merger = merger;

        data = (E[])new Object[arr.length];

        for(int i = 0; i < data.length; i++) {
            data[i] = arr[i];
        }

        tree = (E[]) new Object[4 * arr.length];

        buildTree(0, 0, arr.length - 1);
    }

    private int leftChild(int index) {
        return 2*index + 1;
    }

    private int rightChild(int index) {
        return 2*index + 2;
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    //尝试在treeIndex创建一个表示[l, .., r]区间的线段树
    private void buildTree(int treeIndex, int left, int right) {
        //终止条件，递归到底
        if(left == right) {
            tree[treeIndex] = data[left];
            return;
        }

        int leftChildIndex = leftChild(treeIndex);
        int rightChildIndex = rightChild(treeIndex);
        int mid = left + (right - left) / 2;

        buildTree(leftChildIndex, left, mid);
        buildTree(rightChildIndex, mid + 1, right);

        //融合视具体业务而定
        tree[treeIndex] = merger.merge(tree[leftChildIndex], tree[rightChildIndex]);
    }

    public E query(int queryL, int queryR) {
        if(queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length || queryL > queryR) {
            throw new IllegalArgumentException("query range is valid!");
        }
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    //尝试在区间内查找目标区间
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        //终止条件
        if(l == queryL && r == queryR) {
            return tree[treeIndex];
        }

        //检查是否查询区间仅在一边
        int leftChildIndex = leftChild(treeIndex);
        int rightChildIndex = rightChild(treeIndex);
        int mid = l + (r - l) / 2;

        if(queryL >= mid + 1) {
            return query(rightChildIndex, mid + 1, r, queryL, queryR);
        }

        if(queryR <= mid) {
            return query(leftChildIndex, l, mid, queryL, queryR);
        }


        //查询两边范围有交叉的
        E leftResult = query(leftChildIndex, l, mid, queryL, mid);
        E rightResult = query(rightChildIndex, mid + 1, r, mid + 1, queryR);

        return merger.merge(leftResult, rightResult);
    }


    public void update(int index, E e) {
        if(index < 0 || index >= data.length) {
            throw new IllegalArgumentException("index is not valid!");
        }

        data[index] = e;

        updateTree(0, 0, data.length - 1, index);
    }

    private void updateTree(int treeIndex, int l, int r, int updateIndex) {
        if(l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        int leftChildIndex = leftChild(treeIndex);
        int rightChildIndex = rightChild(treeIndex);
        int mid = l + (r - l) / 2;

        if(updateIndex <= mid) {
            updateTree(leftChildIndex, l, mid, updateIndex);
        }

        if(updateIndex >= mid + 1) {
            updateTree(rightChildIndex, mid + 1, r, updateIndex);
        }

        tree[treeIndex] = merger.merge(tree[leftChildIndex], tree[rightChildIndex]);
    }
}
