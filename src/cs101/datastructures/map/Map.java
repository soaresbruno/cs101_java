package cs101.datastructures.map;

import cs101.datastructures.collection.list.List;

/**
 * Created by IntelliJ IDEA.
 * User: bruno
 * Date: 6/30/12
 * Time: 12:32 PM
 * To change this template use File | Settings | File Templates.
 */
public interface Map<K,V> {

    public void set(K key, V value);
    public V get(K key);
    public V remove(K key);
    public void removeAll();
    public boolean contains(K key);
    public boolean containsValue(V value);
    public boolean isEmpty();
    public int size();
    public List<K> keys();
    public List<V> values();

}
