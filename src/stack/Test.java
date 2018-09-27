package stack;

public class Test {

    public static void main(String[] args) {
        _ArrayStack<Integer> stack = new _ArrayStack<>();

        for(int i = 0; i < 5; i ++) {
            stack.push(i);
            System.out.println(stack);
        }

        stack.pop();
        System.out.println(stack);
    }
}
