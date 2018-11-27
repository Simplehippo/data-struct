package bst_tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class _BinarySearchTree<E extends Comparable<E>> {
    private Node root;
    private int size;

    public _BinarySearchTree(Node root) {
        this.root = root;
    }

    public _BinarySearchTree() {
        root = null;
        size = 0;
    }

    public int getSize() {
        return size;
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

    public boolean contains(E e) {
        return contains(root, e);
    }

    private boolean contains(Node node, E e) {
        if(node == null) {
            return false;
        }

        if(e.compareTo(node.e) == 0) {
            return true;
        } else if(e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }
    }

    public void preOrder() {
        preOrder(root);
    }

    private void preOrder(Node node) {
        if(node == null) {
            return;
        }

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    public void preOrderNR() {
        Stack<Node> stack = new Stack<>();
        if(root != null) {
            stack.push(root);
        }

        while(!stack.isEmpty()) {
            Node cur = stack.pop();

            System.out.println(cur.e);
            if(cur.right != null) {
                stack.push(cur.right);
            }
            if(cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    public void inOrder() {
        inOrder(root);
    }

    private void inOrder(Node node) {
        if(node == null) {
            return;
        }

        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }

    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node node) {
        if(node == null) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.e);
    }

    public void levelOrder() {
        Deque<Node> queue = new ArrayDeque<>();
        if(root != null) {
            queue.add(root);
        }

        while(!queue.isEmpty()) {
            Node cur = queue.pollFirst();
            System.out.println(cur.e);
            if(cur.left != null) {
                queue.addLast(cur.left);
            }
            if(cur.right != null) {
                queue.addLast(cur.right);
            }
        }

    }

    public Node getMin(Node node) {
        Node min = node;
        while (min.left != null && min != null) {
            min = min.left;
        }
        return min;
    }

    public Node getMax(Node node) {
        Node max = node;
        while (max.right != null && max != null) {
            max = max.right;
        }
        return max;
    }

    public E minnum() {
        if(size == 0) {
            throw new IllegalArgumentException("size < 0");
        }

        Node ret = getMin(root);
        return ret.e;
    }

    public E maxnum() {
        if(size == 0) {
            throw new IllegalArgumentException("size < 0");
        }

        Node ret = getMax(root);
        return ret.e;
    }

    public E removeMin() {
        E ret = minnum();
        root = removeMin(root);
        return ret;
    }

    private Node removeMin(Node node) {
        if(node == null) {
            return null;
        }

        if(node.left == null) {
            Node newNode = node.right;
            node.right = null;
            size--;
            return newNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    public E removeMax() {
        E ret = maxnum();
        root = removeMax(root);
        return ret;
    }

    private Node removeMax(Node node) {
        if(node == null) {
            return null;
        }

        if(node.right == null) {
            Node newNode = node.left;
            node.left = null;
            size--;
            return newNode;
        }

        node.right = removeMin(node.right);
        return node;
    }

    public boolean remove(E e) {
        if(e == null) {
            return false;
        }
        int oldSize = size;
        root = remove(root, e);
        return oldSize != size;
    }

    private Node remove(Node node, E e) {
        if(node == null) {
            return null;
        }

        if(e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if(e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else if(e.compareTo(node.e) == 0){//e.compareTo(node.e) == 0
            if(node.left == null) {
                Node retNode = node.right;
                node.right = null;
                size--;
                return retNode;
            }

            if(node.right == null) {
                Node retNode = node.left;
                node.left = null;
                size--;
                return retNode;
            }

            Node preNode = getMax(node.left);
            Node newLeftNode = removeMax(node.left);
            preNode.left = newLeftNode;
            preNode.right = node.right;
            node.left = null;
            node.right = null;

            return preNode;
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
