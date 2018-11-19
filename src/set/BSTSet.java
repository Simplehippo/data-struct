package set;

public class BSTSet<E extends Comparable<E>> implements Set<E> {
    private TreeNode root;
    private int size;

    public BSTSet() {
        size = 0;
    }

    private TreeNode getNode(E e) {
        return getNode(root, e);
    }

    private TreeNode getNode(TreeNode node, E e) {
        if(node == null) {
            return null;
        }

        if(e.compareTo(node.e) < 0) {
            return getNode(node.left, e);
        } else if(e.compareTo(node.e) > 0) {
            return getNode(node.right, e);
        } else {
            return node;
        }
    }

    @Override
    public void add(E e) {
        root = add(root, e);
    }

    //尝试在以node为根的二分搜索树种添加e
    private TreeNode add(TreeNode node, E e) {
        if(node == null) { //add
            return new TreeNode(e);
        }

        if(e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if(e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }

        return node;
    }

    //得到以node为根的搜索树的最小结点
    private TreeNode getMinNode(TreeNode node) {
        if(node == null) {
            return null;
        }

        TreeNode pNode = node;
        while(pNode.left != null) {
            pNode = pNode.left;
        }

        return pNode;
    }

    //删除以node为根结点的搜索树的最小结点,返回删除了最小结点之后的新node
    private TreeNode removeMinNode(TreeNode node) {
        if(node == null) {
            return null;
        }

        if(node.left == null) {//remove
            TreeNode newNode = node.right;
            node.right = null;
            size--;
            return newNode;
        }

        node.left = removeMinNode(node.left);
        return node;
    }

    @Override
    public void remove(E e) {
        if(contains(e)) {
            root = remove(root, e);
        }
    }

    //在根节点为node的搜索树中删除e, 并返回删除了e之后的新node
    private TreeNode remove(TreeNode node, E e) {
        if(node == null) {
            return null;
        }

        if(e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
        } else if(e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
        } else {//remove
            if(node.left == null) {//no left
                TreeNode newNode = node.right;
                node.right = null; //let gc
                size--;
                return newNode;
            }

            if(node.right == null) {//no right
                TreeNode newNode = node.left;
                node.left = null;//let gc
                size--;
                return newNode;
            }

            //have left and have right
            TreeNode successor = getMinNode(node.right);
            successor.right = removeMinNode(node.right);
            successor.left = node.left;
            node.left = node.right = null; //let gc
            return successor;
        }

        return node;
    }

    @Override
    public boolean contains(E e) {
        TreeNode node = getNode(root, e);
        return node != null;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    private class TreeNode {
        E e;
        TreeNode left;
        TreeNode right;

        public TreeNode(E e, TreeNode left, TreeNode right) {
            this.e = e;
            this.left = left;
            this.right = right;
        }

        public TreeNode(E e) {
            this.e = e;
        }

        public TreeNode() {

        }
    }
}
