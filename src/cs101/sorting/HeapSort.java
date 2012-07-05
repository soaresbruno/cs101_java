package cs101.sorting;

import cs101.datastructures.collection.Iterator;
import cs101.datastructures.collection.list.List;
import cs101.datastructures.collection.tree.AVLTree;
import cs101.datastructures.collection.tree.BSTree;

/**
 * Created by IntelliJ IDEA.
 * User: bruno
 * Date: 7/2/12
 * Time: 10:59 AM
 * To change this template use File | Settings | File Templates.
 */
public class HeapSort extends AbstractSort {

    public <T extends Comparable<? super T>> void sort(List<T> list, int start, int count) {
        // First, create heap with items from list (removing them from list)
        BSTree<T> heap = new AVLTree<T>();
        for (int i = 0; i < count; i++) {
            heap.add(list.remove(start));
        }

        // Now iterate the heap and add items back to list
        Iterator<T> iterator = heap.iterator();
        int i = start;
        while (iterator.hasNext()) {
            list.insert(iterator.next(), i++);
        }
    }
}
