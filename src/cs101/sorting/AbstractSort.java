package cs101.sorting;

import cs101.datastructures.collection.list.List;

/**
 * Created by IntelliJ IDEA.
 * User: bruno
 * Date: 7/1/12
 * Time: 12:04 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractSort implements Sort {
    
    public <T extends Comparable<? super T>> void sort(List<T> list) {
        sort(list, 0, list.size());
    }
    
}
