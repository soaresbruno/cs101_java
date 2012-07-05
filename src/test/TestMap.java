import cs101.datastructures.map.Map;
import cs101.datastructures.collection.list.Vector;

import static org.junit.Assert.*;

/**
 * Created by IntelliJ IDEA.
 * User: bruno
 * Date: 6/30/12
 * Time: 2:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestMap extends TestCommon {

    public static void testSetAndGet(Map<Integer, Integer> map, int count) {
        Vector<Integer> keys = new Vector<Integer>(count);
        
        for (int i = 0; i < count; i++) {
            int size = map.size();
            Integer key = randomUnique(map.keys());

            Integer value = random();
            map.set(key, value);
            assertEquals(size + 1, map.size());
            assertTrue(map.contains(key));
            assertTrue(map.containsValue(value));
            assertEquals(value, map.get(key));
            
            value = random();
            map.set(key, value);
            assertEquals(size + 1, map.size());
            assertEquals(value, map.get(key));

            keys.add(key);
        }

        for (int i = 0; i < count; i++) {
            map.remove(keys.get(i));
        }
    }
    
    public static void testRemove(Map<Integer, Integer> map, int count) {
        Vector<Integer> keys = new Vector<Integer>(count);
                
        for (int i = 0; i < count; i++) {
            Integer key = randomUnique(map.keys());
            Integer value = random();
            map.set(key, value);
            
            keys.add(key);
        }
        
        for (int i = 0; i < count; i++) {
            int size = map.size();
            Integer key = keys.get(i);
            Integer value = map.remove(key);

            assertNotNull(value);
            assertFalse(map.contains(key));
            assertFalse(map.containsValue(value));
            assertEquals(size - 1, map.size());
        }
    }
    
    public static void testMap(Map<Integer, Integer> map) {
        int size = map.size();
        
        testSetAndGet(map, 100);
        assertEquals(size, map.size());
        
        testRemove(map, 100);
        assertEquals(size, map.size());
    }

}
