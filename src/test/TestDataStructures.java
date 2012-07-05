import cs101.datastructures.collection.list.DoublyLinkedList;
import cs101.datastructures.collection.set.HashSet;
import cs101.datastructures.map.Hashtable;
import cs101.datastructures.collection.list.LinkedList;
import cs101.datastructures.collection.list.Vector;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: bruno
 * Date: 6/30/12
 * Time: 10:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class TestDataStructures {

    @Test
    public void testVector() {
        TestList.testList(new Vector<Integer>());
    }

    @Test
    public void testLinkedList() {
        TestList.testList(new LinkedList<Integer>());
    }

    @Test
    public void testDoubleLinkedList() {
        TestList.testList(new DoublyLinkedList<Integer>());
    }

    @Test
    public void testHashSet() {
        TestSet.testSet(new HashSet<Integer>());
    }

    @Test
    public void testHashtable() {
        TestMap.testMap(new Hashtable<Integer, Integer>());
    }

}
