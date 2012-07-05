import cs101.datastructures.collection.list.List;
import cs101.datastructures.collection.list.Vector;
import cs101.sorting.*;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: bruno
 * Date: 7/1/12
 * Time: 12:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestSort extends TestCommon {

    public static void testSort(List<Integer> list, int count, Sort sort) {
        int start = list.size();
        
        for (int i = count - 1; i >= 0; i--) {
            list.add(i);
        }
        
        sort.sort(list, start, count);
        for (int i = 1; i < count; i++) {
            Integer e1 = list.get(start + i - 1);
            Integer e2 = list.get(start + i);
            assertTrue(e1 <= e2);
        }
        
        for (int i = count - 1; i >= 0; i--) {
            list.removeLast();
        }
    }

    @Test
    public void testBubbleSort() {
        Vector<Integer> list = new Vector<Integer>();
        testSort(list, 100, new BubbleSort());
    }
    
    @Test
    public void testSelectionSort() {
        Vector<Integer> list = new Vector<Integer>();
        testSort(list, 100, new SelectionSort());
    }

    @Test
    public void testInsertionSort() {
        Vector<Integer> list = new Vector<Integer>();
        testSort(list, 100, new InsertionSort());
    }
    
    @Test
    public void testMergeSort() {
        Vector<Integer> list = new Vector<Integer>();
        testSort(list, 100, new MergeSort());
    }

    @Test
    public void testHeapSort() {
        Vector<Integer> list = new Vector<Integer>();
        testSort(list, 100, new HeapSort());
    }

}
