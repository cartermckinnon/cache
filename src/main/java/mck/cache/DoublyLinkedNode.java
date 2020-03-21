package mck.cache;

class DoublyLinkedNode<K,V> {
    protected final K key;
    protected final V value;
    
    protected transient DoublyLinkedNode<K,V> parent;
    protected transient DoublyLinkedNode<K,V> child;

    protected DoublyLinkedNode(K key, V value) {
        this.key = key;
        this.value = value;
        this.parent = null;
        this.child = null;
    }

    protected DoublyLinkedNode<K,V> unlink() {
        if(parent != null) {
            this.parent.child = this.child;
        }
        if(child != null) {
            child.parent = this.parent;
        }
        return this;
    }

    protected DoublyLinkedNode<K,V> link(DoublyLinkedNode<K,V> child) {
        if(child != null) {
            this.child = child;
            child.parent = this;
        }
        return this;
    }
}