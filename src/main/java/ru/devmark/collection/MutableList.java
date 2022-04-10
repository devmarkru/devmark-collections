package ru.devmark.collection;

public interface MutableList<T> extends ReadOnlyList<T> {

    boolean add(T element);

    void add(int index, T element);

    void clear();

    boolean remove(T element);

    ReadOnlyList<T> toReadOnlyList();
}
