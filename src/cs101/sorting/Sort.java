package cs101.sorting;

import cs101.datastructures.collection.list.List;

/**
 * Created by IntelliJ IDEA.
 * User: bruno
 * Date: 7/1/12
 * Time: 10:47 AM
 * To change this template use File | Settings | File Templates.
 */
public interface Sort {

    public <T extends Comparable<? super T>> void sort(List<T> list);

    public <T extends Comparable<? super T>> void sort(List<T> list, int start, int count);

}
