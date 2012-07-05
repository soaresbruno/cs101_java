package cs101.datastructures.collection;

/**
 * Created by IntelliJ IDEA.
 * User: bruno
 * Date: 7/2/12
 * Time: 10:12 AM
 * To change this template use File | Settings | File Templates.
 */
public interface Iterator<T> {

    public boolean hasNext();
    public T next();
    
}
