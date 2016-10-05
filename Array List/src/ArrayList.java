/**
 * Your implementation of an ArrayList.
 *
 * @author Neil Barooah
 * @version 1
 */
public class ArrayList<T> implements ArrayListInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new ArrayList.
     *
     * You may add statements to this method.
     */
    public ArrayList() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        if (data == null) {
            throw new IllegalArgumentException();
        }
        int currentCapacity = backingArray.length;
        if (currentCapacity == size) {
            T[] temp = (T[]) new Object[currentCapacity * 2];
            for (int i = 0; i < index; i++) {
                temp[i] = backingArray[i];
            }
            temp[index] = data;
            for (int i = index; i < currentCapacity; i++) {
                temp[i + 1] = backingArray[i];
            }
            backingArray = temp;
            size++;
        } else {
            if (index == size) {
                backingArray[index] = data;
                size++;
            } else {
                T[] tempArray = (T[]) new Object[currentCapacity];
                for (int i = 0; i < index; i++) {
                    tempArray[i] = backingArray[i];
                }
                tempArray[index] = data;
                for (int i = index; i < currentCapacity - 1; i++) {
                    tempArray[i + 1] = backingArray[i];
                }
                size++;
                backingArray = tempArray;
            }
        }
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Please use valid data type");
        }
        addAtIndex(0, data);
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Please use valid data type");
        }
        int currentCapacity = backingArray.length;
        if (currentCapacity == size) {
            T[] temp = (T[]) new Object[currentCapacity * 2];
            for (int i = 0; i < currentCapacity; i++) {
                temp[i] = backingArray[i];
            }
            backingArray = temp;
        }
        backingArray[size] = data;
        size++;
    }

    @Override
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Please enter valid index");
        }
        if (index == size - 1) {
            T data = backingArray[index];
            backingArray[index] = null;
            size--;
            return data;
        } else {
            T data = backingArray[index];
            for (int i = index; i < backingArray.length - 1; i++)  {
                backingArray[i] = backingArray[i + 1];
            }
            backingArray[backingArray.length - 1] = null;
            size--;
            return data;
        }

    }

    @Override
    public T removeFromFront() {
        if (size == 0) {
            return null;
        }
        return removeAtIndex(0);
    }

    @Override
    public T removeFromBack() {
        if (size == 0) {
            return null;
        }
        T data = backingArray[size - 1];
        backingArray[size - 1] = null;
        size--;
        return data;

    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("You cannot access an "
                   + "index that is out of range");
        }
        return backingArray[index];
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
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public Object[] getBackingArray() {
        // DO NOT MODIFY.
        return backingArray;
    }
}