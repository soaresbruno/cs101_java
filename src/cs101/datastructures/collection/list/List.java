package cs101.datastructures.collection.list;

import cs101.datastructures.collection.Collection;

/**
 * Created by IntelliJ IDEA.
 * User: bruno
 * Date: 6/30/12
 * Time: 9:34 AM
 * To change this template use File | Settings | File Templates.
 */
public interface List<T> extends Collection<T> {

    public void insert(T element, int index);
    public T replace(T element, int index);
    public T remove(int index);
    public T removeFirst();
    public T removeLast();
    public int indexOf(T element);
    public T get(int index);
    public T first();
    public T last();
    
}
