package linked_list;

import java.util.LinkedList;

public class _DoubleLinkedList<E> {
    private Node dummyHead;
    private Node tail;
    private int size;

    public _DoubleLinkedList() {
        dummyHead = new Node(null, null, null);
        tail = dummyHead;
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(int index, E e) {
        if(index < 0 || index > size) {
            throw new IllegalArgumentException("index out!!");
        }

        Node prev;
        if(index > size>>1) {
            prev = tail;
            for(int i=size; i>index; i--) {
                prev = prev.prev;
            }

        } else {
            prev = dummyHead;
            for(int i=0; i<index; i++) {
                prev = prev.next;
            }
        }

        Node next = prev.next;
        Node newNode = new Node(e, prev, next);
        prev.next = newNode;
        if(next != null) {
            next.prev = newNode;
        }
        if(index == size) {
            tail = newNode;
        }
        size++;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    public E remove(int index) {
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("index out!!");
        }

        Node prev;
        if(index > size>>1) {
            prev = tail;
            for(int i=size; i>index; i--) {
                prev = prev.prev;
            }

        } else {
            prev = dummyHead;
            for(int i=0; i<index; i++) {
                prev = prev.next;
            }
        }

        Node removeNode = prev.next;
        Node next = removeNode.next;
        prev.next = removeNode.next;
        if(next != null) {
            next.prev = prev;
        }
        if(index == size - 1) {
            tail = prev;
        }
        size--;
        return removeNode.e;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public void set(int index, E e) {
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("set: index out!");
        }

        Node cur = dummyHead.next;
        for(int i=0; i<index; i++) {
            cur = cur.next;
        }

        cur.e = e;
    }

    public E get(int index) {
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("get: index out!");
        }

        Node cur = dummyHead.next;
        for(int i=0; i<index; i++) {
            cur = cur.next;
        }

        return cur.e;
    }

    public boolean contains(E e) {
        Node cur = dummyHead.next;
        for(int i=0; i<size; i++) {
            if(e==null) {
                if(cur.e == null)
                    return true;
            } else if(e.equals(cur.e)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    public int find(E e) {
        Node cur = dummyHead.next;
        for(int i=0; i<size; i++) {
            if(e==null) {
                if(cur.e == null)
                    return i;
            } else if(e.equals(cur.e)) {
                return i;
            }
            cur = cur.next;
        }
        return -1;
    }


    private class Node {
        E e;
        Node prev;
        Node next;

        public Node(E e, Node prev, Node next) {
            this.e = e;
            this.prev = prev;
            this.next = next;
        }

        public Node(E e) {
            this.e = e;
        }
    }
}
