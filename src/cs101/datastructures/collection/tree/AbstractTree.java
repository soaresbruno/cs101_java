package cs101.datastructures.collection.tree;

import cs101.datastructures.collection.AbstractCollection;
import cs101.datastructures.collection.list.DoublyLinkedList;
import cs101.datastructures.collection.list.List;
import cs101.datastructures.collection.list.Vector;

/**
 * Created by IntelliJ IDEA.
 * User: bruno
 * Date: 7/1/12
 * Time: 4:41 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractTree<T> extends AbstractCollection<T> implements Tree<T> {

    protected TreeNode root;
    protected int size;

    public int size() {
        return size;
    }

    abstract class TreeNode {

        protected T value;
        protected TreeNode parent;

        public TreeNode(T value) {
            this.value = value;
        }
        
        public T getValue() {
            return value;
        }
        
        public void setValue(T value) {
            this.value = value;
        }
        
        public TreeNode getParent() {
            return parent;
        }

        public void removeFromParent() {
            if (parent != null) {
                parent.removeChild(this);
            }
        }

        public boolean isLeaf() {
            return getChildCount() == 0;
        }

        public abstract int getChildCount();
        public abstract boolean removeChild(TreeNode node);
        public abstract boolean replaceChild(TreeNode node, TreeNode newNode);

    }

}
