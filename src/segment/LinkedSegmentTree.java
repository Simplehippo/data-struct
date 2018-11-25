package segment;

public class LinkedSegmentTree<E> implements SegmentTree<E> {
    private Node root;
    private E[] data;
    private Merge<E> merger;

    public LinkedSegmentTree(E[] arr, Merge<E> merger) {
        if(arr == null || arr.length <= 0) {
            throw new IllegalArgumentException("arr can`t empty!");
        }
        this.merger = merger;

        data = (E[])new Object[arr.length];

        for(int i = 0; i < data.length; i++) {
            data[i] = arr[i];
        }

        root = buildTree(0, arr.length - 1);
    }

    //尝试在treeIndex创建一个表示[l, .., r]区间的线段树
    private Node buildTree(int l, int r) {
        //终止条件，递归到底
        if(l == r) {
            return new Node(data[l], l, r);
        }

        Node retNode = new Node(null, l, r);
        //递归创建左子树和右子树
        int mid = l + (r - l) / 2;
        retNode.leftChild = buildTree(l, mid);
        retNode.rightChild = buildTree(mid + 1, r);

        //融合视具体业务而定
        retNode.e = merger.merge(retNode.leftChild.e, retNode.rightChild.e);

        return retNode;
    }

    public E query(int queryL, int queryR) {
        if(queryL < 0 || queryL >= data.length || queryR < 0 || queryR >= data.length || queryL > queryR) {
            throw new IllegalArgumentException("query range is valid!");
        }
        return query(root, queryL, queryR);
    }

    //尝试在node区间内查找目标区间
    private E query(Node node, int queryL, int queryR) {
        //终止条件
        if(node.l == queryL && node.r == queryR) {
            return node.e;
        }

        //检查是否查询区间仅在一边
        int mid = node.l + (node.r - node.l) / 2;

        if(queryL >= mid + 1) {
            return query(node.rightChild, queryL, queryR);
        }

        if(queryR <= mid) {
            return query(node.leftChild, queryL, queryR);
        }


        //查询两边范围有交叉的
        E leftResult = query(node.leftChild, queryL, mid);
        E rightResult = query(node.rightChild, mid + 1, queryR);

        return merger.merge(leftResult, rightResult);
    }


    public void update(int index, E e) {
        if(index < 0 || index >= data.length) {
            throw new IllegalArgumentException("index is not valid!");
        }

        data[index] = e;

        updateTree(root, index);
    }

    private void updateTree(Node node, int updateIndex) {
        if(node.l == node.r) {
            node.e = data[node.l];
            return;
        }

        int mid = node.l + (node.r - node.l) / 2;

        if(updateIndex <= mid) {
            updateTree(node.leftChild, updateIndex);
        }

        if(updateIndex >= mid + 1) {
            updateTree(node.rightChild, updateIndex);
        }

        node.e = merger.merge(node.leftChild.e, node.rightChild.e);
    }

    class Node {
        E e;
        int l;
        int r;
        Node leftChild;
        Node rightChild;

        public Node(E e, int l, int r) {
            this.e = e;
            this.l = l;
            this.r = r;
        }

        public Node(E e, int l, int r, Node leftChild, Node rightChild) {
            this.e = e;
            this.l = l;
            this.r = r;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        public Node() {

        }
    }
}
