package segment;

public class Test {

    public static void main(String[] args){
        Integer[] arr = {1, 2, -1, 0, 3, 9, -2};
        SegmentTree<Integer> segmentTree = new LinkedSegmentTree<>(arr, (a, b)->a>b ? a:b);
        System.out.println(segmentTree.query(0, 4));
        segmentTree.update(3, 18);
        System.out.println(segmentTree.query(0, 4));

    }
}
