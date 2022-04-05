package ru.devmark.collection;

public interface MutableList<T> extends ReadOnlyList<T> {

    boolean add(T element);

    void clear();

    boolean remove(T element);
}
