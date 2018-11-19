package map;

public class LinkedListMap<K, V> implements Map<K, V> {
    private Node dummyHead;
    private int size;

    public LinkedListMap() {
        dummyHead = new Node();
        size = 0;
    }

    private Node getNode(K key) {
        Node pNode = dummyHead;
        while(pNode != null) {
            if(pNode.key.equals(key)) {
                return pNode;
            }
            pNode = pNode.next;
        }
        return null;
    }

    @Override
    public void put(K key, V value) {
        Node node = getNode(key);
        if(node == null) {
            dummyHead.next = new Node(key, value, dummyHead.next);
            size++;
        } else {
            node.value = value;
        }
    }

    @Override
    public void remove(K key) {
        Node preNode = dummyHead;
        while(preNode.next != null) {
            if(preNode.next.key.equals(key)) {//remove
                Node delNode = preNode.next;
                preNode.next = preNode.next.next;
                size--;
                delNode.next = null;
                return;
            }
            preNode = preNode.next;
        }
    }

    @Override
    public boolean containsKey(K key) {
        Node pNode = dummyHead;
        while(pNode != null) {
            if(pNode.key.equals(key)) {
                return true;
            }
            pNode = pNode.next;
        }
        return false;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
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


    private class Node{
        K key;
        V value;
        Node next;

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Node() {
        }
    }
}
