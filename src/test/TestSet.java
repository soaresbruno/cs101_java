import cs101.datastructures.collection.list.List;
import cs101.datastructures.collection.set.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by IntelliJ IDEA.
 * User: bruno
 * Date: 7/4/12
 * Time: 4:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestSet extends TestCommon {

    public static void testAdd(Set<Integer> set, int count) {
        Integer[] added = new Integer[count];

        for (int i = 0; i < count; i++) {
            int size = set.size();
            Integer element = randomUnique(set);

            set.add(element);
            assertEquals(size + 1, set.size());
            assertTrue(set.contains(element));

            added[i] = element;
        }

        for (int i = 0; i < count; i++) {
            set.remove(added[i]);
        }
    }
    
    public static void testRemove(Set<Integer> set, int count) {
        Integer[] added = new Integer[count];

        for (int i = 0; i < count; i++) {
            Integer element = randomUnique(set);
            set.add(element);
            added[i] = element;
        }

        for (int i = 0; i < count; i++) {
            int size = set.size();
            Integer element = added[i];
            
            assertTrue(set.remove(element));
            assertEquals(size - 1, set.size());
            assertFalse(set.contains(element));
        }
    }

    public static void testSet(Set<Integer> set) {
        int size = set.size();

        testAdd(set, 100);
        assertEquals(size, set.size());

        testRemove(set, 100);
        assertEquals(size, set.size());
    }

}
