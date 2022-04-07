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
}
