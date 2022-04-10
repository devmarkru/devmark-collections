package ru.devmark.collection;

import java.util.*;

public class DmCollections {

    public static <T> ReadOnlyList<T> toReadOnlyList(Collection<T> source) {
        return new DmArrayList<>(source);
    }

    public static <T> ReadOnlyList<T> emptyList() {
        return new DmArrayList<>(Collections.emptyList());
    }

    public static <T> ReadOnlyList<T> listOf(T element1) {
        return new DmArrayList<>(Collections.singletonList(element1));
    }

    public static <T> ReadOnlyList<T> listOf(T element1, T element2) {
        List<T> l = new ArrayList<>(2);
        l.add(element1);
        l.add(element2);
        return new DmArrayList<>(l);
    }

    public static <T> ReadOnlyList<T> listOf(T element1, T element2, T element3) {
        List<T> l = new ArrayList<>(3);
        l.add(element1);
        l.add(element2);
        l.add(element3);
        return new DmArrayList<>(l);
    }

    public static <T> MutableList<T> mutableListOf() {
        return new DmArrayList<>(new ArrayList<>());
    }

    public static <T> ReadOnlySet<T> toReadOnlySet(Collection<T> source) {
        return new DmHashSet<>(source);
    }

    public static <T> ReadOnlySet<T> emptySet() {
        return new DmHashSet<>(Collections.emptySet());
    }

    public static <T> ReadOnlySet<T> setOf(T element1) {
        return new DmHashSet<>(Collections.singleton(element1));
    }

    public static <T> ReadOnlySet<T> setOf(T element1, T element2) {
        Set<T> set = new HashSet<>();
        set.add(element1);
        set.add(element2);
        return new DmHashSet<>(set);
    }

    public static <T> ReadOnlySet<T> setOf(T element1, T element2, T element3) {
        Set<T> set = new HashSet<>();
        set.add(element1);
        set.add(element2);
        set.add(element3);
        return new DmHashSet<>(set);
    }

    public static <T> MutableSet<T> mutableSetOf() {
        return new DmHashSet<>(new HashSet<>());
    }

    public static <K, V> ReadOnlyMap<K, V> toReadOnlyMap(Map<K, V> source) {
        return new DmHashMap<>(source);
    }

    public static <K, V> ReadOnlyMap<K, V> emptyMap() {
        return new DmHashMap<>(Collections.emptyMap());
    }

    public static <K, V> ReadOnlyMap<K, V> mapOf(Pair<K, V> element1) {
        Map<K, V> map = new HashMap<>();
        map.put(element1.key(), element1.value());
        return new DmHashMap<>(map);
    }

    public static <K, V> ReadOnlyMap<K, V> mapOf(Pair<K, V> element1, Pair<K, V> element2) {
        Map<K, V> map = new HashMap<>();
        map.put(element1.key(), element1.value());
        map.put(element2.key(), element2.value());
        return new DmHashMap<>(map);
    }

    public static <K, V> ReadOnlyMap<K, V> mapOf(Pair<K, V> element1, Pair<K, V> element2, Pair<K, V> element3) {
        Map<K, V> map = new HashMap<>();
        map.put(element1.key(), element1.value());
        map.put(element2.key(), element2.value());
        map.put(element3.key(), element3.value());
        return new DmHashMap<>(map);
    }

    public static <K, V> MutableMap<K, V> mutableMapOf() {
        return new DmHashMap<>(new HashMap<>());
    }
}
