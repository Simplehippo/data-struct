package avl;

public interface Map<K, V> {

    void put(K key, V value);
    void remove(K key);
    boolean containsKey(K key);
    V get(K key);
    int getSize();
    boolean isEmpty();
}