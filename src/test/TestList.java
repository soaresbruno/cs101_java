import cs101.datastructures.collection.list.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by IntelliJ IDEA.
 * User: bruno
 * Date: 6/30/12
 * Time: 10:11 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestList extends TestCommon {

    public static void testAdd(List<Integer> list, int count) {
        Integer[] added = new Integer[count];

        for (int i = 0; i < count; i++) {
            int size = list.size();
            Integer element = randomUnique(list);
            
            list.add(element);
            assertEquals(size + 1, list.size());
            assertEquals(element, list.last());

            added[i] = element;
        }

        for (int i = 0; i < count; i++) {
            list.removeLast();
        }
    }

    public static void testInsert(List<Integer> list, int count) {
        Integer[] inserted = new Integer[count];

        for (int i = 0; i < count; i++) {
            int size = list.size();
            int index = random(0, size + 1);
            Integer element = randomUnique(list);

            assertTrue(index >= 0 && index <= size);
            list.insert(element, index);
            assertEquals(size + 1, list.size());
            assertEquals(element, list.get(index));

            inserted[i] = element;
        }

        for (int i = 0; i < count; i++) {
            list.remove(inserted[i]);
        }
    }
    
    public static void testRemove(List<Integer> list, int count) {
        Integer[] inserted = new Integer[count];

        for (int i = 0; i < count; i++) {
            int size = list.size();
            int index = random(0, size + 1);
            Integer element = randomUnique(list);

            assertTrue(index >= 0 && index <= size);
            list.insert(element, index);
            inserted[i] = element;
        }

        for (int i = 0; i < count; i++) {
            int size = list.size();
            Integer element = inserted[i];
            
            list.remove(element);
            assertEquals(size - 1, list.size());
            assertFalse(list.contains(element));
        }
    }
    
    public static void testList(List<Integer> list) {
        int size = list.size();

        testAdd(list, 100);
        assertEquals(size, list.size());

        testInsert(list, 100);
        assertEquals(size, list.size());

        testRemove(list, 100);
        assertEquals(size, list.size());
    }

}
