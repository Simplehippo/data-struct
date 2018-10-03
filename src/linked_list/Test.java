package linked_list;

public class Test {
    
    public static void main(String[] args){  
        _LinkedList<Integer> list = new _LinkedList<>();

        for(int i = 0; i < 5; i ++) {
            list.addFirst(i);
            System.out.println(list);
        }

        list.removeFirst();
        System.out.println(list);

        list.removeLast();
        System.out.println(list);

    }

}
