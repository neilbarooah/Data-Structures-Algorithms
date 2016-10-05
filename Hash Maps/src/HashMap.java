import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.HashSet;

/**
 * Your implementation of HashMap.
 * 
 * @author Neil Barooah
 * @version 1.3
 */
public class HashMap<K, V> implements HashMapInterface<K, V> {

    // Do not make any new instance variables.
    private MapEntry<K, V>[] table;
    private int size;

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code STARTING_SIZE}.
     *
     * Do not use magic numbers!
     *
     * Use constructor chaining.
     */
    public HashMap() {
        this(STARTING_SIZE);
    }

    /**
     * Create a hash map with no entries. The backing array has an initial
     * capacity of {@code initialCapacity}.
     *
     * You may assume {@code initialCapacity} will always be positive.
     *
     * @param initialCapacity initial capacity of the backing array
     */
    public HashMap(int initialCapacity) {
        table = new MapEntry[initialCapacity];
        size = 0;
    }

    @Override
    public V add(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Key/Value cannot be null.");
        }
        if ((double) (size + 1) / table.length >  MAX_LOAD_FACTOR) {
            resizeBackingTable(table.length * 2 + 1);
        }
        int tableIndex = this.getIndex(key);
        MapEntry<K, V> entry = this.table[tableIndex];
        V finalValue = null;
        if (entry == null) {
            table[tableIndex] = new MapEntry<K, V>(key, value);
        } else if (entry.getKey().equals(key)) {
            if (!entry.isRemoved()) {
                finalValue = entry.getValue();
            }
            entry.setValue(value);
            entry.setRemoved(false);
        } else {
            entry.setKey(key);
            entry.setValue(value);
            entry.setRemoved(false);
        }
        if (finalValue == null) {
            size++;
        }
        return finalValue;
    }

    /**
     * Helper method for add/remove to get the corresponding index in
     * table of key
     * @param key key in the map
     * @return  int representing corresponding index in table
     */
    private int getIndex(K key) {
        int index = Math.abs(key.hashCode()) % table.length;
        MapEntry<K, V> entry = table[index];
        int removed = -1;
        int i = index;
        boolean looped = false;
        while (entry != null && !entry.getKey().equals(key) && (!looped
                || i != index)) {
            if (removed == -1 && entry.isRemoved()) {
                removed = index;
            }
            if (++i >= table.length) {
                i = 0;
                looped = true;
            }
            entry = table[i];
        }
        if (entry != null && entry.getKey().equals(key)) {
            return i;
        } else {
            return (removed == -1 ? i : removed);
        }
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("The given key cannot be null");
        }
        int index = this.getIndex(key);
        MapEntry<K, V> entry = table[index];
        if (entry != null && entry.getKey().equals(key) && !entry.
                isRemoved()) {
            entry.setRemoved(true);
            size--;
            return entry.getValue();
        }
        throw new NoSuchElementException("No such elements exist.");
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("The key cannot be null.");
        }
        int index = Math.abs(key.hashCode()) % table.length;
        if (size == 0) {
            throw new NoSuchElementException("No such element exists.");
        } else if (table[index] != null && !table[index].isRemoved()
                && table[index].getKey().equals(key)) {
            return table[index].getValue();
        } else {
            for (int i = index; i < (index + table.length); i++) {
                if (table[i % table.length] != null && !table[i % table.length]
                        .isRemoved() && table[i % table.length].getKey().
                        equals(key)) {
                    return table[i % table.length].getValue();
                }
            }
        }
        throw new NoSuchElementException("No such element exists.");
    }

    @Override
    public boolean contains(K key) {
        if (key == null) {
            throw new IllegalArgumentException("The key cannot be null.");
        }
        int index = Math.abs(key.hashCode()) % table.length;
        if (size == 0) {
            return false;
        } else if (table[index] != null && !table[index].isRemoved()
                && table[index].getKey().equals(key)) {
            return true;
        } else {
            for (int i = index; i < (index + table.length); i++) {
                if (table[i % table.length] != null && !table[i % table.length]
                        .isRemoved() && table[i % table.length].getKey().
                        equals(key)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void clear() {
        table = (MapEntry<K, V>[]) new MapEntry[STARTING_SIZE];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<K>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && !table[i].isRemoved()) {
                keys.add(table[i].getKey());
            }
        }
        return keys;
    }

    @Override
    public List<V> values() {
        List<V> values = new ArrayList<V>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null && !table[i].isRemoved()) {
                values.add(table[i].getValue());
            }
        }
        return values;
    }

    @Override
    public void resizeBackingTable(int length) {
        if (length <= 0 || length < size) {
            throw new IllegalArgumentException("The size entered is"
                    + "too small.");
        }
        MapEntry<K, V>[] tempMap = table;
        table = (MapEntry<K, V>[]) new MapEntry[length];
        size = 0;
        for (int i = 0; i < tempMap.length; i++) {
            MapEntry<K, V> entry = tempMap[i];
            if (entry != null && !entry.isRemoved()) {
                add(entry.getKey(), entry.getValue());
            }
        }
    }
    
    @Override
    public MapEntry<K, V>[] getTable() {
        // DO NOT EDIT THIS METHOD!
        return table;
    }
}
