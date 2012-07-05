package cs101.datastructures.map;

/**
 * Created by IntelliJ IDEA.
 * User: bruno
 * Date: 6/30/12
 * Time: 12:37 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractMap<K, V> implements Map<K, V> {

    protected int size;
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }

}
