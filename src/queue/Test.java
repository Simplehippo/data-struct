package queue;

import java.util.Random;

public class Test {

    public static void main(String[] args){
//        test_ArrayQueue();
//        test_LoopQueue();
        test_time();
    }

    public static void test_time() {
        _ArrayQueue<Integer> arrayQueue = new _ArrayQueue<>();
        _LoopQueue<Integer> loopQueue = new _LoopQueue<>();
        _LinkedListQueue<Integer> linkedListQueue = new _LinkedListQueue<>();

        int count = 100000;

        double time1 = computeTime(arrayQueue, count);
        System.out.println("_ArrayQueue: " + time1);

        double time2 = computeTime(loopQueue, count);
        System.out.println("_LoopQueue: " + time2);

        double time3 = computeTime(linkedListQueue, count);
        System.out.println("linkedListQueue: " + time3);
    }

    public static double computeTime(_Queue<Integer> q, int count) {
        Random random = new Random();

        long start = System.nanoTime();

        for(int i = 0; i < count; i ++) {
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        }

        for(int i = 0; i < count; i ++) {
            q.dequeue();
        }

        long end = System.nanoTime();

        return (end - start) / 1000000000.0;
    }

    public static void test_ArrayQueue() {
        _ArrayQueue<Integer> queue = new _ArrayQueue<>();

        for(int i = 0; i < 5; i ++) {
            queue.enqueue(i);
            System.out.println(queue);
        }

        queue.dequeue();
        System.out.println(queue);
    }

    public static void test_LoopQueue() {
        _LoopQueue<Integer> queue = new _LoopQueue<>();

        for(int i = 0; i < 3; i ++) {
            queue.enqueue(i);
            System.out.println(queue);
        }

        queue.dequeue();
        System.out.println(queue);
    }

}
