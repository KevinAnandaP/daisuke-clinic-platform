package datastructure;

import java.util.function.Consumer;

/**
 * Implementation of a Binary Search Tree
 */
public class BinarySearchTree<T extends Comparable<T>> {
    private static class TreeNode<E> {
        private E data;
        private TreeNode<E> left;
        private TreeNode<E> right;

        public TreeNode(E data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    private TreeNode<T> root;
    private int size;

    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Insert a new element into the tree
     */
    public void insert(T data) {
        root = insertRec(root, data);
        size++;
    }

    private TreeNode<T> insertRec(TreeNode<T> root, T data) {
        if (root == null) {
            return new TreeNode<>(data);
        }

        int compareResult = data.compareTo(root.data);
        if (compareResult < 0) {
            root.left = insertRec(root.left, data);
        } else if (compareResult > 0) {
            root.right = insertRec(root.right, data);
        }
        
        return root;
    }

    /**
     * Search for an element in the tree
     */
    public T search(T data) {
        return searchRec(root, data);
    }

    private T searchRec(TreeNode<T> root, T data) {
        if (root == null) {
            return null;
        }

        int compareResult = data.compareTo(root.data);
        if (compareResult == 0) {
            return root.data;
        }

        if (compareResult < 0) {
            return searchRec(root.left, data);
        } else {
            return searchRec(root.right, data);
        }
    }

    /**
     * Perform an in-order traversal of the tree
     */
    public void inOrderTraversal(Consumer<T> action) {
        inOrderRec(root, action);
    }

    private void inOrderRec(TreeNode<T> root, Consumer<T> action) {
        if (root != null) {
            inOrderRec(root.left, action);
            action.accept(root.data);
            inOrderRec(root.right, action);
        }
    }    /**
     * Get all elements in the tree in in-order sequence
     */
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        Object[] result = new Object[size];
        
        // Use a wrapper class to track the current index in the array
        class IndexWrapper {
            int index = 0;
        }
        
        IndexWrapper wrapper = new IndexWrapper();
        
        // Use in-order traversal to fill the array
        inOrderTraversal(data -> {
            result[wrapper.index++] = data;
        });
        
        return (T[]) result;
    }

    /**
     * Check if the tree is empty
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Get the size of the tree
     */
    public int size() {
        return size;
    }
}
