package ru.devmark.collection;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public interface ReadOnlyList<T> {

    int size();

    T get(int index);

    boolean isEmpty();

    boolean isNotEmpty();

    boolean contains(T element);

    int indexOf(T element);

    T firstOrNull();

    T lastOrNull();

    ReadOnlyList<T> filter(Predicate<? super T> predicate);

    <R> ReadOnlyList<R> map(Function<? super T, ? extends R> mapper);

    MutableList<T> toMutableList();

    void forEach(Consumer<? super T> action);

    ReadOnlyList<T> take(int count);

    ReadOnlyList<T> skip(int count);

    ReadOnlySet<T> toReadOnlySet();

    <K> ReadOnlyMap<K, T> associateBy(Function<? super T, ? extends K> keyMapper);

    T[] toArray(Class<T> klass);
}
