package com.yucai.stack;

import com.yucai.array_list._ArrayList;

public class _ArrayStack<E> implements _Stack<E> {

    _ArrayList<E> array;

    public _ArrayStack() {
        this.array = new _ArrayList<>();
    }

    public _ArrayStack(int capacity) {
        this.array = new _ArrayList<>(capacity);
    }

    public int getCapacity() {
        return array.getCapacity();
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
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public E peek() {
        return array.getLast();
    }

    @Override
    public String toString() {
        StringBuilder stb = new StringBuilder();
        stb.append("_Stack: ");
        stb.append("[");

        for(int i = 0; i < array.getSize(); i ++) {
            stb.append(array.get(i));
            if(i != array.getSize() - 1) {
                stb.append(", ");
            }
        }

        stb.append("] top");
        return stb.toString();
    }
}
