package cs101.datastructures.collection;

/**
 * Created by IntelliJ IDEA.
 * User: bruno
 * Date: 7/1/12
 * Time: 4:38 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractCollection<T> implements Collection<T> {

    protected int size;

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size;
    }

    public Object[] toArray() {
        Object[] array = new Object[size()];
        int i = 0;

        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            array[i++] = iterator.next();
        }

        return array;
    }
    
    public T[] toArray(T[] array) {
        int i = 0;

        Iterator<T> iterator = iterator();
        while (iterator.hasNext()) {
            array[i++] = iterator.next();
        }

        return array;
    }
}
