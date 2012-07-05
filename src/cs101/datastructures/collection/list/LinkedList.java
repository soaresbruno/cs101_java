package cs101.datastructures.collection.list;

import cs101.datastructures.collection.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: bruno
 * Date: 6/30/12
 * Time: 11:00 AM
 * To change this template use File | Settings | File Templates.
 */
public class LinkedList<T> extends AbstractList<T> {

    protected LinkedListNode<T> first;
    private long modifyCount;

    protected LinkedListNode<T> createNode(T value) {
        return new LinkedListNode<T>(value);
    }

    protected LinkedListNode<T> getNodeAt(int index) {
        LinkedListNode<T> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }

        return node;
    }
    
    public void add(T element) {
        insert(element, size);
    }

    public void insert(T element, int index) {
        assertValidIndex(index, true);
        
        LinkedListNode<T> node = createNode(element);
        if (index == 0) {
            doInsert(null, node, first);
        }
        else {
            LinkedListNode<T> prev = getNodeAt(index - 1);
            doInsert(prev, node, prev.next);
        }

        size++;
        modifyCount++;
    }
    
    protected void doInsert(LinkedListNode<T> prev, LinkedListNode<T> node, LinkedListNode<T> next) {
        if (prev == null) {
            first = node;
        }
        else {
            prev.next = node;
        }
    
        node.next = next;
    }

    public T remove(int index) {
        assertValidIndex(index, false);
        T element = null;

        if (index == 0) {
            element = first.value;
            doRemove(null, first.next);
        }
        else {
            LinkedListNode<T> prev = getNodeAt(index - 1);
            element = prev.next.value;
            doRemove(prev, prev.next.next);
        }

        size--;
        modifyCount++;
        return element;
    }
    
    protected void doRemove(LinkedListNode<T> prev, LinkedListNode<T> next) {
        if (prev == null) {
            first = next;
        }
        else {
            prev.next = next;
        }
    }
    
    public void removeAll() {
        first = null;
        size = 0;
        modifyCount++;
    }

    public int indexOf(T element) {
        LinkedListNode<T> node = first;

        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (node.value == null) {
                    return i;
                }
                node = node.next;
            }
        }
        else {
            for (int i = 0; i < size; i++) {
                if (element.equals(node.value)) {
                    return i;
                }
                node = node.next;
            }
        }

        return -1; // not found
    }

    public T get(int index) {
        assertValidIndex(index, false);

        LinkedListNode<T> node = getNodeAt(index);
        return node.value;
    }
    
    public T replace(T element, int index) {
        assertValidIndex(index, false);

        LinkedListNode<T> node = getNodeAt(index);
        T oldElement = node.value;
        node.value = element;

        modifyCount++;
        return oldElement;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private LinkedListNode<T> node = first;
            private long initialModifyCount = modifyCount;

            public boolean hasNext() {
                return node != null;
            }

            public T next() {
                if (modifyCount != initialModifyCount) {
                    throw new IllegalStateException("Iterator is invalid; collection has been modified.");
                }

                T value = null;
                if (node != null) {
                    value = node.value;
                    node = node.next;
                }

                return value;
            }
        };
    }

}

class LinkedListNode<T> {
    
    T value;
    LinkedListNode<T> next;
    
    public LinkedListNode(T value) {
        this.value = value;
    }
    
}
