package cs101.datastructures.collection;

import com.sun.org.apache.xml.internal.utils.ObjectPool;

/**
 * Created by IntelliJ IDEA.
 * User: bruno
 * Date: 7/1/12
 * Time: 4:32 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Collection<T> {

    public void add(T element);
    public boolean remove(T element);
    public void removeAll();
    public boolean contains(T element);
    public boolean isEmpty();
    public int size();
    public Iterator<T> iterator();
    public Object[] toArray();
    public T[] toArray(T[] array);

}
