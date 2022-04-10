package ru.devmark.collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class DmArrayListTest {

    @Test
    void fromSourceList() {
        List<String> source = new ArrayList<>();
        source.add("ab");
        source.add("ab");
        var strings = DmCollections.toReadOnlyList(source).toMutableList();
        source.clear();

        Assertions.assertEquals(0, source.size());
        Assertions.assertEquals(2, strings.size());
    }

    @Test
    void emptyList() {
        var strings = DmCollections.emptyList();

        Assertions.assertTrue(strings.isEmpty());
        Assertions.assertFalse(strings.isNotEmpty());
        Assertions.assertEquals(0, strings.size());

        Assertions.assertFalse(strings.contains("s"));
        Assertions.assertEquals(-1, strings.indexOf("s"));

        Assertions.assertFalse(strings.first().isPresent());
        Assertions.assertFalse(strings.last().isPresent());
    }

    @Test
    void list1() {
        var strings = DmCollections.listOf("a");

        Assertions.assertFalse(strings.isEmpty());
        Assertions.assertTrue(strings.isNotEmpty());
        Assertions.assertEquals(1, strings.size());

        Assertions.assertEquals(0, strings.indexOf("a"));
        Assertions.assertTrue(strings.contains("a"));

        Assertions.assertEquals("a", strings.first().get());
        Assertions.assertEquals("a", strings.last().get());

        Assertions.assertEquals(0, strings.filter(i -> i.length() > 1).size());

        Assertions.assertEquals(1, strings.map(String::length).get(0));
    }

    @Test
    void list2() {
        var strings = DmCollections.listOf("ab", "ba");

        Assertions.assertFalse(strings.isEmpty());
        Assertions.assertTrue(strings.isNotEmpty());
        Assertions.assertEquals(2, strings.size());

        Assertions.assertEquals(1, strings.indexOf("ba"));
        Assertions.assertTrue(strings.contains("ab"));

        Assertions.assertEquals("ab", strings.first().get());
        Assertions.assertEquals("ba", strings.last().get());

        Assertions.assertEquals(2, strings.filter(i -> i.contains("b")).size());

        Assertions.assertEquals(2, strings.map(String::length).get(1));
    }

    @Test
    void list3() {
        var strings = DmCollections.listOf("ab", "ba", "bb");

        Assertions.assertFalse(strings.isEmpty());
        Assertions.assertTrue(strings.isNotEmpty());
        Assertions.assertEquals(3, strings.size());

        Assertions.assertEquals(1, strings.indexOf("ba"));
        Assertions.assertTrue(strings.contains("bb"));

        Assertions.assertEquals("ab", strings.first().get());
        Assertions.assertEquals("bb", strings.last().get());

        Assertions.assertEquals(2, strings.filter(i -> i.contains("a")).size());

        Assertions.assertEquals(2, strings.map(String::length).get(2));
    }

    @Test
    void testImmutable() {
        var strings = DmCollections.listOf("ab", "cd");
        var mutableStrings = strings.toMutableList();
        Assertions.assertNotSame(strings, mutableStrings);
    }

    @Test
    void clearList() {
        var strings = DmCollections.listOf("ab", "cd");
        var mutableStrings = strings.toMutableList();
        Assertions.assertEquals(2, mutableStrings.size());

        mutableStrings.clear();
        Assertions.assertEquals(2, strings.size());
        Assertions.assertEquals(0, mutableStrings.size());
    }

    @Test
    void add() {
        var strings = DmCollections.listOf("ab", "cd");
        var mutableStrings = strings.toMutableList();
        Assertions.assertEquals(2, mutableStrings.size());

        mutableStrings.add("ef");
        Assertions.assertEquals(2, strings.size());
        Assertions.assertEquals(3, mutableStrings.size());
    }

    @Test
    void addAt() {
        MutableList<String> strings = DmCollections.listOf("ab", "cd").toMutableList();
        Assertions.assertEquals(2, strings.size());

        strings.add(0, "ef");
        Assertions.assertEquals(3, strings.size());
        Assertions.assertEquals("ef", strings.get(0));
        Assertions.assertEquals("ab", strings.get(1));
    }

    @Test
    void plus() {
        ReadOnlyList<String> first = DmCollections.listOf("ab", "cd");
        ReadOnlyList<String> second = DmCollections.listOf("ef", "gh");

        var result = first.plus(second);

        Assertions.assertEquals(4, result.size());
        Assertions.assertEquals("cd", result.get(1));
        Assertions.assertEquals("ef", result.get(2));
    }

    @Test
    void remove() {
        var strings = DmCollections.listOf("ab", "cd");
        var mutableStrings = strings.toMutableList();
        Assertions.assertEquals(2, mutableStrings.size());

        mutableStrings.remove("ab");
        Assertions.assertEquals(2, strings.size());
        Assertions.assertEquals(1, mutableStrings.size());
    }

    @Test
    void take() {
        var strings = DmCollections.listOf("aaa", "bbb", "ccc");
        var newStrings = strings.take(2);

        Assertions.assertEquals(3, strings.size());
        Assertions.assertEquals(2, newStrings.size());
        Assertions.assertEquals("bbb", newStrings.get(1));
    }

    @Test
    void takeAllExists() {
        var strings = DmCollections.listOf("aaa", "bbb", "ccc");
        var newStrings = strings.take(10);

        Assertions.assertEquals(3, strings.size());
        Assertions.assertEquals(3, newStrings.size());
    }

    @Test
    void skip() {
        var strings = DmCollections.listOf("aaa", "bbb", "ccc");
        var newStrings = strings.skip(2);

        Assertions.assertEquals(3, strings.size());
        Assertions.assertEquals(1, newStrings.size());
        Assertions.assertEquals("ccc", newStrings.get(0));
    }

    @Test
    void skipMoreThanExists() {
        var strings = DmCollections.listOf("aaa", "bbb", "ccc");
        var newStrings = strings.skip(10);

        Assertions.assertEquals(3, strings.size());
        Assertions.assertEquals(0, newStrings.size());
    }

    @Test
    void toMap() {
        var strings = DmCollections.listOf("aa", "bbb", "dddd");
        var map = strings.toReadOnlyMap(i -> i.substring(0, 1), String::length);

        Assertions.assertEquals(3, map.size());
        Assertions.assertEquals(4, map.get("d"));
    }

    @Test
    void associateBy() {
        var strings = DmCollections.listOf("aaa", "bbb", "ccc");
        var map = strings.associateBy(i -> i.substring(0, 1));

        Assertions.assertEquals(3, map.size());
        Assertions.assertEquals("bbb", map.get("b"));
    }

    @Test
    void associateByWithMerge() {
        var strings = DmCollections.listOf("aaa", "bbb", "bbc");
        var map = strings.associateBy(i -> i.substring(0, 1), (a, b) -> a);

        Assertions.assertEquals(2, map.size());
        Assertions.assertEquals("bbb", map.get("b"));
    }

    @Test
    void joinToStringEmptyList() {
        ReadOnlyList<String> emptyString = DmCollections.emptyList();
        Assertions.assertEquals("", emptyString.joinToString(", "));
    }

    @Test
    void joinToStringList1() {
        var strings = DmCollections.listOf("aaa");
        Assertions.assertEquals("aaa", strings.joinToString(", "));
    }

    @Test
    void joinToStringList2() {
        var strings = DmCollections.listOf(11, 22);
        Assertions.assertEquals("11, 22", strings.joinToString(", "));
    }

    @Test
    void joinToStringList3() {
        var strings = DmCollections.listOf("aaa", "bbb", "ccc");
        Assertions.assertEquals("aaa, bbb, ccc", strings.joinToString(", "));
    }

    @Test
    void allMatch() {
        var strings = DmCollections.listOf("apple", "banana", "cherry");

        Assertions.assertTrue(strings.all(i -> i.length() >= 5));
        Assertions.assertFalse(strings.all(i -> i.startsWith("z")));
    }

    @Test
    void anyMatch() {
        var strings = DmCollections.listOf("apple", "banana", "cherry");

        Assertions.assertTrue(strings.any(i -> i.startsWith("b")));
        Assertions.assertFalse(strings.any(i -> i.startsWith("z")));
    }

    @Test
    void noneMatch() {
        var strings = DmCollections.listOf("apple", "banana", "cherry");

        Assertions.assertTrue(strings.none(i -> i.startsWith("z")));
        Assertions.assertFalse(strings.none(i -> i.startsWith("a")));
    }
}
