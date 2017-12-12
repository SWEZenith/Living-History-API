package com.zenith.livinghistory.api.zenithlivinghistoryapi.common;

import java.util.HashMap;

public class LRUCache<K,V> {

    //region Node class

    class Node<T,U>{

        Node<T,U> previous;
        Node<T,U> next;
        T key;
        U value;

        public Node(Node<T,U> previous, Node<T,U> next, T key, U value){

            this.previous = previous;
            this.next = next;
            this.key = key;
            this.value = value;
        }
    }

    //endregion

    //region Private Members

    private HashMap<K, Node<K,V>> cache = new HashMap<>();

    private Node<K,V> leastRecentlyUsed;

    private Node<K,V> mostRecentlyUsed;

    private int maxSize;

    private int currentSize;

    //endregion

    //region Constructor

    public LRUCache(int maxSize) {

        this.maxSize = maxSize;
        this.currentSize = 0;
    }

    //endregion

    //region Public Methods

    public V get(K key){

        Node<K,V> node = cache.get(key);

        if (node == null)
            return null;
        else if(mostRecentlyUsed.key.equals(key))
            return mostRecentlyUsed.value;

        Node<K, V> next = node.next;
        Node<K,V> previous = node.previous;

        if (node.key.equals(leastRecentlyUsed.key)){

            next.previous = null;
            leastRecentlyUsed = next;

        } else if (!node.key.equals(mostRecentlyUsed.key)){

            previous.next = next;
            next.previous = previous;
        }

        node.previous = mostRecentlyUsed;
        mostRecentlyUsed.next = node;
        mostRecentlyUsed = node;
        mostRecentlyUsed.next = null;

        return node.value;
    }

    public void put(K key, V value){

        if (cache.containsKey(key))
            return;

        Node<K,V> node = new Node<>(mostRecentlyUsed, null, key, value);

        if (mostRecentlyUsed != null)
            mostRecentlyUsed.next = node;

        cache.put(key, node);
        mostRecentlyUsed = node;

        if (currentSize == maxSize){

            cache.remove(leastRecentlyUsed.key);
            leastRecentlyUsed = leastRecentlyUsed.next;
            leastRecentlyUsed.previous = null;

        } else if (currentSize < maxSize){

            if (currentSize == 0)
                leastRecentlyUsed = node;

            currentSize++;
        }
    }

    //endregion
}