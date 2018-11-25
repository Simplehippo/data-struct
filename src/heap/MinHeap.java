package heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MinHeap<E extends Comparable<E>> {

    private List<E> data;
    private Comparator<E> comparator;

    public MinHeap() {
        this.data = new ArrayList<>();
    }

    public MinHeap(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public MinHeap(E[] arr) { //heapify
        heapify(arr);
    }

    public MinHeap(E[] arr, Comparator<E> comparator) { //heapify
        this.comparator = comparator;
        heapify(arr);
    }

    public int getSize() {
        return data.size();
    }

    public boolean isEmpty() {
        return data.size() == 0;
    }

    private int leftChild(int index) {
        return index*2 + 1;
    }

    private int rightChild(int index) {
        return index*2 + 2;
    }

    private int parent(int index) {
        if(index == 0) {
            throw new IllegalArgumentException("heap is empty! no parent!");
        }
        return (index - 1) / 2;
    }

    private void swap(int index1, int index2) {
        E temp = data.get(index1);
        data.set(index1, data.get(index2));
        data.set(index2, temp);
    }

    private void siftUp(int index) {
        int i = index;
        while(i > 0) {
            E cur = data.get(i);
            int parentIndex = parent(i);
            E parent = data.get(parentIndex);
            if(comparator != null) {
                if(comparator.compare(cur, parent) >= 0) {
                    break;
                }
            } else {
                if(cur.compareTo(parent) >= 0 ) { // because of min heap
                    break;
                }
            }

            swap(i, parentIndex);
            i = parentIndex;
        }
    }

    private void siftDown(int index) {
        int i = index;
        while(leftChild(i) < data.size()) {
            int minIndex = leftChild(i);
            if(rightChild(i) < data.size()){
                if(comparator != null) {
                    if(comparator.compare(data.get(rightChild(i)), data.get(leftChild(i))) < 0) {
                        minIndex = rightChild(i);
                    }
                } else {
                    if(data.get(rightChild(i)).compareTo(data.get(leftChild(i))) < 0) {
                        minIndex = rightChild(i);
                    }
                }
            }
            if(comparator != null) {
                if(comparator.compare(data.get(i), data.get(minIndex)) <= 0) {
                    break;
                }
            } else{
                if(data.get(i).compareTo(data.get(minIndex)) <= 0) {
                    break;
                }
            }

            swap(i, minIndex);
            i = minIndex;
        }
    }

    public void push(E e) {
        data.add(e);
        siftUp(data.size() - 1);
    }

    public E peek() {
        if(data.size() == 0) {
            throw new IllegalArgumentException("heap is empty!");
        }
        return data.get(0);
    }

    public E pop() {
        E ret = data.get(0);
        swap(data.size() - 1, 0);
        data.remove(data.size() -1);
        siftDown(0);
        return ret;
    }

    public E replace(E e) {
        E ret = data.get(0);
        data.set(0, e);
        siftDown(0);
        return ret;
    }

    public void heapify(E[] arr) {
        data = new ArrayList<>(Arrays.asList(arr));
        for(int i = parent(data.size() - 1); i >= 0; i--) {
            siftDown(i);
        }
    }
}
