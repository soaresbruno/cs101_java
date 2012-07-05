package cs101.datastructures.collection.set;

import cs101.datastructures.collection.Iterator;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: bruno
 * Date: 7/2/12
 * Time: 2:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class HashSet<T> extends AbstractSet<T> {
    
    private Object[] buckets;
    private float loadFactor;
    private long modifyCount;

    private static final double LOG_2 = Math.log(2.0);
    
    public HashSet() {
        this(16, 0.75f);
    }
    
    public HashSet(int capacity) {
        this(capacity, 0.75f);
    }
    
    public HashSet(int capacity, float loadFactor) {
        this.loadFactor = loadFactor;
        grow(capacity);
    }

    private void grow(int newCapacity) {
        // Calculate new capacity as the next power of two that is bigger than newCapacity
        newCapacity = (int) Math.pow(2, Math.ceil(Math.log(newCapacity) / LOG_2));

        // Store existing entries for rehash
        Object[] entries = null;
        if (buckets != null && size > 0) {
            entries = new Object[size];
            for (int i = 0, j = 0, n = buckets.length; i < n; i++) {
                Entry entry = (Entry) buckets[i];
                while (entry != null) {
                    entries[j++] = entry;
                    entry = entry.next;
                }
            }
        }

        // Instantiate new table
        buckets = new Object[newCapacity];

        // Re-hash entries
        if (entries != null) {
            for (int i = 0, n = entries.length; i < n; i++) {
                Entry entry = (Entry) entries[i];
                entry.prev = entry.next = null; // reset list pointers

                int index = entry.hash % buckets.length;
                Entry existingEntry = (Entry) buckets[index];

                if (existingEntry == null) {
                    buckets[index] = entry;
                }
                else {
                    while (true) {
                        if (existingEntry.next == null) {
                            entry.prev = existingEntry;
                            existingEntry.next = entry;
                            break;
                        }
                        existingEntry = existingEntry.next;
                    }
                }
            }
        }
    }
    
    private int hash(T element) {
        return element != null ? element.hashCode() : 0;
    }
    
    private Entry findEntry(T element) {
        int hash = hash(element), index = hash % buckets.length;
        Entry entry = (Entry) buckets[index];

        while (entry != null) {
            if ((element == null && entry.value == null) || (element != null && element.equals(entry.value))) {
                break;
            }
            entry = entry.next;
        }

        return entry;
    }

    public void add(T element) {
        int hash = hash(element), index = hash % buckets.length;
        Entry entry = (Entry) buckets[index];
        
        // If this is the first key to fall at index
        if (entry == null) {
            buckets[index] = entry = new Entry(hash, element);
            size++;
        }
        // Otherwise, handle collision
        else {
            // First, locate entry for given key
            while (true) {
                if ((element == null && entry.value == null) || (element != null && element.equals(entry.value))) {
                    break;
                }
                // No more entries, so just create a new one
                if (entry.next == null) {
                    entry.next = new Entry(hash, element);
                    entry.next.prev = entry;
                    entry = entry.next;

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
        entry.value = element;
        
        // Check size and grow table if needed
        if (loadFactor > 0 && (size * 1.0 / buckets.length) > loadFactor) {
            grow(buckets.length * 2);
        }

        modifyCount++;
    }

    public boolean remove(T element) {
        int hash = hash(element), index = hash % buckets.length;
        Entry entry = findEntry(element);

        // Entry found, so store value and remove it
        if (entry != null) {
            element = entry.value;

            // If this is the first entry for hash, update first entry for index
            if (entry.prev == null) {
                buckets[index] = entry.next;
            }
            // Otherwise, remove entry (adjust linked list)
            else {
                entry.prev.next = entry.next;
            }
            // If there is a next entry, update reverse (prev) link
            if (entry.next != null) {
                entry.next.prev = entry.prev;
            }

            size--;
            modifyCount++;
            return true;
        }
        else {
            return false;
        }
    }

    public void removeAll() {
        Arrays.fill(buckets, null);
        size = 0;
        modifyCount++;
    }

    public boolean contains(T element) {
        return findEntry(element) != null;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private Entry entry;
            private long initialModifyCount = modifyCount;

            public boolean hasNext() {
                return entry != null;
            }

            public T next() {
                if (initialModifyCount != modifyCount) {
                    throw new IllegalStateException("Iterator is invalid; collection has been modified.");
                }
                
                T value = null;

                if (entry != null) {
                    value = entry.value;

                    if (entry.next != null) {
                        entry = entry.next;
                    }
                    else {
                        int index = entry.hash % buckets.length;
                        entry = null;

                        while (entry == null && ++index < buckets.length) {
                            entry = (Entry) buckets[index];
                        }
                    }
                }

                return value;
            }
        };
    }
    
    protected class Entry {

        private int hash;
        private T value;
        private Entry prev;
        private Entry next;
        
        public Entry(int hash, T value) {
            this.value = value;
            this.hash = hash;
        }

    }

}
