package cs101.datastructures.map;

import cs101.datastructures.collection.list.List;
import cs101.datastructures.collection.list.Vector;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: bruno
 * Date: 6/30/12
 * Time: 12:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class Hashtable<K, V> extends AbstractMap<K, V> {

    private Object[] table;
    private float loadFactor;

    private static final double LOG_2 = Math.log(2.0);

    public Hashtable() {
        this(16, 0.75f);
    }

    public Hashtable(int capacity) {
        this(capacity, 0.75f);
    }

    public Hashtable(int capacity, float loadFactor) {
        this.loadFactor = loadFactor;
        grow(capacity);
    }

    private void grow(int newCapacity) {
        // Calculate new capacity as the next power of two that is bigger than newCapacity
        newCapacity = (int) Math.pow(2, Math.ceil(Math.log(newCapacity) / LOG_2));

        // Store existing entries for rehash
        Object[] entries = null;
        if (table != null && size > 0) {
            entries = new Object[size];
            for (int i = 0, j = 0, n = table.length; i < n; i++) {
                HashtableEntry<K, V> entry = (HashtableEntry<K, V>) table[i];
                while (entry != null) {
                    entries[j++] = entry;
                    entry = entry.next;
                }
            }
        }

        // Instantiate new table
        table = new Object[newCapacity];

        // Re-hash entries
        if (entries != null) {
            for (int i = 0, n = entries.length; i < n; i++) {
                HashtableEntry<K, V> entry = (HashtableEntry<K, V>) entries[i];
                entry.next = null; // clear next pointer, since this will be the last entry in the bucket

                int index = entry.key.hashCode() % table.length;
                HashtableEntry<K, V> tableEntry = (HashtableEntry<K, V>) table[index];

                if (tableEntry == null) {
                    table[index] = entry;
                }
                else {
                    while (true) {
                        if (tableEntry.next == null) {
                            tableEntry.next = entry;
                            break;
                        }
                        tableEntry = tableEntry.next;
                    }
                }
            }
        }
    }

    public void set(K key, V value) {
        int index = key.hashCode() % table.length;
        HashtableEntry<K, V> entry = (HashtableEntry<K, V>) table[index];

        // If this is the first key to fall at index
        if (entry == null) {
            table[index] = entry = new HashtableEntry<K, V>(key);
            size++;
        }
        // Otherwise, handle collision
        else {
            // First, locate entry for given key
            while (true) {
                if (key.equals(entry.key)) {
                    break;
                }
                // No more entries, so just create a new one
                if (entry.next == null) {
                    entry = entry.next = new HashtableEntry<K, V>(key);
                    size++;
                    break;
                }
                // Otherwise, move to next entry
                else {
                    entry = entry.next;
                }
            }
        }

        // Now that we have the entry, update value
        entry.value = value;

        // Check size and grow table if needed
        if (loadFactor > 0 && (size * 1.0 / table.length) > loadFactor) {
            grow(table.length * 2);
        }
    }

    public V remove(K key) {
        V value = null;
        int index = key.hashCode() % table.length;
        HashtableEntry<K, V> entry = (HashtableEntry<K, V>) table[index];
        HashtableEntry<K, V> prev = null;

        // Locate entry for given key
        while (entry != null) {
            if (key.equals(entry.key)) {
                break;
            }
            prev = entry;
            entry = entry.next;
        }

        // Entry found, so store value and remove it
        if (entry != null) {
            value = entry.value;

            // If this is the first entry for hash, update first entry for index
            if (prev == null) {
                table[index] = entry.next;
            }
            // Otherwise, remove entry (adjust linked list)
            else {
                prev.next = entry.next;
            }
            size--;
        }

        return value;
    }

    public void removeAll() {
        Arrays.fill(table, null);
        size = 0;
    }

    public boolean contains(K key) {
        int index = key.hashCode() % table.length;
        HashtableEntry<K, V> entry = (HashtableEntry<K, V>) table[index];
        
        while (entry != null) {
            if (key.equals(entry.key)) {
                return true;
            }
            entry = entry.next;
        }

        return false;
    }
    
    public boolean containsValue(V value) {
        // If value is null, return true when the first null value is found
        if (value == null) {
            for (int i = 0, n = table.length; i < n; i++) {
                HashtableEntry<K, V> entry = (HashtableEntry<K, V>) table[i];
                while (entry != null) {
                    if (entry.value == null) {
                        return true;
                    }
                    entry = entry.next;
                }
            }
        }
        // Otherwise, locate value comparing objects
        else {
            for (int i = 0, n = table.length; i < n; i++) {
                HashtableEntry<K, V> entry = (HashtableEntry<K, V>) table[i];
                while (entry != null) {
                    if (value.equals(entry.value)) {
                        return true;
                    }
                    entry = entry.next;
                }
            }
        }
        
        return false;
    }
    
    public V get(K key) {
        int index = key.hashCode() % table.length;
        HashtableEntry<K, V> entry = (HashtableEntry<K, V>) table[index];
        
        while (entry != null) {
            if (key.equals(entry.key)) {
                return entry.value;
            }
            entry = entry.next;
        }

        return null;
    }

    public List<K> keys() {
        Vector<K> keys = new Vector<K>(size);

        for (int i = 0, n = table.length; i < n; i++) {
            HashtableEntry<K, V> entry = (HashtableEntry<K, V>) table[i];
            while (entry != null) {
                keys.add(entry.key);
                entry = entry.next;
            }
        }
        
        return keys;
    }

    public List<V> values() {
        Vector<V> values = new Vector<V>(size);

        for (int i = 0, n = table.length; i < n; i++) {
            HashtableEntry<K, V> entry = (HashtableEntry<K, V>) table[i];
            while (entry != null) {
                values.add(entry.value);
                entry = entry.next;
            }
        }

        return values;
    }
}

class HashtableEntry<K, V> {
    
    K key;
    V value;
    HashtableEntry<K, V> next;
    
    public HashtableEntry(K key) {
        this.key = key;
    }

}
