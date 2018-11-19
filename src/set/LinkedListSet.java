package set;

public class LinkedListSet<E> implements Set<E> {
    private Node dummyHead;
    private int size;

    public LinkedListSet() {
        dummyHead = new Node(null);
        size = 0;
    }

    @Override
    public void add(E e) {
        if(!contains(e)) {
            dummyHead.next = new Node(e, dummyHead.next);
            size++;
        }
    }

    @Override
    public void remove(E e) {
        Node preNode = dummyHead;
        while(preNode.next != null) {
            if(preNode.next.e.equals(e)) {
                Node delNode = preNode.next;
                preNode.next= preNode.next.next;
                delNode.next = null;
            }
            preNode = preNode.next;
        }
    }

    @Override
    public boolean contains(E e) {
        Node pNode = dummyHead;
        while(pNode != null) {
            if(pNode.e.equals(e)) {
                return true;
            }
            pNode = pNode.next;
        }

        return false;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }



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

}
