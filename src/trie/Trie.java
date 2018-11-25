package trie;

import java.util.HashMap;
import java.util.Map;

public class Trie {
    private Node root;
    private int size;

    public Trie() {
        root = new Node();
        size = 0;
    }

    private class Node {
        boolean isWord;
        int value;
        Map<Character, Node> next;

        public Node(int value) {
            this.value = value;
            next = new HashMap<>();
        }

        public Node() {
            this(0);
        }
    }

    public void put(String word) {
        put(word, 0);
    }

    public void put(String word, int value) {
        Node cur = root;

        for(int i=0; i<word.length(); i++) {
            char c = word.charAt(i);
            if(cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }

        if(!cur.isWord) {
            cur.isWord = true;
            size++;
        }

        cur.value = value;
    }

    //a-z  .代表一个任意英文字母
    public boolean containsWord(String regex) {

        return containsWord(root, regex, 0);
    }

    private boolean containsWord(Node node, String regex, int index) {
        if(index == regex.length()) {
            return node.isWord;
        }

        char c = regex.charAt(index);
        if(c != '.') {
            if(node.next.get(c) == null) {
                return false;
            }
            return containsWord(node.next.get(c), regex, index + 1);
        } else {
            for(char subKey : node.next.keySet()) {
                if(containsWord(node.next.get(subKey), regex, index + 1)) {
                    return true;
                }
            }

            return false;
        }
    }

    public boolean containsPrefix(String prefixRegex) {

        return containsPrefix(root, prefixRegex, 0);
    }

    public boolean containsPrefix(Node node, String prefixRegex, int index) {
        if(index == prefixRegex.length()) {
            return true;
        }

        char c = prefixRegex.charAt(index);
        if(c != '.') {
            if(node.next.get(c) == null) {
                return false;
            }
            return containsPrefix(node.next.get(c), prefixRegex, index + 1);
        } else {
            for(char subKey : node.next.keySet()) {
                if(containsPrefix(node.next.get(subKey), prefixRegex, index + 1)) {
                    return true;
                }
            }
            return false;
        }
    }

    private Node getNode(String word) {
        Node cur = root;

        for(int i=0; i<word.length(); i++) {
            char c = word.charAt(i);
            if(cur.next.get(c) == null) {
                return null;
            }
            cur = cur.next.get(c);
        }

        return cur;
    }

    public int getValue(String word) {
        Node node = getNode(word);
        return node == null ? -1 : node.value;
    }

    public int sumPrefix(String prefix) {
        Node node = getNode(prefix);
        return node == null ? 0 : sumPrefix(node);
    }

    private int sumPrefix(Node node) {
        int sum = node.value;
        for(char c : node.next.keySet()) {
            sum += sumPrefix(node.next.get(c));
        }
        return sum;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
