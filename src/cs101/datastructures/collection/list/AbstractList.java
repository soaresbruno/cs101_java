package cs101.datastructures.collection.list;

import cs101.datastructures.collection.AbstractCollection;
import cs101.datastructures.collection.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: bruno
 * Date: 6/30/12
 * Time: 9:36 AM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractList<T> extends AbstractCollection<T> implements List<T> {

    public boolean remove(T element) {
        int index = indexOf(element);

        if (index >= 0) {
            remove(index);
            return true;
        }
        else {
            return false;
        }
    }
    
    public T removeFirst() {
        return size == 0 ? null : remove(0);
    }
    
    public T removeLast() {
        return size == 0 ? null : remove(size - 1);
    }

    public boolean contains(T element) {
        return indexOf(element) >= 0;
    }

    public T first() {
        return size == 0 ? null : get(0);
    }
    
    public T last() {
        return size == 0 ? null : get(size - 1);
    }

    protected void assertValidIndex(int index, boolean inserting) {
        if (index < 0) {
            throw new IndexOutOfBoundsException(index + "<0");
        }
        else if (inserting && index > size) {
            throw new IndexOutOfBoundsException(index + ">" + size);
        }
        else if (!inserting && index >= size) {
            throw new IndexOutOfBoundsException(index + ">=" + size);
        }
    }
    
}
