import java.util.NoSuchElementException;

/**
 * Your implementation of a min heap.
 *
 * @author Neil Barooah
 * @version 1.0
 */
public class MinHeap<T extends Comparable<? super T>>
    implements HeapInterface<T> {

    private T[] backingArray;
    private int size;
    // Do not add any more instance variables. Do not change the declaration
    // of the instance variables above.

    /**
     * Creates a Heap with an initial size of {@code STARTING_SIZE} for the
     * backing array.
     *
     * Use the constant field in the interface. Do not use magic numbers!
     */
    public MinHeap() {
        backingArray = (T[]) new Comparable[STARTING_SIZE];
        size = 0;
    }

    @Override
    public void add(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Item added cannot be null");
        }
        if (size == backingArray.length - 1) {
            int resetCapacity = (backingArray.length * 3) / 2;
            T[] tempArray = (T[]) new Comparable[resetCapacity];
            for (int i = 0; i < backingArray.length; i++) {
                tempArray[i] = backingArray[i];
            }
            backingArray = tempArray;
        }
        backingArray[size + 1] = item;
        size++;
        if (size > 1) {
            upheap();
        }
    }

    /**
     * method to restore order property of min heap after adding an item
     */
    private void upheap() {
        int currentIndex = size;
        int parentIndex = currentIndex / 2;
        while (parentIndex >= 1 && backingArray[currentIndex].
                compareTo(backingArray[parentIndex]) < 0) {
            T currentData = backingArray[currentIndex];
            T parentData = backingArray[parentIndex];
            backingArray[currentIndex] = parentData;
            backingArray[parentIndex] = currentData;
            currentIndex = parentIndex;
            parentIndex = currentIndex / 2;
        }
    }

    @Override
    public T remove() {
        if (size == 0) {
            throw new NoSuchElementException("The heap is empty");
        }
        T data = backingArray[1];
        if (size == 1) {
            backingArray[1] = null;
            size--;
            return data;
        } else {
            backingArray[1] = backingArray[size];
            backingArray[size] = null;
            size--;
            if (size == 1) {
                return data;
            } else if (size == 2) {
                T root = backingArray[1];
                T leftChild = backingArray[2];
                if (root.compareTo(leftChild) > 0) {
                    backingArray[1] = leftChild;
                    backingArray[2] = root;
                }
            } else {
                downheap(data);
            }
        }
        return data;
    }

    /**
     * method to restore order property of min heap after removing root
     * @param data that's to be removed from heap
     * @return removed data from heap
     */
    private T downheap(T data) {
        int currentIndex = 1;
        int leftChildIndex = 2 * currentIndex;
        int rightChildIndex = 2 * currentIndex + 1;
        int childIndex = getIndex(leftChildIndex, rightChildIndex);
        if (childIndex > size) {
            return data;
        }
        while (backingArray[currentIndex].
                compareTo(backingArray[childIndex]) > 0) {
            T currentData = backingArray[currentIndex];
            T childData = backingArray[childIndex];
            backingArray[currentIndex] = childData;
            backingArray[childIndex] = currentData;
            currentIndex = childIndex;
            leftChildIndex = 2 * currentIndex;
            rightChildIndex = 2 * currentIndex + 1;
            childIndex = getIndex(leftChildIndex, rightChildIndex);
            if (childIndex > size) {
                return data;
            }
        }
        return data;
    }

    /**
     * method to obtain minimum of 2 children of a node
     * @param leftChildIndex index of left child of current node
     * @param rightChildIndex index of left child of current node
     * @return index of minimum smallest element
     */
    private int getIndex(int leftChildIndex, int rightChildIndex) {
        if (leftChildIndex > size) {
            return size + 10;
        } else if (leftChildIndex == size && rightChildIndex == size + 1) {
            return leftChildIndex;
        } else {
            return backingArray[leftChildIndex].
                    compareTo(backingArray[rightChildIndex])
                    < 0 ? leftChildIndex : rightChildIndex;
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
        backingArray = (T[]) new Comparable[STARTING_SIZE];
        size = 0;
    }

    @Override
    public Comparable[] getBackingArray() {
        // DO NOT CHANGE THIS METHOD!
        return backingArray;
    }

}
