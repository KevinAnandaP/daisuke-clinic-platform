package datastructure;

import java.util.function.Predicate;

/**
 * Generic implementation of a Singly Linked List
 */
public class LinkedList<T> {
    private Node<T> head;
    private int size;

    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * Add an element to the end of the list
     */
    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newNode);
        }
        size++;
    }

    /**
     * Remove the first occurrence of an element that matches the given predicate
     */
    public boolean remove(Predicate<T> predicate) {
        if (head == null) {
            return false;
        }

        if (predicate.test(head.getData())) {
            head = head.getNext();
            size--;
            return true;
        }

        Node<T> current = head;
        while (current.getNext() != null) {
            if (predicate.test(current.getNext().getData())) {
                current.setNext(current.getNext().getNext());
                size--;
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    /**
     * Find the first element that matches the given predicate
     */
    public T find(Predicate<T> predicate) {
        Node<T> current = head;
        while (current != null) {
            if (predicate.test(current.getData())) {
                return current.getData();
            }
            current = current.getNext();
        }
        return null;
    }

    /**
     * Get all elements in the list
     */
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        Object[] result = new Object[size];
        Node<T> current = head;
        int index = 0;
        while (current != null) {
            result[index++] = current.getData();
            current = current.getNext();
        }
        return (T[]) result;
    }

    /**
     * Check if the list is empty
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Get the size of the list
     */
    public int size() {
        return size;
    }
}
