package datastructure;

/**
 * Generic implementation of a Queue (FIFO) using a linked list
 */
public class Queue<T> {
    private Node<T> front;  // For dequeue operations
    private Node<T> rear;   // For enqueue operations
    private int size;

    public Queue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    /**
     * Add an element to the end of the queue
     */
    public void enqueue(T data) {
        Node<T> newNode = new Node<>(data);
        
        if (isEmpty()) {
            front = newNode;
        } else {
            rear.setNext(newNode);
        }
        
        rear = newNode;
        size++;
    }

    /**
     * Remove and return the element at the front of the queue
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        
        T data = front.getData();
        front = front.getNext();
        
        if (front == null) {
            rear = null;  // If the queue becomes empty
        }
        
        size--;
        return data;
    }

    /**
     * Return the element at the front of the queue without removing it
     */
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return front.getData();
    }

    /**
     * Check if the queue is empty
     */
    public boolean isEmpty() {
        return front == null;
    }

    /**
     * Get the size of the queue
     */
    public int size() {
        return size;
    }

    /**
     * Get all elements in the queue as an array (front to rear)
     */
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        Object[] result = new Object[size];
        Node<T> current = front;
        int index = 0;
        while (current != null) {
            result[index++] = current.getData();
            current = current.getNext();
        }
        return (T[]) result;
    }
}
