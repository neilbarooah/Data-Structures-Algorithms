/**
 * Your implementation of a DoublyLinkedList
 *
 * @author Neil Barooah
 * @version 1.0
 */
public class DoublyLinkedList<T> implements LinkedListInterface<T> {
    // Do not add new instance variables.
    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;
    private int size;

    @Override
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Please enter a valid index");
        }
        if (data == null) {
            throw new IllegalArgumentException("Please enter valid data type");
        }
        if (index == 0) {
            addToFront(data);
        } else if (index == size) {
            addToBack(data);
        } else {
            LinkedListNode<T> prevNode = head;
            for (int i = 0; i < index - 1; i++) {
                prevNode = prevNode.getNext();
            }
            LinkedListNode<T> nextNode = prevNode.getNext();
            LinkedListNode<T> newNode = new LinkedListNode<T>(data,
                    prevNode, nextNode);
            prevNode.setNext(newNode);
            nextNode.setPrevious(newNode);
            size++;
        }
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Please enter valid data");
        }
        LinkedListNode<T> newNode = new LinkedListNode<T>(data, null, null);
        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
        }
        size++;
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Please provide valid data");
        }
        LinkedListNode<T> newNode = new LinkedListNode<T>(data, null, null);
        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrevious(tail);
            tail = newNode;
        }
        size++;
    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Please enter a valid index");
        }
        if (index == 0) {
            return removeFromFront();
        } else if (index == size - 1) {
            return removeFromBack();
        } else {
            LinkedListNode<T> currentNode = head;
            for (int i = 0; i < index; i++) {
                currentNode = currentNode.getNext();
            }
            T data = currentNode.getData();
            LinkedListNode<T> prevNode = currentNode.getPrevious();
            LinkedListNode<T> nextNode = currentNode.getNext();
            prevNode.setNext(nextNode);
            nextNode.setPrevious(prevNode);
            size--;
            return data;
        }
    }

    @Override
    public T removeFromFront() {
        if (size == 0) {
            return null;
        } else if (size == 1) {
            T data = head.getData();
            head = null;
            tail = null;
            size--;
            return data;
        } else {
            T data = head.getData();
            LinkedListNode<T> newHead = head.getNext();
            newHead.setPrevious(null);
            head = newHead;
            size--;
            return data;
        }
    }

    @Override
    public T removeFromBack() {
        if (size == 0) {
            return null;
        } else if (size == 1) {
            T data = head.getData();
            head = null;
            tail = null;
            size--;
            return data;
        } else {
            T data = tail.getData();
            LinkedListNode<T> newTail = tail.getPrevious();
            newTail.setNext(null);
            tail = newTail;
            size--;
            return data;
        }
    }

    @Override
    public boolean removeAllOccurrences(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Please enter valid data");
        }
        if (size == 0) {
            return false;
        } else if (size == 1) {
            T headData = head.getData();
            if (headData.equals(data)) {
                head = null;
                tail = null;
                size--;
                return true;
            } else {
                return false;
            }
        } else {
            boolean status = false;
            LinkedListNode<T> currentNode = head;
            int originalSize = size;
            for (int i = 0; i < originalSize; i++) {
                if (currentNode.getData().equals(data)) {
                    if (currentNode.equals(head)) {
                        if (size == 1) {
                            head = null;
                            tail = null;
                            size--;
                            return true;
                        } else {
                            LinkedListNode<T> newHead = currentNode.getNext();
                            newHead.setPrevious(null);
                            head = newHead;
                            size--;
                            currentNode = head;
                        }
                    } else if (currentNode.equals(tail)) {
                        LinkedListNode<T> newTail = currentNode.getPrevious();
                        newTail.setNext(null);
                        tail = newTail;
                        size--;
                    } else {
                        LinkedListNode<T> prevNode = currentNode.getPrevious();
                        LinkedListNode<T> nextNode = currentNode.getNext();
                        prevNode.setNext(nextNode);
                        nextNode.setPrevious(prevNode);
                        size--;
                        currentNode = nextNode;
                    }
                    status = true;
                } else {
                    currentNode = currentNode.getNext();
                }
            }
            return status;
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Please enter valid index");
        }
        LinkedListNode<T> currentNode = head;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }
        return currentNode.getData();
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        if (size == 0) {
            return array;
        } else if (size == 1) {
            array[0] = head;
            return array;
        } else {
            LinkedListNode<T> currentNode = head;
            for (int i = 0; i < size; i++) {
                array[i] = currentNode.getData();
                currentNode = currentNode.getNext();
            }
            return array;
        }
    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    @Override
    public LinkedListNode<T> getTail() {
        // DO NOT MODIFY!
        return tail;
    }
}
