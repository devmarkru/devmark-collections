package ru.devmark.collection;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class DmHashSet<T> implements MutableSet<T> {

    private final Set<T> set;

    public DmHashSet(Collection<T> set) {
        this.set = new HashSet<>(set);
    }

    @Override
    public int size() {
        return set.size();
    }

    @Override
    public boolean isEmpty() {
        return set.isEmpty();
    }

    @Override
    public boolean isNotEmpty() {
        return !set.isEmpty();
    }

    @Override
    public boolean contains(T element) {
        return set.contains(element);
    }

    @Override
    public ReadOnlyList<T> filter(Predicate<? super T> predicate) {
        return new DmArrayList<>(set.stream().filter(predicate).toList());
    }

    @Override
    public <R> ReadOnlyList<R> map(Function<? super T, ? extends R> mapper) {
        List<R> list = new ArrayList<>();
        set.forEach(e -> list.add(mapper.apply(e)));
        return new DmArrayList<>(list);
    }

    @Override
    public ReadOnlyList<T> toReadOnlyList() {
        return new DmArrayList<>(set);
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        set.forEach(action);
    }

    @Override
    public boolean add(T element) {
        return set.add(element);
    }

    @Override
    public void clear() {
        set.clear();
    }

    @Override
    public boolean remove(T element) {
        return set.remove(element);
    }

    @Override
    public MutableSet<T> toMutableSet() {
        return new DmHashSet<>(set);
    }

    @Override
    public String toString() {
        return set.toString();
    }
}
