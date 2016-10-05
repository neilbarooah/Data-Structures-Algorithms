import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;
/**
 * Your implementation of a skip list.
 * 
 * @author Neil Barooah
 * @version 1.0
 */
public class SkipList<T extends Comparable<? super T>>
    implements SkipListInterface<T> {
    // Do not add any additional instance variables
    private CoinFlipper coinFlipper;
    private int size;
    private SkipListNode<T> head;

    /**
     * Constructs a SkipList object that stores data in ascending order.
     * When an item is inserted, the flipper is called until it returns a tails.
     * If, for an item, the flipper returns n heads, the corresponding node has
     * n + 1 levels.
     *
     * @param coinFlipper the source of randomness
     */
    public SkipList(CoinFlipper coinFlipper) {
        this.coinFlipper = coinFlipper;
        head = new SkipListNode<T>(null, 1, null, null, null, null);
        size = 0;
    }

    @Override
    public T first() {
        T first;
        if (size == 0) {
            throw new NoSuchElementException("Skip list is empty.");
        } else {
            SkipListNode<T> current = head;
            while (current.getDown() != null) {
                current = current.getDown();
            }
            first = current.getNext().getData();
        }
        return first;
    }

    @Override
    public T last() {
        if (size == 0) {
            throw new java.util.NoSuchElementException("Skip list is empty");
        }
        return lastRecursive(head);
    }

    /**
     * helper method to obtain last element of list
     * @param current current node of list
     * @return last element of list
     */
    private T lastRecursive(SkipListNode<T> current) {
        SkipListNode<T> pointer = current;
        while (pointer.getNext() != null) {
            pointer = pointer.getNext();
        }
        if (pointer.getDown() != null) {
            return lastRecursive(pointer.getDown());
        } else {
            return pointer.getData();
        }
    }

    @Override
    public void put(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        }
        int putLevel = 1;
        while (coinFlipper.flipCoin() == CoinFlipper.Coin.HEADS) {
            putLevel++;
        }
        while (putLevel >= head.getLevel()) {  // correct number of levels
            SkipListNode<T> newHead = new SkipListNode<T>(null,
                    head.getLevel() + 1, null, null, null, head);
            head.setUp(newHead);
            head = newHead;
        }
        SkipListNode<T> current = head;
        while (putLevel < current.getLevel()) {
            current = current.getDown();       // start from the right level
        }
        SkipListNode<T> up = null;
        int level = current.getLevel();
        while (level != 0) {
            while (current.getNext() != null && current.getNext().getData().
                    compareTo(data) < 0) {
                current = current.getNext();
            }
            SkipListNode<T> nextNode;
            if (current.getNext() != null) {
                nextNode = current.getNext();
            } else {
                nextNode = null;
            }
            SkipListNode<T> downNode;
            if (current.getDown() != null) {
                downNode = current.getDown();
            } else {
                downNode = null;
            }
            SkipListNode<T> newNode = new SkipListNode<T>(data,
                    current.getLevel(), current, nextNode, up, downNode);
            current.setNext(newNode);
            if (nextNode != null) {
                nextNode.setPrev(newNode);
            }
            if (up != null) {
                up.setDown(newNode);
                newNode.setUp(up);
            }
            up = newNode;
            current = current.getDown();
            level--;
        }
        size++;
    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data is null");
        }
        if (size == 0) {
            throw new NoSuchElementException("The data isn't in skip list.");
        }
        SkipListNode<T> current = head;
        while (current != null) {
            if (current.getNext() == null
                    || current.getNext().getData().compareTo(data) > 0) {
                current = current.getDown();
            } else if (current.getNext().getData().compareTo(data) < 0) {
                current = current.getNext();
            } else if (current.getNext().getData().compareTo(data) == 0) {
                T answer = current.getNext().getData();
                SkipListNode<T> removeNode = current.getNext();
                SkipListNode<T> nextNode = null;
                if (removeNode.getNext() != null) {
                    nextNode = removeNode.getNext();
                }
                current.setNext(nextNode);
                if (nextNode != null) {
                    nextNode.setPrev(current);
                }
                removeNode.setUp(null);
                removeNode.setDown(null);
                removeNode.setPrev(null);
                removeNode.setNext(null);
                removeNode.setData(null);
                if (current.getData() == null && nextNode == null) {
                    // detele current level
                    head = current;
                    head.setLevel(current.getLevel());
                    head.setUp(null);
                    if (current.getLevel() > 1) {
                        head.setDown(current.getDown());
                        current.getDown().setUp(head);
                    }
                }
                if (current.getLevel() > 1) {
                    current = current.getDown();
                } else {
                    size--;
                    return answer;
                }
            }
        }
        throw new NoSuchElementException("Data not found in skip list.");
    }

    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        }
        if (size == 0) {
            return false;
        }
        SkipListNode<T> current = head;
        while (current != null) {
            if (current.getNext() == null) {
                current = current.getDown();
            } else if (current.getNext().getData().compareTo(data) < 0) {
                current = current.getNext();
            } else if (current.getNext().getData().compareTo(data) == 0) {
                return true;
            } else if (current.getNext().getData().compareTo(data) > 0) {
                current = current.getDown();
            }
        }
        return false;
    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        }
        if (size == 0) {
            throw new java.util.NoSuchElementException("Skip list is empty.");
        }
        SkipListNode<T> current = head;
        while (current != null) {
            if (current.getNext() == null) {
                current = current.getDown();
            } else if (current.getNext().getData().compareTo(data) < 0) {
                current = current.getNext();
            } else if (current.getNext().getData().compareTo(data) == 0) {
                return current.getNext().getData();
            } else if (current.getNext().getData().compareTo(data) > 0) {
                current = current.getDown();
            }
        }
        throw new NoSuchElementException("Data not found.");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        size = 0;
        head = new SkipListNode<T>(null, 1, null, null, null, null);
    }

    @Override
    public Set<T> dataSet() {
        Set<T> dataSet = new TreeSet<T>();
        if (size == 0) {
            return dataSet;
        }
        SkipListNode<T> current = head;
        while (current.getDown() != null) {
            current = current.getDown();
        }
        current = current.getNext();
        while (current.getNext() != null) {
            dataSet.add(current.getData());
            current = current.getNext();
        }
        dataSet.add(current.getData());
        return dataSet;
    }

    @Override
    public SkipListNode<T> getHead() {
        return head;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("**********************\n");
        builder.append(String.format("SkipList (size = %d)\n", size()));
        SkipListNode<T> levelCurr = getHead();

        while (levelCurr != null) {
            SkipListNode<T> curr = levelCurr;
            int level = levelCurr.getLevel();
            builder.append(String.format("Level: %2d   ", level));

            while (curr != null) {
                builder.append(String.format("(%s)%s", curr.getData(),
                            curr.getNext() == null ? "\n" : ", "));
                curr = curr.getNext();
            }
            levelCurr = levelCurr.getDown();
        }
        builder.append("**********************\n");
        return builder.toString();
    }
}
