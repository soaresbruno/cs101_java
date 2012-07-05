package cs101.sorting;

import cs101.datastructures.collection.list.DoublyLinkedList;
import cs101.datastructures.collection.list.List;

/**
 * Created by IntelliJ IDEA.
 * User: bruno
 * Date: 7/1/12
 * Time: 1:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class MergeSort extends AbstractSort {

    public <T extends Comparable<? super T>> void sort(List<T> list, int start, int count) {
        List<Comparable[]> sublists = new DoublyLinkedList<Comparable[]> ();
        for (int i = 0; i < count; i++) {
            sublists.add(new Comparable[] { list.remove(start) });
        }
        
        while (sublists.size() > 1) {
            Comparable[] list1 = sublists.removeFirst();
            Comparable[] list2 = sublists.removeFirst();
            sublists.add(merge(list1, list2));
        }

        Comparable[] result = sublists.removeFirst();
        for (int i = 0; i < count; i++) {
            list.add((T) result[i]);
        }
    }

    private Comparable[] merge(Comparable[] list1, Comparable[] list2) {
        int i = 0, n = list1.length;
        int j = 0, m = list2.length;
        int k = 0;
        
        Comparable[] merged = new Comparable[n + m];
        
        while (i < n && j < m) {
            merged[k++] = list1[i].compareTo(list2[j]) <= 0 ? list1[i++] : list2[j++];
        }
        
        if (i < n) {
            System.arraycopy(list1, i, merged, k, n - i);
        }
        else if (j < m) {
            System.arraycopy(list2, j, merged, k, m - j);
        }

        return merged;
    }

}
