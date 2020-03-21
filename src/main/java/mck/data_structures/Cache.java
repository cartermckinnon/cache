package mck.data_structures;

import java.util.Optional;

public interface Cache<K, V> {
    public Optional<V> get(K key);

    public void set(K key, V value);

    public void clear(K key);
}