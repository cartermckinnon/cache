package mck.data_structures;

class ValueNode<K,V> {
    private final K key;
    private final V value;

    protected KeyValue(K key, V value) {
        this.key = key;
        this.value = value;
    }
}