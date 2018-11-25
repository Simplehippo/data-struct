package heap;

public class Test {
    public static void main(String[] args){
        Integer[] arr = {8, 1, 1, 4, 0, 19, 2};
        MinHeap<Integer> heap = new MinHeap<>(arr, (a,b) -> b-a);

        while(!heap.isEmpty()) {
            System.out.println(heap.pop());
        }
    }
}
