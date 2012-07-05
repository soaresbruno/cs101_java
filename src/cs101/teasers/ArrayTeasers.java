package cs101.teasers;

import cs101.datastructures.collection.list.Vector;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: bruno
 * Date: 7/4/12
 * Time: 11:13 PM
 * To change this template use File | Settings | File Templates.
 */
public class ArrayTeasers {
    
    public static int findKthBiggestElement(Vector<Integer> array1, Vector<Integer> array2, int k) {
        Vector<Integer> biggest = new Vector<Integer>();
        fillBiggestElements(array1, biggest, k);
        fillBiggestElements(array2, biggest, k);
        return biggest.get(k - 1);
    }

    private static void fillBiggestElements(Vector<Integer> array, Vector<Integer> biggest, int k) {
        for (int i = 0; i < array.size(); i++) {
            int value = array.get(i);
            int j = -1;
            while (++j < biggest.size()) {
                if (value > biggest.get(j)) {
                    break;
                }
            }

            if (j < k) {
                biggest.insert(value, j);
                if (biggest.size() > k) {
                    biggest.removeLast();
                }
            }
        }
    }
    
    public static void main(String[] args) {
        Vector<Integer> array1 = new Vector<Integer>();
        array1.add(1);
        array1.add(5);
        array1.add(3);
        array1.add(4);

        Vector<Integer> array2 = new Vector<Integer>();
        array2.add(9);
        array2.add(2);
        array2.add(1);
        array2.add(4);
        array2.add(3);

        System.out.println(findKthBiggestElement(array1, array2, 4));
    }
    
}
