import java.util.NoSuchElementException;

/**
 * Your implementation of a linked queue.
 *
 * @author Neil Barooah
 * @version 1.0
 */
public class LinkedQueue<T> implements QueueInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private LinkedNode<T> tail;
    private int size;

    @Override
    public T dequeue() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (size == 1) {
            T data = head.getData();
            head = null;
            tail = null;
            size--;
            return data;
        } else {
            T data = head.getData();
            head = head.getNext();
            size--;
            return data;
        }
    }

    @Override
    public void enqueue(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        }
        if (size == 0) {
            head = new LinkedNode<T>(data, null);
            tail = head;
        } else if (size == 1) {
            tail = new LinkedNode<T>(data, null);
            head.setNext(tail);
        } else {
            LinkedNode<T> newNode = new LinkedNode<T>(data, null);
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the head of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the head node
     */
    public LinkedNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the tail of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the tail node
     */
    public LinkedNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }
}
