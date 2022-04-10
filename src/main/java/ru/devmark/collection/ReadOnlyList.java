package ru.devmark.collection;

import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

public interface ReadOnlyList<T> extends Iterable<T> {

    int size();

    T get(int index);

    boolean isEmpty();

    boolean isNotEmpty();

    boolean contains(T element);

    int indexOf(T element);

    Optional<T> first();

    Optional<T> last();

    ReadOnlyList<T> filter(Predicate<? super T> predicate);

    <R> ReadOnlyList<R> map(Function<? super T, ? extends R> mapper);

    MutableList<T> toMutableList();

    ReadOnlyList<T> take(int count);

    ReadOnlyList<T> skip(int count);

    ReadOnlySet<T> toReadOnlySet();

    <K, V> ReadOnlyMap<K, V> toReadOnlyMap(Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends V> valueMapper);

    <K> ReadOnlyMap<K, T> associateBy(Function<? super T, ? extends K> keyMapper);

    <K> ReadOnlyMap<K, T> associateBy(Function<? super T, ? extends K> keyMapper, BinaryOperator<T> mergeFunction);

    T[] toArray(Class<T> klass);

    String joinToString(String delimiter);

    List<T> toList();

    boolean all(Predicate<? super T> predicate);

    boolean any(Predicate<? super T> predicate);

    boolean none(Predicate<? super T> predicate);

    ReadOnlyList<T> plus(ReadOnlyList<T> collection);
}
