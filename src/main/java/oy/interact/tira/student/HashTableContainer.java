package oy.interact.tira.student;

import java.util.function.Predicate;

import oy.interact.tira.util.Pair;
import oy.interact.tira.util.TIRAKeyedContainer;

public class HashTableContainer<K extends Comparable<K>,V> implements TIRAKeyedContainer<K,V> {
    
    private static final int DEFAULT_HASHTABLE_SIZE = 20;
    private Pair<K, V> [] itemArray = null;
    int count = 0;
    int pairsUpdated = 0;
    int collisionCount = 0;
    int maxProbingCount = 0;

    @SuppressWarnings("unchecked")
    public HashTableContainer() {
        itemArray = (Pair<K, V> []) new Pair[DEFAULT_HASHTABLE_SIZE];
    }

    private int indexFor(int hash, int hashModifier) {
        return ((hash + hashModifier) & 0x7FFFFFFF) % itemArray.length;
    }

    @SuppressWarnings("unchecked")
    private void reallocate(int newCapacity) {
        int oldCapacity = capacity();
        Pair<K, V> [] oldArray = null;
        oldArray = itemArray;
        itemArray = (Pair<K, V> []) new Pair[newCapacity];
        collisionCount = 0;
        maxProbingCount = 0;
        count = 0;
        for (int index = 0; index < oldCapacity; index++) {
            if (oldArray[index] != null) {
                add(oldArray[index].getKey(), oldArray[index].getValue());
            }
        }
    }
    
    @Override
	public void add(K key, V value) throws OutOfMemoryError, IllegalArgumentException {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Trying to add null key or value");
        } if (size() >= 0.65 * capacity()) {
            reallocate((int) (1.35 * capacity()));
        }
            boolean added = false;
            int index = 0;
            int hashModifier = 0;
            int probingCount = 0;
            int hash = key.hashCode();
            do {
                index = indexFor(hash, hashModifier);
                if (itemArray[index] == null) {
                    itemArray[index] = new Pair<>(key, value);
                    added = true;
                    count++;
                } else if (itemArray[index].getKey().equals(key)) {
                    itemArray[index] = new Pair<>(key, value);
                    pairsUpdated++;
                    added = true;
                } else {
                    hashModifier++;
                    collisionCount++;
                    probingCount++;
                }
            } while (!added);
            if (probingCount > maxProbingCount) {
                maxProbingCount = probingCount;
            }
    }

    @Override
	public V get(K key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("Trying to get with null key");
        } else {
            int index = 0;
            int hash = key.hashCode();
            int hashModifier = 0;
            do {
                index = indexFor(hash, hashModifier);
                if (itemArray[index] == null) {
                    return null; // Item not found with given key
                } else if (itemArray[index].getKey().equals(key)) {
                    return itemArray[index].getValue(); // Item found
                } else {
                    hashModifier++;
                    }
                } while (true);
        }
    }

    @Override
	public V find(Predicate<V> searcher) {
        for (int index = 0; index < capacity(); index++) {
            if (itemArray[index] != null) {
                if (searcher.test(itemArray[index].getValue())) {
                    return itemArray[index].getValue();
                }
            }
        }
        return null;
    }

    @Override
	public int size() {
        return count;
    }

    @Override
	public int capacity() {
        return itemArray.length;
    }

    @SuppressWarnings("unchecked")
    @Override
	public void ensureCapacity(int capacity) throws OutOfMemoryError, IllegalArgumentException {
        if (capacity <= 0 || capacity <= size()) {
            throw new IllegalArgumentException("Ensuring capacity with illegal arguments");
        } else {
            if (count == 0) {
                Pair<K, V> [] newArray = null;
                newArray = (Pair<K, V> []) new Pair[capacity];
                itemArray = newArray;
            } else {
                reallocate(capacity);
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
	public void clear() {
        Pair<K, V> [] newArray = null;
        newArray = (Pair<K, V> []) new Pair[DEFAULT_HASHTABLE_SIZE];
        itemArray = newArray;
        count = 0;
        pairsUpdated = 0;
        collisionCount = 0;
        maxProbingCount = 0;
    }

    @SuppressWarnings("unchecked")
	@Override
    public Pair<K,V> [] toArray() throws Exception {
        Pair<K, V> [] toArray = null;
        toArray = (Pair<K, V> []) new Pair[size()];
        int toIndex = 0;
        for (int index = 0; index < capacity(); index++) {
            if (itemArray[index] != null) {
                toArray[toIndex] = itemArray[index];
                toIndex++;
            }
        }
            return toArray;
    }

    
	/**
	 * Removes and retrieves a value assosicated with the key (keys are equal), or null.
	 * 
	 * The key-value pair is removed from the container, if found.
	 * 
	 * @param key The key to use in searching.
	 * @return The value or null if one is not found.
	 * @throws IllegalArgumentException If the key is null.
	 */
    @Override
	public V remove(K key) throws IllegalArgumentException {
        return null;
    }

}