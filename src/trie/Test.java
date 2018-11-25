package trie;

public class Test {
    public static void main(String[] args){  
        Trie trie = new Trie();
        trie.put("apple", 3);
        System.out.println(trie.sumPrefix("ap"));
        trie.put("app", 2);
        System.out.println(trie.sumPrefix("ap"));
        System.out.println(trie.containsPrefix(".p.le"));
    }
    
}
