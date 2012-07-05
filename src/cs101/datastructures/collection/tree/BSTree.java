package cs101.datastructures.collection.tree;

import cs101.datastructures.collection.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: bruno
 * Date: 7/1/12
 * Time: 4:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class BSTree<T extends Comparable<? super T>> extends AbstractTree<T> {

    private long modifyCount;

    protected BSTreeNode findNode(T element) {
        BSTreeNode node = (BSTreeNode) root;
        while (node != null) {
            int comparison;
            if (element == null) {
                comparison = node.getValue() == null ? 0 : -1;
            }
            else {
                comparison = element.compareTo(node.getValue());
            }

            if (comparison == 0) {
                return node;
            }
            else {
                node = comparison < 0 ? node.getLeftChild() : node.getRightChild();
            }
        }

        return null; // not found
    }

    public void add(T element) {
        add(new BSTreeNode(element));
        size++;
        modifyCount++;
    }
    
    protected void add(BSTreeNode node) {
        if (root == null) {
            root = node;
        }
        else {
            BSTreeNode parent = (BSTreeNode) root;
            T value = node.getValue();

            while (true) {
                if (value == null || value.compareTo(parent.getValue()) < 0) {
                    if (parent.getLeftChild() == null) {
                        parent.setLeftChild(node);
                        break;
                    }
                    else {
                        parent = parent.getLeftChild();
                    }
                }
                else {
                    if (parent.getRightChild() == null) {
                        parent.setRightChild(node);
                        break;
                    }
                    else {
                        parent = parent.getRightChild();
                    }
                }
            }
        }    
    }

    public boolean remove(T element) {
        BSTreeNode node = findNode(element);
        // If element was not found, nothing to remove
        if (node == null) {
            return false;
        }
        else {
            remove(node);
            size--;
            modifyCount++;
            return true;
        }
    }
    
    protected void remove(BSTreeNode node) {
        // If node is a leaf, just remove it from its parent
        if (node.isLeaf()) {
            if (node == root) {
                root = null;
            }
            else {
                node.removeFromParent();
            }
        }
        // Otherwise, if node has one single child, move this child up the tree
        else if (node.getChildCount() == 1) {
            BSTreeNode child = node.getLeftChild();
            if (child == null) {
                child = node.getRightChild();
            }

            if (node == root) {
                root = child;
            }
            else {
                node.getParent().replaceChild(node, child);
            }
        }
        // Otherwise (node has both children), replace value with smallest child and remove child
        else {
            BSTreeNode smallest = node.getRightChild().findSmallest();
            node.setValue(smallest.getValue());
            remove(smallest);
        }
    }

    public void removeAll() {
        root = null;
        size = 0;
        modifyCount++;
    }

    public void rebalance() {
        boolean rebalanced = false;
        do {
            Iterator<BSTreeNode> nodeIterator = nodeIterator();
            while (nodeIterator.hasNext()) {
                if (rebalanced = rebalance(nodeIterator.next())) {
                    break;
                }
            }
        }
        while (rebalanced);
    }

    protected boolean rebalance(BSTreeNode node) {
        int balance = node.getLeftHeight() - node.getRightHeight();

        if (Math.abs(balance) > 1) {
            // If right sub-tree outweighs left sub-tree
            if (balance < -1) {
                BSTreeNode right = node.getRightChild();
                // If right child is left unbalanced, rotate it first
                if (right.getLeftHeight() > right.getRightHeight()) {
                    rotate(right, 1); // right rotation to fix child balance
                }
                rotate(node, -1); // left rotation to fix balance
            }
            // Otherwise, if left sub-tree outweighs right sub-tree
            else if (balance > 1) {
                BSTreeNode left = node.getLeftChild();
                // If left child is right unbalanced, rotate it first
                if (left.getLeftHeight() < left.getRightHeight()) {
                    rotate(left, -1); // left rotation to fix child balance
                }
                rotate(node, 1); // right rotation to fix balance
            }

            modifyCount++;
            return true;
        }
        else {
            return false;
        }
    }

    protected void rotate(BSTreeNode pivot, int direction) {
        BSTreeNode child = direction < 0 ? pivot.getRightChild() : pivot.getLeftChild();

        // If root of the sub-tree is the tree root, make child the new tree root
        if (pivot == root) {
            child.removeFromParent();
            root = child;
        }
        // Otherwise, make child a child of pivot's parent
        else {
            pivot.getParent().replaceChild(pivot, child);
        }

        if (direction < 0) {
            pivot.setRightChild(child.getLeftChild());
            child.setLeftChild(pivot);
        }
        else {
            pivot.setLeftChild(child.getRightChild());
            child.setRightChild(pivot);
        }
    }

    public boolean contains(T element) {
        return findNode(element) != null;
    }

    public boolean isEmpty() {
        return root != null;
    }
    
    private Iterator<BSTreeNode> nodeIterator() {
        return new Iterator<BSTreeNode>() {
        
            private BSTreeNode node = root == null ? null : ((BSTreeNode) root).findSmallest();
            private long initialModifyCount = modifyCount;
            
            public boolean hasNext() {
                return node != null;
            }
            
            public BSTreeNode next() {
                if (initialModifyCount != modifyCount) {
                    throw new IllegalStateException("Iterator is invalid; collection has been modified.");
                }

                // Store current node
                BSTreeNode currNode = node;

                // Move to next node
                if (node != null) {
                    if (node.getRightChild() != null) {
                        node = node.getRightChild().findSmallest();
                    }
                    else {
                        BSTreeNode parent = (BSTreeNode) node.getParent();
                        while (parent != null && node.equals(parent.getRightChild())) {
                            node = parent;
                            parent = (BSTreeNode) node.getParent();
                        }

                        node = parent;
                    }
                }

                return currNode;
            }
        };    
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private Iterator<BSTreeNode> nodeIterator = nodeIterator();
            
            public boolean hasNext() {
                return nodeIterator.hasNext();
            }
            
            public T next() {
                BSTreeNode node = nodeIterator.next();
                return node != null ? node.getValue() : null;
            }
        };
    }

    class BSTreeNode extends TreeNode {

        private BSTreeNode leftChild;
        private BSTreeNode rightChild;
        private int leftHeight;
        private int rightHeight;

        public BSTreeNode(T value) {
            super(value);
        }

        public BSTreeNode getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(BSTreeNode node) {
            if (leftChild != null) {
                leftChild.parent = null;
            }

            if (node != null) {
                if (node.parent != null) {
                    node.removeFromParent();
                }
                node.parent = this;
                leftChild = node;
                setLeftHeight(Math.max(node.getLeftHeight(), node.getRightHeight()) + 1);
            }
            else {
                leftChild = null;
                setLeftHeight(0);
            }
        }

        public BSTreeNode getRightChild() {
            return rightChild;
        }

        public void setRightChild(BSTreeNode node) {
            if (rightChild != null) {
                rightChild.parent = null;
            }

            if (node != null) {
                if (node.parent != null) {
                    node.removeFromParent();
                }
                node.parent = this;
                rightChild = node;
                setRightHeight(Math.max(node.getLeftHeight(), node.getRightHeight()) + 1);
            }
            else {
                rightChild = null;
                setRightHeight(0);
            }
        }
        
        public int getLeftHeight() {
            return leftHeight;
        }
        
        public void setLeftHeight(int leftHeight) {
            if (leftHeight != this.leftHeight) {
                this.leftHeight = leftHeight;
                leftOrRightHeightChanged();
            }
        }
        
        public int getRightHeight() {
            return rightHeight;
        }

        public void setRightHeight(int rightHeight) {
            if (rightHeight != this.rightHeight) {
                this.rightHeight = rightHeight;
                leftOrRightHeightChanged();
            }
        }

        private void leftOrRightHeightChanged() {
            if (parent != null) {
                BSTreeNode parentNode = (BSTreeNode) parent;
                int parentHeight = Math.max(leftHeight, rightHeight) + 1;

                if (this == parentNode.leftChild) {
                    parentNode.setLeftHeight(parentHeight);
                }
                else if (this == parentNode.rightChild) {
                    parentNode.setRightHeight(parentHeight);
                }
            }
        }

        public boolean isLeaf() {
            return leftChild == null && rightChild == null;
        }

        public int getChildCount() {
            int count = 0;
            if (leftChild != null) {
                count++;
            }
            if (rightChild != null) {
                count++;
            }
            return count;
        }

        public boolean removeChild(TreeNode node) {
            if (node == leftChild) {
                setLeftChild(null);
                return true;
            }
            else if (node == rightChild) {
                setRightChild(null);
                return true;
            }

            return false;
        }

        public boolean replaceChild(TreeNode node, TreeNode newNode) {
            if (node == leftChild) {
                setLeftChild((BSTreeNode) newNode);
                return true;
            }
            else if (node == rightChild) {
                setRightChild((BSTreeNode) newNode);
                return true;
            }
            else {
                return false;
            }
        }

        public BSTreeNode findSmallest() {
            BSTreeNode smallest = this;
            while (smallest.leftChild != null) {
                smallest = smallest.leftChild;
            }

            return smallest;
        }

    }

}
