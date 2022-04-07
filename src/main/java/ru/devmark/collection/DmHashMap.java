package ru.devmark.collection;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class DmHashMap<K, V> implements MutableMap<K, V> {

    private final Map<K, V> map;

    public DmHashMap(Map<K, V> map) {
        this.map = new HashMap<>(map);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public V get(K key) {
        return map.get(key);
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean isNotEmpty() {
        return !map.isEmpty();
    }

    @Override
    public boolean containsKey(K key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(V value) {
        return map.containsValue(value);
    }

    @Override
    public ReadOnlyList<V> filterValues(Predicate<? super V> predicate) {
        List<V> filteredValues = new ArrayList<>();
        map.values().forEach(e -> {
            if (predicate.test(e)) {
                filteredValues.add(e);
            }
        });
        return new DmArrayList<>(filteredValues);
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        map.forEach(action);
    }

    @Override
    public ReadOnlySet<K> keys() {
        return new DmHashSet<>(map.keySet());
    }

    @Override
    public ReadOnlyList<V> values() {
        return new DmArrayList<>(map.values());
    }

    @Override
    public ReadOnlySet<Pair<K, V>> entrySet() {
        var set = new HashSet<Pair<K, V>>();
        map.forEach((key, value) -> set.add(Pair.of(key, value)));
        return new DmHashSet<>(set);
    }

    @Override
    public MutableMap<K, V> toMutableMap() {
        return new DmHashMap<>(map);
    }

    @Override
    public V put(K key, V value) {
        return map.put(key, value);
    }

    @Override
    public V putIfAbsent(K key, V value) {
        return map.putIfAbsent(key, value);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public V remove(K key) {
        return map.remove(key);
    }

    @Override
    public ReadOnlyMap<K, V> toReadOnlyMap() {
        return new DmHashMap<>(map);
    }

    @Override
    public String toString() {
        return map.toString();
    }
}
