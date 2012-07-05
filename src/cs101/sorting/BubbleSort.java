package cs101.sorting;

import cs101.datastructures.collection.list.List;

/**
 * Created by IntelliJ IDEA.
 * User: bruno
 * Date: 7/1/12
 * Time: 10:48 AM
 *
 * Bubble sort is a simple sorting algorithm. The algorithm starts at the beginning of the data set. It compares the
 * first two elements, and if the first is greater than the second, it swaps them. It continues doing this for each
 * pair of adjacent elements to the end of the data set. It then starts again with the first two elements, repeating
 * until no swaps have occurred on the last pass. This algorithm's average and worst case performance is O(n2), so it
 * is rarely used to sort large, unordered, data sets. Bubble sort can be used to sort a small number of items (where
 * its inefficiency is not a high penalty). Bubble sort may also be efficiently used on a list that is already sorted
 * except for a very small number of elements that are not significantly out of place. For example, if one element is
 * out of place by one position, then bubble sort will take only 2n time.
 */
public class BubbleSort extends AbstractSort {

    public <T extends Comparable<? super T>> void sort(List<T> list, int start, int count) {
        boolean swapped;
        int i;
        
        do {
            swapped = false;
            i = 0;

            while (++i < count) {
                T e1 = list.get(start + i - 1);
                T e2 = list.get(start + i);

                // If e1 is bigger than e2, swap elements
                if (e1.compareTo(e2) > 0) {
                    list.replace(e2, start + i - 1);
                    list.replace(e1, start + i);

                    swapped = true;
                }
            }
        }
        while (swapped);
    }

}
