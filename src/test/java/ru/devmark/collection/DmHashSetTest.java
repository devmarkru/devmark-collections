package ru.devmark.collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class DmHashSetTest {

    @Test
    void fromSourceSet() {
        Set<String> source = new HashSet<>();
        source.add("ab");
        source.add("cd");

        var strings = DmCollections.toReadOnlySet(source);
        source.clear();

        Assertions.assertEquals(0, source.size());
        Assertions.assertEquals(2, strings.size());
    }

    @Test
    void emptySet() {
        ReadOnlySet<String> set = DmCollections.emptySet();

        Assertions.assertTrue(set.isEmpty());
        Assertions.assertFalse(set.isNotEmpty());
        Assertions.assertEquals(0, set.size());
        Assertions.assertFalse(set.contains("s"));
    }

    @Test
    void set1() {
        ReadOnlySet<String> set = DmCollections.setOf("a");

        Assertions.assertFalse(set.isEmpty());
        Assertions.assertTrue(set.isNotEmpty());
        Assertions.assertEquals(1, set.size());
        Assertions.assertTrue(set.contains("a"));
    }

    @Test
    void set2() {
        ReadOnlySet<String> set = DmCollections.setOf("aa", "bb");

        Assertions.assertFalse(set.isEmpty());
        Assertions.assertTrue(set.isNotEmpty());
        Assertions.assertEquals(2, set.size());
        Assertions.assertTrue(set.contains("bb"));
    }

    @Test
    void set3() {
        ReadOnlySet<String> set = DmCollections.setOf("aaa", "bbb", "ccc");

        Assertions.assertFalse(set.isEmpty());
        Assertions.assertTrue(set.isNotEmpty());
        Assertions.assertEquals(3, set.size());
        Assertions.assertTrue(set.contains("bbb"));
    }

    @Test
    void filterSet() {
        ReadOnlySet<String> set = DmCollections.setOf("aaaa", "b", "ccc");

        var filteredSet = set.filter(i -> i.length() >= 3);

        Assertions.assertEquals(2, filteredSet.size());
    }

    @Test
    void mapSet() {
        ReadOnlySet<String> set = DmCollections.setOf("aaaa", "b", "ccc");

        var mappedList = set.map(String::length).toReadOnlySet();

        Assertions.assertTrue(mappedList.contains(4));
    }

    @Test
    void addElement() {
        MutableSet<String> set = DmCollections.setOf("aaaa", "b", "ccc").toMutableSet();
        Assertions.assertFalse(set.contains("ddd"));

        set.add("ddd");
        Assertions.assertTrue(set.contains("ddd"));
    }

    @Test
    void clearSet() {
        MutableSet<String> set = DmCollections.setOf("aaaa", "b", "ccc").toMutableSet();
        Assertions.assertEquals(3, set.size());

        set.clear();
        Assertions.assertEquals(0, set.size());
    }

    @Test
    void removeElement() {
        MutableSet<String> set = DmCollections.setOf("aaaa", "b", "ccc").toMutableSet();
        Assertions.assertTrue(set.contains("b"));

        set.remove("b");
        Assertions.assertFalse(set.contains("b"));
    }

    @Test
    void allMatch() {
        var strings = DmCollections.setOf("apple", "banana", "cherry");

        Assertions.assertTrue(strings.all(i -> i.length() >= 5));
        Assertions.assertFalse(strings.all(i -> i.startsWith("z")));
    }

    @Test
    void anyMatch() {
        var strings = DmCollections.setOf("apple", "banana", "cherry");

        Assertions.assertTrue(strings.any(i -> i.startsWith("b")));
        Assertions.assertFalse(strings.any(i -> i.startsWith("z")));
    }

    @Test
    void noneMatch() {
        var strings = DmCollections.setOf("apple", "banana", "cherry");

        Assertions.assertTrue(strings.none(i -> i.startsWith("z")));
        Assertions.assertFalse(strings.none(i -> i.startsWith("a")));
    }
}
