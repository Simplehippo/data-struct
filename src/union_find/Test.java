package union_find;

import java.util.Random;

public class Test {
    public static void main(String[] args){
        int size = 1000;
        int m = 10000000;

//        UF uf1 = new UnionFind1(size);
//        isRight(uf1);
//        System.out.println("ut1 spend times: " + computeTime(uf1, m) + " s");

        UF uf2 = new UnionFind2(size);
        isRight(uf2);
        System.out.println("ut2 spend times: " + computeTime(uf2, m) + " s");

        UF uf3 = new UnionFind3(size);
        isRight(uf3);
        System.out.println("ut3 spend times: " + computeTime(uf3, m) + " s");

        UF uf4 = new UnionFind3(size);
        isRight(uf4);
        System.out.println("ut4 spend times: " + computeTime(uf4, m) + " s");

        UF uf5 = new UnionFind3(size);
        isRight(uf5);
        System.out.println("ut5 spend times: " + computeTime(uf5, m) + " s");

        UF uf6 = new UnionFind3(size);
        isRight(uf6);
        System.out.println("ut6 spend times: " + computeTime(uf6, m) + " s");

    }

    public static void isRight(UF uf) {
        uf.union(4, 6);
        uf.union(2, 4);
        uf.union(1, 8);
        uf.union(7, 8);
        uf.union(2, 8);


        if(uf.isConnected(8, 4) && uf.isConnected(7, 1) && !uf.isConnected(7, 5)) {
            //System.out.println("测试成功！");
        } else {
            System.out.println("测试失败！");
        }
    }

    public static double computeTime(UF uf, int m) {
        Random random = new Random();

        long start = System.nanoTime();
        for(int i=0; i<m; i++) {
            int p = random.nextInt(uf.getSize());
            int q = random.nextInt(uf.getSize());
            uf.union(p, q);
        }

        for(int i=0; i<m; i++) {
            int p = random.nextInt(uf.getSize());
            int q = random.nextInt(uf.getSize());
            uf.isConnected(p, q);
        }
        long end = System.nanoTime();

        return (end - start) / 1000000000.0;
    }
}
