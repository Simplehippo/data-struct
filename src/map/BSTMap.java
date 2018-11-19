package map;

public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {
    private TreeNode root;
    private int size;

    public BSTMap() {
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

    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private TreeNode put(TreeNode node, K key, V value) {
        if(root == null) {
            size++;
            return new TreeNode(key, value);
        }

        if(key.compareTo(node.key) < 0) {
            node.left = put(node.left, key, value);
        } else if(key.compareTo(node.key) > 0) {
            node.right = put(node.right, key, value);
        } else {//equals do override
            node.value = value;
            return node;
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


    //return removed minNode -> node
    private TreeNode removeMinNode(TreeNode node) {
        if(node == null) {
            return null;
        }

        if(node.left == null) {
            TreeNode newNode = node.right;
            node.right = null;
            size--;
            return newNode;
        }

        node.left = removeMinNode(node.left);
        return node;
    }


    @Override
    public void remove(K key) {
        root = remove(root, key);
    }

    private TreeNode remove(TreeNode node, K key) {
        if(node == null) {
            return null;
        }

        if(key.compareTo(node.key) < 0) {
            node.left = remove(node.left, key);
        } else if(key.compareTo(node.key) > 0) {
            node.right = remove(node.right, key);
        } else {//delete
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

        public TreeNode(K key, V value, TreeNode left, TreeNode right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public TreeNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public TreeNode() {
        }
    }
}
