package ru.devmark.collection;

import java.util.function.BiConsumer;
import java.util.function.Predicate;

public interface ReadOnlyMap<K, V> {

    int size();

    V get(K key);

    boolean isEmpty();

    boolean isNotEmpty();

    boolean containsKey(K key);

    boolean containsValue(V value);

    ReadOnlyMap<K, V> filterKeys(Predicate<? super K> predicate);

    ReadOnlyMap<K, V> filterValues(Predicate<? super V> predicate);

    void forEach(BiConsumer<? super K, ? super V> action);

    ReadOnlySet<K> keys();

    ReadOnlyList<V> values();

    MutableMap<K, V> toMutableMap();

    ReadOnlySet<Pair<K, V>> entrySet();
}
