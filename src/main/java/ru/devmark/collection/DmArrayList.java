package ru.devmark.collection;

import java.lang.reflect.Array;
import java.util.*;
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
    public Optional<T> first() {
        if (list.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(list.get(0));
    }

    @Override
    public Optional<T> last() {
        if (list.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(list.get(list.size() - 1));
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
    public ReadOnlyList<T> take(int count) {
        return new DmArrayList<>(list.subList(0, Math.min(list.size(), count)));
    }

    @Override
    public ReadOnlyList<T> skip(int count) {
        if (count >= list.size()) {
            return new DmArrayList<>(Collections.emptyList());
        }
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
    public T[] toArray(Class<T> klass) {
        return toArray(list, (T[]) Array.newInstance(klass, list.size()));
    }

    @Override
    public ReadOnlyList<T> toReadOnlyList() {
        return new DmArrayList<T>(list);
    }

    @Override
    public String joinToString(String delimiter) {
        return list.stream().map(Object::toString).collect(Collectors.joining(delimiter));
    }

    @Override
    public String toString() {
        return list.toString();
    }

    private <T> T[] toArray(Collection<T> c, T[] a) {
        return c.size() > a.length ?
                c.toArray((T[]) Array.newInstance(a.getClass().getComponentType(), c.size())) :
                c.toArray(a);
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    public List<T> toList() {
        return new ArrayList<>(list);
    }
}
