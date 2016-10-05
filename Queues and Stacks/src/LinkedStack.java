import java.util.NoSuchElementException;

/**
 * Your implementation of a linked stack.
 *
 * @author Neil Barooah
 * @version 1.0
 */
public class LinkedStack<T> implements StackInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private int size;

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public T pop() {
        if (size == 0) {
            throw new NoSuchElementException("Stack is empty");
        }
        if (size == 1) {
            T data = head.getData();
            head = null;
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
    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        }
        if (size == 0) {
            head = new LinkedNode<T>(data, null);
        } else {
            LinkedNode<T> newNode = new LinkedNode<T>(data, head);
            head = newNode;
        }
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the head of this stack.
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
}
