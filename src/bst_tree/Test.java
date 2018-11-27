package bst_tree;

public class Test {
    
    public static void main(String[] args){  
        _BinarySearchTree<Integer> bst = new _BinarySearchTree<>();
        int[] nums = {5, 3, 6, 8, 4, 2};
        for(int num : nums) {
            bst.add(num);
        }

//        bst.preOrder();
//        System.out.println();
//
//        bst.preOrderNR();
//        System.out.println();
//
//        bst.inOrder();
//        System.out.println();
//
//        bst.postOrder();
//        System.out.println();

//        bst.levelOrder();
        boolean r = bst.remove(5);
        System.out.println(r);
        bst.levelOrder();

    }
}
