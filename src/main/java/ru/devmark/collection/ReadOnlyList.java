package ru.devmark.collection;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
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

    <K> ReadOnlyMap<K, T> associateBy(Function<? super T, ? extends K> keyMapper);

    T[] toArray(Class<T> klass);

    String joinToString(String delimiter);

    List<T> toList();
}
