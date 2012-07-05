package cs101.datastructures.collection.list;

/**
 * Created by IntelliJ IDEA.
 * User: bruno
 * Date: 6/30/12
 * Time: 11:40 AM
 * To change this template use File | Settings | File Templates.
 */
public class DoublyLinkedList<T> extends LinkedList<T> {

    protected DoublyLinkedListNode<T> last;
    
    @Override
    protected LinkedListNode<T> createNode(T value) {
        return new DoublyLinkedListNode<T>(value);
    }
    
    @Override
    protected LinkedListNode<T> getNodeAt(int index) {
        LinkedListNode<T> node;

        // first half, so walk forwards until node at index is found
        if (index <= size / 2) {
            node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
        }
        // second half, so walk backwards until node at index is found
        else {
            node = last;
            for (int i = size - 1; i > index; i--) {
                node = ((DoublyLinkedListNode<T>) node).prev;
            }
        }

        return node;
    }
    
    @Override
    protected void doInsert(LinkedListNode<T> prev, LinkedListNode<T> node, LinkedListNode<T> next) {
        DoublyLinkedListNode<T> dPrev = (DoublyLinkedListNode<T>) prev;
        DoublyLinkedListNode<T> dNode = (DoublyLinkedListNode<T>) node;
        DoublyLinkedListNode<T> dNext = (DoublyLinkedListNode<T>) next;
        
        if (dPrev == null) {
            first = node;
        }
        else {
            dPrev.next = node;
        }
        
        dNode.prev = dPrev;
        dNode.next = dNext;
        
        if (dNext == null) {
            last = dNode;
        }
        else {
            dNext.prev = dNode;
        }
    }
    
    @Override
    protected void doRemove(LinkedListNode<T> prev, LinkedListNode<T> next) {
        DoublyLinkedListNode<T> dPrev = (DoublyLinkedListNode<T>) prev;
        DoublyLinkedListNode<T> dNext = (DoublyLinkedListNode<T>) next;
        
        if (dPrev == null) {
            first = dNext;
        }
        else {
            dPrev.next = dNext;
        }
        if (dNext == null) {
            last = dPrev;
        }
        else {
            dNext.prev = dPrev;
        }
    }
    
    @Override
    public void removeAll() {
        first = last = null;
        size = 0;
    }

}

class DoublyLinkedListNode<T> extends LinkedListNode<T> {

    DoublyLinkedListNode<T> prev;
    
    public DoublyLinkedListNode(T value) {
        super(value);
    }

}
