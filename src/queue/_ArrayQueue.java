package queue;

import array_list._ArrayList;

public class _ArrayQueue<E> implements _Queue<E> {

    private _ArrayList<E> array;

    public _ArrayQueue(int capacity) {
        array = new _ArrayList<>(capacity);
    }

    public _ArrayQueue() {
        array = new _ArrayList<>();
    }

    @Override
    public void enqueue(E e) {
        array.addLast(e);
    }

    @Override
    public E dequeue() {
        return array.removeFirst();
    }

    @Override
    public E getFront() {
        return array.getFirst();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }
    
    @Override
    public String toString() {
        StringBuilder stb = new StringBuilder();
        stb.append("_ArrayQueue: ");
        stb.append("front [");

        for(int i = 0; i < array.getSize(); i ++) {
            stb.append(array.get(i));
            if(i != array.getSize() - 1) {
                stb.append(", ");
            }
        }

        stb.append("] tail");
        return stb.toString();
    }
} 