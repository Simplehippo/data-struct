package tree;

public class BinarySearchTree<E extends Comparable<E>> {
    private Node root;
    private int size;

    public BinarySearchTree(Node root) {
        this.root = root;
    }

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    public void add(E e) {
        root = add(root, e);
    }

    private Node add(Node node, E e) {
        if(node == null) {
            size++;
            return new Node(e);
        }

        if(e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if(e.compareTo(node.e) > 0) {
            node.right = add(node.right, e);
        }

        return node;
    }

    private class Node {
        E e;
        Node left;
        Node right;

        public Node(E e, Node left, Node right) {
            this.e = e;
            this.left = left;
            this.right = right;
        }

        public Node(E e) {
            this.e = e;
        }
    }
}