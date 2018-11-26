package avl;

import java.util.ArrayList;

public class AVLTree<K extends Comparable<K>, V> implements Map<K, V> {
    private TreeNode root;
    private int size;

    public AVLTree() {
        size = 0;
    }

    private TreeNode getNode(TreeNode node, K key) {
        if(node == null) {
            return null;
        }

        if(key.compareTo(node.key) < 0) {
            return getNode(node.left, key);
        } else if(key.compareTo(node.key) > 0) {
            return getNode(node.right, key);
        }

        return node;
    }

    private int getBalanceFactor(TreeNode node) {
        if(node == null) {
            return 0;
        }

        return getHeight(node.left) - getHeight(node.right);
    }

    private int getHeight(TreeNode node) {
        return node == null ? 0 : node.height;
    }

    public boolean isBST() {
        ArrayList<K> keys = new ArrayList<>();

        inOrder(root, keys);

        for(int i=1; i<keys.size(); i++) {
            if(keys.get(i-1).compareTo(keys.get(i)) > 0) {
                return false;
            }
        }

        return true;
    }

    private void inOrder(TreeNode node, ArrayList<K> keys) {
        if(node == null) {
            return;
        }

        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(TreeNode node) {
        if(node == null) {
            return true;
        }

        int balancedFactor = getBalanceFactor(node);
        if(Math.abs(balancedFactor) > 1) {
            return false;
        }

        return isBalanced(node.left) && isBalanced(node.right);
    }

    private TreeNode rightRotate(TreeNode y) {
        TreeNode x = y.left;
        TreeNode T3 = x.right;

        x.right = y;
        y.left = T3;

        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));

        return x;
    }

    private TreeNode leftRotate(TreeNode y) {
        TreeNode x = y.right;
        TreeNode T3 = x.left;

        x.left = y;
        y.right = T3;

        y.height = 1 + Math.max(getHeight(y.left), getHeight(y.right));
        x.height = 1 + Math.max(getHeight(x.left), getHeight(x.right));

        return x;
    }

    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private TreeNode put(TreeNode node, K key, V value) {
        if(node == null) {
            size++;
            return new TreeNode(key, value);
        }

        if(key.compareTo(node.key) < 0) {
            node.left = put(node.left, key, value);
        } else if(key.compareTo(node.key) > 0) {
            node.right = put(node.right, key, value);
        } else {//equals do override
            node.value = value;
        }

        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        int balanceFactor = getBalanceFactor(node);
        if(Math.abs(balanceFactor) > 1) {
//            System.out.println("not balanced: " + balanceFactor);
        }

        //LL
        if(balanceFactor > 1 && getBalanceFactor(node.left) >= 0) {
            return rightRotate(node);
        }

        //RR
        if(balanceFactor < -1 && getBalanceFactor(node.right) <= 0) {
            return leftRotate(node);
        }

        //LR
        if(balanceFactor > 1 && getBalanceFactor(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        //RL
        if(balanceFactor < -1 && getBalanceFactor(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

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


    @Override
    public void remove(K key) {
        root = remove(root, key);
    }

    private TreeNode remove(TreeNode node, K key) {
        if(node == null) {
            return null;
        }

        TreeNode retNode;
        if(key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
            retNode = node;
        } else if(key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
            retNode = node;
        } else {//delete
            if(node.left == null) {//no left
                TreeNode newNode = node.right;
                node.right = null; //let gc
                size--;
                retNode = newNode;
            } else if(node.right == null) {//no right
                TreeNode newNode = node.left;
                node.left = null;//let gc
                size--;
                retNode = newNode;
            } else {
                //have left and have right
                TreeNode successor = getMinNode(node.right);
                successor.right = remove(node.right, successor.key);
                successor.left = node.left;
                node.left = node.right = null; //let gc
                retNode = successor;
            }
        }

        if(retNode == null) {
            return null;
        }

        //更新高度
        retNode.height = 1 + Math.max(getHeight(retNode.left), getHeight(retNode.right));

        //平衡
        int balanceFactor = getBalanceFactor(retNode);

        //LL
        if(balanceFactor > 1 && getBalanceFactor(retNode.left) >= 0) {
            return rightRotate(retNode);
        }

        //RR
        if(balanceFactor < -1 && getBalanceFactor(retNode.right) <= 0) {
            return leftRotate(retNode);
        }

        //LR
        if(balanceFactor > 1 && getBalanceFactor(retNode.left) < 0) {
            retNode.left = leftRotate(retNode.left);
            return rightRotate(retNode);
        }

        //RL
        if(balanceFactor < -1 && getBalanceFactor(retNode.right) > 0) {
            retNode.right = rightRotate(retNode.right);
            return leftRotate(retNode);
        }

        return retNode;
    }

    @Override
    public boolean containsKey(K key) {
        TreeNode node = getNode(root, key);
        return node != null;
    }

    @Override
    public V get(K key) {
        TreeNode node = getNode(root, key);
        return node == null ? null : node.value;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    private class TreeNode{
        K key;
        V value;
        TreeNode left;
        TreeNode right;
        int height;

        public TreeNode(K key, V value, TreeNode left, TreeNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
            this.height = 1;
        }

        public TreeNode(K key, V value) {
            this(key, value, null, null);
        }

        public TreeNode() {
            this(null, null, null, null);
        }
    }
}
