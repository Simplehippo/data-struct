package hash;

import java.util.TreeMap;

public class HashTable<K, V> {
    private static final int[] capacity
            = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
            49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469,
            12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};
    private static final int upperTol = 10;
    private static final int lowerTol = 2;


    private TreeMap<K, V>[] buckets;
    private int M;
    private int capacityIndex;
    private int size;

    public HashTable() {
        this.M = capacity[0];
        this.size = 0;
        this.capacityIndex = 0;
        this.buckets = new TreeMap[M];

        for(int i=0; i<M; i++) {
            buckets[i] = new TreeMap<>();
        }
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public void put(K key, V value) {
        TreeMap<K, V> map = buckets[hash(key)];
        if(map.containsKey(key)) {
            map.put(key, value);
        } else {
            map.put(key, value);
            size++;
            if(size >= upperTol * M && capacityIndex + 1 < capacity.length) {
                capacityIndex++;
                resize(capacity[capacityIndex]);
            }
        }
    }

    public V remove(K key) {
        TreeMap<K, V> map = buckets[hash(key)];
        V ret = null;
        if(map.containsKey(key)) {
            ret = map.remove(key);
            size--;
            if(size < lowerTol * M && capacityIndex - 1 >= 0) {
                capacityIndex --;
                resize(capacity[capacityIndex]);
            }
        }
        return ret;
    }

    private void resize(int newM) {
        TreeMap<K, V>[] newBuckets = new TreeMap[newM];
        for(int i=0; i<newM; i++) {
            newBuckets[i] = new TreeMap<>();
        }

        int oldM = M;
        this.M = newM;
        for(int i=0; i<oldM; i++) {
            TreeMap<K, V> map = buckets[i];
            for(K key : map.keySet()) {
                newBuckets[hash(key)].put(key, map.get(key));
            }
        }

        this.buckets = newBuckets;
    }

    public V get(K key) {
        TreeMap<K, V> map = buckets[hash(key)];
        if(map.containsKey(key)) {
            return map.get(key);
        }
        return null;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
