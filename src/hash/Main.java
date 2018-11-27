package hash;

public class Main {
    public static void main(String[] args){  
        HashTable<String, Integer> map = new HashTable<>();

        map.put("杨", 100);
        map.put("与", 1389);
        map.put("cai", -199);
        
        System.out.println(map.get("cai"));
    }
}
