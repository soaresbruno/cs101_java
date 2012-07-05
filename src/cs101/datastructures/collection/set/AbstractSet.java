package cs101.datastructures.collection.set;

import cs101.datastructures.collection.AbstractCollection;

/**
 * Created by IntelliJ IDEA.
 * User: bruno
 * Date: 7/2/12
 * Time: 2:58 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractSet<T> extends AbstractCollection<T> implements Set<T> {
    
    protected int size;
    
    public boolean isEmpty() {
        return size() == 0;
    }
    
    public int size() {
        return size;
    }
    
}
