package map;

public interface Map<K,V> {
    void put(K key, V value);
    void remove(K key);
    V get(K key);
    void set(K key, V value);
    int getSize();
    boolean isEmpty();
}