package stack;

import linked_list._LinkedList;

public class _LinkedListStack<E> implements _Stack<E> {

    private _LinkedList<E> data;
    private int size;

    public _LinkedListStack() {
        this.data = new _LinkedList<>();
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void push(E e) {
        data.addFirst(e);
    }

    @Override
    public E pop() {
        return data.removeFirst();
    }

    @Override
    public E peek() {
        return data.get(0);
    }
}
