import cs101.datastructures.collection.Collection;
import cs101.datastructures.collection.list.List;

/**
 * Created by IntelliJ IDEA.
 * User: bruno
 * Date: 6/30/12
 * Time: 2:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestCommon {

    public static int random() {
        return random(0, Integer.MAX_VALUE);
    }

    public static int randomUnique(Collection<Integer> list) {
        return randomUnique(0, Integer.MAX_VALUE, list);
    }

    public static int random(int min, int max) {
        return min + (int) (Math.random() * max);
    }

    public static int randomUnique(int min, int max, Collection<Integer> list) {
        while (true) {
            int random = random(min, max);
            if (!list.contains(random)) {
                return random;
            }
        }
    }

}
