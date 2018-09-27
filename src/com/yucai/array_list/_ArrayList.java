package com.yucai.array_list;

import java.util.Arrays;

public class _ArrayList<E> {

    private E[] data;
    private int size;


    public _ArrayList() {
        this(10);
    }

    public _ArrayList(int capacity) {
        data = (E[])new Object[capacity];
        size = 0;
    }

    public _ArrayList(E[] arr) {
        data = Arrays.copyOf(arr, arr.length * 2);
        size = arr.length;
    }

    public _ArrayList(_ArrayList oldArr) {
        E[] oldData = (E[])oldArr.data;
        data = Arrays.copyOf(oldData, oldData.length);
        size = oldArr.size;
    }

    public int getCapacity() {
        return data.length;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(int index, E e) {

        if(size == data.length) {
            resize(2 * data.length);
        }

        if(index < 0 || index > size) {
            throw new IllegalArgumentException("add failed: index error!");
        }

        for(int i = size - 1; i >= index; i --) {
            data[i + 1] = data[i];
        }

        data[index] = e;
        size ++;
    }

    public void addLast(E e) {
        add(size, e);
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public E get(int index) {
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("get failed: index error!");
        }
        return data[index];
    }

    public E getLast() {
        return get(size - 1);
    }

    public E getFirst() {
        return get(0);
    }

    public void set(int index, E e) {
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("set failed: index error!");
        }
        data[index] = e;
    }

    public boolean contains(E e) {
        for(int i = 0; i < size; i ++) {
            if(data[i].equals(e)) {
                return true;
            }
        }
        return false;
    }

    public int find(E e) {
        for(int i = 0; i < size; i ++) {
            if(data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    public int[] findAll(E e) {
        int[] indexs = new int[size];
        int indexs_size = 0;
        for(int i = 0; i < size; i ++) {
            if(data[i].equals(e)) {
                indexs[indexs_size] = i;
                indexs_size ++;
            }
        }
        return Arrays.copyOf(indexs, indexs_size);
    }

    public E remove(int index) {
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("remove failed: index error!");
        }

        E ret = data[index];
        for(int i = index + 1; i < size; i ++) {
            data[i - 1] = data[i];
        }
        size --;

        if(size == data.length / 4 && data.length / 2 != 0) {
            resize(data.length / 2);
        }
        return ret;
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public E removeFirst() {
        return remove(0);
    }

    public boolean removeElement(E e) {
        int index = find(e);
        if(index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    public void removeAllElement(E e) {
        int[] indexs = findAll(e);
        for(int i = 0; i < indexs.length; i ++) {
            removeElement(e);
        }
    }

    private void resize(int newCapacity) {
        E[] newData = (E[])new Object[newCapacity];
        for(int i = 0; i < size; i ++) {
            newData[i] = data[i];
        }

        data = newData;
    }

    @Override
    public String toString() {
        StringBuilder stb = new StringBuilder();
        stb.append(String.format("_ArrayList: size is %d, capacity is %d", size, data.length));
        stb.append("\n\tdata: [");
        for(int i = 0; i < size; i ++) {
            stb.append(data[i]);
            if(i != size - 1) {
                stb.append(", ");
            }
        }
        stb.append("]");
        return stb.toString();
    }
}
