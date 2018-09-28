package queue;


public class _LoopQueue<E> implements _Queue<E> {

    private E[] data;
    private int front;
    private int tail;
    private int size;

    public _LoopQueue(int capacity) {
        data = (E[])new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public _LoopQueue() {
        this(10);
    }

    @Override
    public void enqueue(E e) {
        if((tail + 1) % data.length == front) {
            resize(getCapacity() * 2);
        }
        data[tail] = e;
        tail = (tail + 1) % data.length;
        size ++;
    }

    private void resize(int newCapacity) {
        E[] newData = (E[])new Object[newCapacity + 1];
        for(int i = 0; i < size; i ++) {
            newData[i] = data[(i+front) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public E dequeue() {
        if(isEmpty()) {
            throw new IllegalArgumentException("queue is empty!!");
        }
        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size --;
        if(size <= getCapacity() / 4 && getCapacity() / 2 != 0) {
            resize(getCapacity() / 2);
        }
        return ret;
    }

    @Override
    public E getFront() {
        if(isEmpty()) {
            throw new IllegalArgumentException("queue is empty!!");
        }
        return data[front];
    }

    @Override
    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length - 1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder stb = new StringBuilder();
        stb.append(String.format("_LoopQueue: size is %d, capacity is %d", size, getCapacity()));
        stb.append("\n");
        stb.append("front [");
        for(int i = front; i != tail; i = (i + 1) % data.length) {
            stb.append(data[i]);
            if((i+1) % data.length != tail) {
                stb.append(", ");
            }
        }
        stb.append("] tail");

        return stb.toString();
    }
}
