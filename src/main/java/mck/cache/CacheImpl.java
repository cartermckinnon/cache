package mck.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class CacheImpl<K, V> implements Cache<K, V> {

    private final Map<K,DoublyLinkedNode<K,V>> map;
    private final int capacity;

    private transient DoublyLinkedNode<K,V> head;
    private transient DoublyLinkedNode<K,V> tail;

    protected CacheImpl(int capacity) {
        this.map = new HashMap<K,DoublyLinkedNode<K,V>>();
        this.capacity = capacity;
        this.head = null;
        this.tail = null;
    }

    public Optional<V> get(K key) {
        DoublyLinkedNode<K,V> node = map.get(key);
        if(node != null) {
            accessed(node);
        }
        return Optional.ofNullable(node.value);
    }

    public void set(K key, V value) {
        DoublyLinkedNode<K,V> node = map.computeIfAbsent(key, ignored -> new DoublyLinkedNode<K,V>(key, value));
        accessed(node);
    }

    public void clear(K key) {
        DoublyLinkedNode<K,V> node = map.remove(key);
        if(node != null) {
            node.unlink();
        }
    }
    
    private void accessed(DoublyLinkedNode<K,V> node) {
        if(head == null) {
            head = node;
            tail = node;
        } else {
            node.unlink().link(head);
        }
        if(map.size() > capacity) {
            if(tail != null) {
                tail = tail.parent;
            }
        }
    }
}