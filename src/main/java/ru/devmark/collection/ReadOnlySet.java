package ru.devmark.collection;

import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public interface ReadOnlySet<T> {

    int size();

    boolean isEmpty();

    boolean isNotEmpty();

    boolean contains(T element);

    ReadOnlyList<T> filter(Predicate<? super T> predicate);

    <R> ReadOnlyList<R> map(Function<? super T, ? extends R> mapper);

    ReadOnlyList<T> toReadOnlyList();

    void forEach(Consumer<? super T> action);

    MutableSet<T> toMutableSet();

    Set<T> toSet();

    boolean all(Predicate<? super T> predicate);

    boolean any(Predicate<? super T> predicate);

    boolean none(Predicate<? super T> predicate);
}
