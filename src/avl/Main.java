package avl;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words)) {
            System.out.println("Total words: " + words.size());

            // Collections.sort(words);

            // Test BSTMap
            long startTime = System.nanoTime();

            BSTMap<String, Integer> BSTMap = new BSTMap<>();
            for (String word : words) {
                if (BSTMap.containsKey(word))
                    BSTMap.put(word, BSTMap.get(word) + 1);
                else
                    BSTMap.put(word, 1);
            }

            for(String word: words)
                BSTMap.containsKey(word);

            long endTime = System.nanoTime();

            double time = (endTime - startTime) / 1000000000.0;
            System.out.println("BSTMap: " + time + " s");


            // Test AVL
            startTime = System.nanoTime();

            AVLTree<String, Integer> avl = new AVLTree<>();
            for (String word : words) {
                if (avl.containsKey(word))
                    avl.put(word, avl.get(word) + 1);
                else
                    avl.put(word, 1);
            }

            for(String word: words)
                avl.containsKey(word);

            endTime = System.nanoTime();

            time = (endTime - startTime) / 1000000000.0;
            System.out.println("AVL: " + time + " s");

            for(String word : words) {
                avl.remove(word);
                if(!avl.isBST() || !avl.isBalanced()) {
                    throw new IllegalArgumentException("avl is not balance!");
                }
            }
            System.out.println("avl is balanced!");
        }

        System.out.println();
    }
}