package cs101.datastructures.collection.tree;

/**
 * Created by IntelliJ IDEA.
 * User: bruno
 * Date: 7/2/12
 * Time: 11:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class AVLTree<T extends Comparable<? super T>> extends BSTree<T> {

    protected void add(BSTreeNode node) {
        super.add(node);
        assertBalanced((BSTreeNode) node.getParent());
    }

    protected  void remove(BSTreeNode node) {
        BSTreeNode parent = (BSTreeNode) node.getParent();
        super.remove(node);
        assertBalanced(parent);
    }

    private void assertBalanced(BSTreeNode node) {
        while (node != null) {
            if (rebalance(node)) {
                break; // found and fixed unbalanced sub-tree
            }
            node = (BSTreeNode) node.getParent();
        }
    }

    public void rebalance() {
        // Do nothing, since AVL trees are always balanced
    }

}
