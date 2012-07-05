package cs101.sorting;

import cs101.datastructures.collection.list.List;

/**
 * Created by IntelliJ IDEA.
 * User: bruno
 * Date: 7/1/12
 * Time: 12:14 PM
 *
 * Selection sort is an in-place comparison sort. It has O(n2) complexity, making it inefficient on large lists, and
 * generally performs worse than the similar insertion sort. Selection sort is noted for its simplicity, and also has
 * performance advantages over more complicated algorithms in certain situations. The algorithm finds the minimum value,
 * swaps it with the value in the first position, and repeats these steps for the remainder of the list. It does no more
 * than n swaps, and thus is useful where swapping is very expensive.
 */
public class SelectionSort extends AbstractSort {

    public <T extends Comparable<? super T>> void sort(List<T> list, int start, int count) {
        for (int i = 0; i < count - 1; i++) {
            T minElement = list.get(i);
            int minIndex = i;
            
            for (int j = i + 1; j < count; j++) {
                T temp = list.get(j);
                if (temp.compareTo(minElement) < 0) {
                    minElement = temp;
                    minIndex = j;
                }
            }
            
            if (minIndex != i) {
                list.replace(list.replace(minElement, i), minIndex);
            }
        }
    }
    
}
