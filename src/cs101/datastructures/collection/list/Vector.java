package cs101.datastructures.collection.list;

import cs101.datastructures.collection.Iterator;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: bruno
 * Date: 6/30/12
 * Time: 9:19 AM
 * To change this template use File | Settings | File Templates.
 */
public class Vector<T> extends AbstractList<T> {
    
    private Object[] array;
    private long modifyCount;
    
    private static final double LOG_2 = Math.log(2.0);

    public Vector() {
        this(8);
    }

    public Vector(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Capacity must be a non negative integer");
        }

        grow(capacity); // will instantiate array
    }
    
    private void grow(int newCapacity) {
        // Calculate new capacity as the next power of two that is bigger than newCapacity
        newCapacity = (int) Math.pow(2, Math.ceil(Math.log(newCapacity) / LOG_2));

        // Instantiate new array
        Object[] newArray = new Object[newCapacity];
        
        // Copy existing elements, if any
        if (array != null && size > 0) {
            System.arraycopy(array, 0, newArray, 0, size);
        }

        // Switch array to new array
        array = newArray;
    }

    public void setSize(int newSize) {
        if (newSize > size) {
            grow(newSize);
        }
        size = newSize;
        modifyCount++;
    }

    public void add(T element) {
        // Make sure our array can accommodate the new element
        if (size == array.length) {
            grow(size * 2);
        }
        
        // Add the element to the array
        array[size++] = element;
        modifyCount++;
    }
    
    public void insert(T element, int index) {
        assertValidIndex(index, true);
        
        // Make sure our array can accommodate the new element
        if (size == array.length) {
            grow(size * 2);
        }
        
        // Shift all elements after index to the right by one
        if (index != size) {
            System.arraycopy(array, index, array, index + 1, size - index);
        }

        // Insert element in the array
        array[index] = element;
        size++;
        modifyCount++;
    }

    public void removeAll() {
        Arrays.fill(array, null);
        size = 0;
        modifyCount++;
    }

    public int indexOf(T element) {
        // if element is null, search for the first null element
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (array[i] == null) {
                    return i;
                }
            }
        }
        // Otherwise, search for the first object equals to element
        else {
            for (int i = 0; i < size; i++) {
                if (element.equals(array[i])) {
                    return i;
                }
            }
        }
        return -1; // not found
    }
    
    public T remove(int index) {
        assertValidIndex(index, false);

        // Store element to be returned later
        T element = (T) array[index];
        
        // Shift all elements after index to the left by one
        if (index != size - 1) {
            System.arraycopy(array, index + 1, array, index, size - (index + 1));
        }

        // Return removed element
        size--;
        modifyCount++;
        return element;
    }
    
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }

        return (T) array[index];
    }
    
    public T replace(T element, int index) {
        assertValidIndex(index, false);
        
        // Store old element and replace it with new one
        T oldElement = (T) array[index];
        array[index] = element;

        modifyCount++;
        return oldElement;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private int index;
            private long initialModifyCount = modifyCount;
            
            public boolean hasNext() {
                return index < size();
            }
            
            public T next() {
                if (initialModifyCount != modifyCount) {
                    throw new IllegalStateException("Iterator is invalid; collection has been modified.");
                }

                return index < size() ? get(index++) : null;
            }
        };
    }

}
