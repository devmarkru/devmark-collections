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
}
