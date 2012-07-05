package cs101.sorting;

import cs101.datastructures.collection.list.List;

/**
 * Created by IntelliJ IDEA.
 * User: bruno
 * Date: 7/1/12
 * Time: 12:24 PM
 *
 * Insertion sort is a simple sorting algorithm that is relatively efficient for small lists and mostly sorted lists,
 * and often is used as part of more sophisticated algorithms. It works by taking elements from the list one by one and
 * inserting them in their correct position into a new sorted list. In arrays, the new list and the remaining elements
 * can share the array's space, but insertion is expensive, requiring shifting all following elements over by one. Shell
 * sort (see below) is a variant of insertion sort that is more efficient for larger lists.
 */
public class InsertionSort extends AbstractSort {

    public <T extends Comparable<? super T>> void sort(List<T> list, int start, int count) {
        for (int i = 1; i < count; i++) {
            T element = list.get(i);
            
            int j;
            for (j = 0; j < i; j++) {
                T temp = list.get(j);
                if (element.compareTo(temp) <= 0) {
                    break;
                }
            }
            
            if (j != i) {
                list.insert(list.remove(i), j);
            }
        }
    }

}
