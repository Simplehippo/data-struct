package linked_list;

public class _LinkedList<E> {
    private Node dummyHead;
    private int size;

    private class Node {
        E e;
        Node next;

        public Node(E e) {
            this.e = e;
        }

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }
    }

    public _LinkedList() {
        dummyHead = new Node(null, null);
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    public void add(int index, E e) {
        if(index < 0 || index > size) {
            throw new IllegalArgumentException("add: index error!");
        }

        Node prev = dummyHead;
        for(int i = 0; i < index; i ++) {
            prev = prev.next;
        }
        prev.next = new Node(e, prev.next);

        size ++;
    }

    public E remove(int index) {
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("remove: index error!");
        }

        Node prev = dummyHead;
        for(int i = 0; i < index; i ++) {
            prev = prev.next;
        }

        Node retNode = prev.next;
        prev.next = retNode.next;
        retNode.next = null;

        size --;
        return retNode.e;
    }


    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public E get(int index) {
        if(index < 0 || index > size) {
            throw new IllegalArgumentException("get: index error!");
        }

        Node cur = dummyHead.next;
        for(int i = 0; i < index; i ++) {
            cur = cur.next;
        }
        return cur.e;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public void set(int index, E e) {
        if(index < 0 || index > size) {
            throw new IllegalArgumentException("set: index error!");
        }

        Node cur = dummyHead.next;
        for(int i = 0; i < index; i ++) {
            cur = cur.next;
        }
        cur.e = e;
    }

    public boolean contains(E e) {
        Node cur = dummyHead.next;
        while(cur != null) {
            if(cur.e.equals(e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();

        res.append("null=>");
        for(Node cur = dummyHead.next; cur != null; cur = cur.next) {
            res.append(cur.e);
            if(cur.next != null) {
                res.append("=>");
            }
        }

        return res.toString();
    }
}
