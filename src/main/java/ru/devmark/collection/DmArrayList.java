package ru.devmark.collection;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class DmArrayList<T> implements MutableList<T> {

    private final List<T> list;

    DmArrayList(Collection<T> list) {
        this.list = new ArrayList<>(list);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public T get(int index) {
        return list.get(index);
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean isNotEmpty() {
        return !list.isEmpty();
    }

    @Override
    public boolean contains(T element) {
        return list.contains(element);
    }

    @Override
    public int indexOf(T element) {
        return list.indexOf(element);
    }

    @Override
    public T firstOrNull() {
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public T lastOrNull() {
        if (list.isEmpty()) {
            return null;
        }
        return list.get(list.size() - 1);
    }

    @Override
    public ReadOnlyList<T> filter(Predicate<? super T> predicate) {
        List<T> filteredList = new ArrayList<>();
        list.forEach(item -> {
            if (predicate.test(item)) {
                filteredList.add(item);
            }
        });
        return new DmArrayList<>(filteredList);
    }

    @Override
    public <R> ReadOnlyList<R> map(Function<? super T, ? extends R> mapper) {
        List<R> mappedList = new ArrayList<>();
        list.forEach(item -> {
            mappedList.add(mapper.apply(item));
        });
        return new DmArrayList<>(mappedList);
    }

    @Override
    public MutableList<T> toMutableList() {
        return new DmArrayList<>(list);
    }

    @Override
    public boolean add(T element) {
        return list.add(element);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public boolean remove(T element) {
        return list.remove(element);
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        list.forEach(action);
    }

    @Override
    public ReadOnlyList<T> take(int count) {
        return new DmArrayList<>(list.subList(0, count));
    }

    @Override
    public ReadOnlyList<T> skip(int count) {
        return new DmArrayList<>(list.subList(count, list.size()));
    }

    @Override
    public ReadOnlySet<T> toReadOnlySet() {
        return new DmHashSet<>(list);
    }

    @Override
    public <K> ReadOnlyMap<K, T> associateBy(Function<? super T, ? extends K> keyMapper) {
        return new DmHashMap<>(list.stream().collect(Collectors.toMap(keyMapper, Function.identity())));
    }

    @Override
    public String toString() {
        return list.toString();
    }

    @Override
    public T[] toArray(Class<T> klass) {
        return toArray(list, (T[]) Array.newInstance(klass, list.size()));
    }

    private <T> T[] toArray(Collection<T> c, T[] a) {
        return c.size() > a.length ?
                c.toArray((T[]) Array.newInstance(a.getClass().getComponentType(), c.size())) :
                c.toArray(a);
    }
}
