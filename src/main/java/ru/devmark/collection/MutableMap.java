package ru.devmark.collection;

public interface MutableMap<K, V> extends ReadOnlyMap<K, V> {

    V put(K key, V value);

    void clear();

    V remove(K key);
}
