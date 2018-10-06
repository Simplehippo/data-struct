package queue;


import linked_list._DoubleLinkedList;

public class _LinkedListQueue<E> implements _Queue<E> {
    private _DoubleLinkedList<E> data;

    public _LinkedListQueue() {
        this.data = new _DoubleLinkedList<>();
    }

    @Override
    public void enqueue(E e) {
        data.addLast(e);
    }

    @Override
    public E dequeue() {
        return data.removeFirst();
    }

    @Override
    public E getFront() {
        return data.get(0);
    }

    @Override
    public int getSize() {
        return data.getSize();
    }

    @Override
    public boolean isEmpty() {
        return data.getSize() == 0;
    }
}
