package ru.devmark.collection;

public interface MutableSet<T> extends ReadOnlySet<T> {

    boolean add(T element);

    void clear();

    boolean remove(T element);

    ReadOnlySet<T> toReadOnlySet();
}
