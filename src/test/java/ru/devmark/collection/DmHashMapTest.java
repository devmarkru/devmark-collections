package ru.devmark.collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class DmHashMapTest {

    @Test
    void fromSourceMap() {
        Map<String, String> source = new HashMap<>();
        source.put("a", "apple");
        source.put("b", "banana");
        var dic = DmCollections.toReadOnlyMap(source).toMutableMap();
        source.clear();

        Assertions.assertEquals(0, source.size());
        Assertions.assertEquals(2, dic.size());
    }

    @Test
    void emptyMap() {
        ReadOnlyMap<String, String> map = DmCollections.emptyMap();

        Assertions.assertTrue(map.isEmpty());
        Assertions.assertFalse(map.isNotEmpty());
        Assertions.assertEquals(0, map.size());
        Assertions.assertFalse(map.containsKey("s"));
        Assertions.assertFalse(map.containsValue("s"));
    }

    @Test
    void map1() {
        ReadOnlyMap<String, String> map = DmCollections.mapOf(Pair.of("a", "apple"));

        Assertions.assertFalse(map.isEmpty());
        Assertions.assertTrue(map.isNotEmpty());
        Assertions.assertEquals(1, map.size());
        Assertions.assertTrue(map.containsKey("a"));
        Assertions.assertTrue(map.containsValue("apple"));
    }

    @Test
    void map2() {
        ReadOnlyMap<String, String> map = DmCollections.mapOf(
                Pair.of("a", "apple"),
                Pair.of("b", "banana")
        );

        Assertions.assertFalse(map.isEmpty());
        Assertions.assertTrue(map.isNotEmpty());
        Assertions.assertEquals(2, map.size());
        Assertions.assertTrue(map.containsKey("a"));
        Assertions.assertTrue(map.containsValue("banana"));
    }

    @Test
    void map3() {
        ReadOnlyMap<String, String> map = DmCollections.mapOf(
                Pair.of("a", "apple"),
                Pair.of("b", "banana"),
                Pair.of("m", "melon")
        );

        Assertions.assertFalse(map.isEmpty());
        Assertions.assertTrue(map.isNotEmpty());
        Assertions.assertEquals(3, map.size());
        Assertions.assertTrue(map.containsKey("b"));
        Assertions.assertTrue(map.containsValue("melon"));
    }
}
