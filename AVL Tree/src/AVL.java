import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Your implementation of an AVL Tree.
 *
 * @author Neil Barooah
 * @version 1.0
 */
public class AVL<T extends Comparable<? super T>> implements AVLInterface<T> {

    // Do not make any new instance variables.
    private AVLNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty AVL tree.
     * DO NOT IMPLEMENT THIS CONSTRUCTOR!
     */
    public AVL() {
        root = null;
        size = 0;
    }

    /**
     * Initializes the AVL tree with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("Data is null.");
        }
        for (T t: data) {
            if (t == null) {
                throw new java.lang.IllegalArgumentException("Data is null.");
            }
            add(t);
        }
    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new java.lang.IllegalArgumentException("The data is null.");
        }
        if (contains(data)) {
            return;
        }
        root = recursiveAdd(root, data);
        size++;
    }

    /**
     * private helper function using recursion to help add(T data).
     *
     * @param current node in the recursion
     * @param data new data to add
     * @return AVLNode holding data
     */
    private AVLNode<T> recursiveAdd(AVLNode<T> current, T data) {
        if (current == null) {
            AVLNode<T> addNode = new AVLNode<T>(data);
            addNode.setHeight(0);
            addNode.setBalanceFactor(0);
            return addNode;
        }
        int position = data.compareTo(current.getData());
        if (position < 0) {
            current.setLeft(recursiveAdd(current.getLeft(), data));
            fixHeight(current);
            fixBalanceFactor(current);
        } else if (position > 0) {
            current.setRight(recursiveAdd(current.getRight(), data));
            fixHeight(current);
            fixBalanceFactor(current);
        }
        int balance = current.getBalanceFactor();
        if (balance <= 1 && balance >= -1) {
            return current;
        } else if (balance < -1
                && current.getRight().getBalanceFactor() > 0) {
            current = rotateRightLeft(current);
        } else if (balance > 1
                && current.getLeft().getBalanceFactor() < 0) {
            current = rotateLeftRight(current);
        } else if (balance < -1) {
            current = rotateLeft(current);
        } else if (balance > 1) {
            current = rotateRight(current);
        }
        return current;
    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is null");
        }
        T removeNode = get(data);
        if (removeNode != null) {
            root = recursiveRemove(root, data);
            size--;
        }
        return removeNode;
    }

    /**
     * Removes a node from the AVL tree
     * @param current current node
     * @param data that needs to be removed from AVL tree
     * @return current node in recursion
     */
    private AVLNode<T> recursiveRemove(AVLNode<T> current, T data) {
        if (current == null) {
            return null;
        }
        int position = data.compareTo(current.getData());
        if (position < 0) {
            current.setLeft(
                    recursiveRemove(current.getLeft(), data));
            fixHeight(current);
            fixBalanceFactor(current);
        } else if (position > 0) {
            current.setRight(
                    recursiveRemove(current.getRight(), data));
            fixHeight(current);
            fixBalanceFactor(current);
        } else {
            if (current.getLeft() == null
                    && current.getRight() == null) {
                return null;
            } else if (current.getLeft() == null) {
                return current.getRight();
            } else if (current.getRight() == null) {
                return current.getLeft();
            } else {
                T smallest = getSmallestNode(current.getRight());
                current.setData(smallest);
                current.setRight(
                        recursiveRemove(current.getRight(), smallest));
                if (current.getRight() != null) {
                    fixHeight(current.getRight());
                    fixBalanceFactor(current.getRight());
                }
                fixHeight(current);
                fixBalanceFactor(current);
            }
        }
        int balanceFactor = current.getBalanceFactor();
        if (balanceFactor < 2 && balanceFactor > -2) {
            return current;
        } else if (balanceFactor < -1
                && current.getRight().getBalanceFactor() > 0) {
            current = rotateRightLeft(current);
        } else if (balanceFactor > 1
                && current.getLeft().getBalanceFactor() < 0) {
            current = rotateLeftRight(current);
        } else if (balanceFactor < -1) {
            current = rotateLeft(current);
        } else if (balanceFactor > 1) {
            current = rotateRight(current);
        }
        return current;
    }

    /**
     * returns smallest node from subtree
     * @param node root node
     * @return smallest node
     */
    private T getSmallestNode(AVLNode<T> node) {
        AVLNode<T> current = node;
        if (current.getLeft() == null) {
            return current.getData();
        } else {
            while (current.getLeft() != null) {
                current = current.getLeft();
            }
        }
        return current.getData();
    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The data is null.");
        } else {
            return recursiveGet(root, data);
        }
    }

    /**
     * recursive helper function for get()
     * @param current current node
     * @param data data to get
     * @return data obtained from node
     */
    private T recursiveGet(AVLNode<T> current, T data) {
        if (current == null) {
            throw new java.util.NoSuchElementException("Data not found");
        }
        int position = data.compareTo(current.getData());
        if (position < 0) {
            return recursiveGet(current.getLeft(), data);
        } else if (position > 0) {
            return recursiveGet(current.getRight(), data);
        } else {
            return current.getData();
        }
    }

    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The given data doesn't exist.");
        }
        return recursiveContains(root, data);
    }

    /**
     * private helper method to check if the tree contains data
     *
     * @param node is the current node of BST
     * @param data is the data we need to search
     * @return boolean whether the BST contains data
     */
    private boolean recursiveContains(AVLNode<T> node, T data) {
        if (node == null) {
            return false;
        } else if (node.getData().compareTo(data) > 0) {
            return recursiveContains(node.getLeft(), data);
        } else if (node.getData().compareTo(data) < 0) {
            return recursiveContains(node.getRight(), data);
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<T> preorder() {
        ArrayList<T> list = new ArrayList<T>(size);
        recursivePreorder(root, list);
        return list;
    }

    /**
     * helper method for preorder
     * @param current root node
     * @param list preorder list of nodes
     */
    private void recursivePreorder(AVLNode<T> current, ArrayList<T> list) {
        if (current == null) {
            return;
        }
        list.add(current.getData());
        recursivePreorder(current.getLeft(), list);
        recursivePreorder(current.getRight(), list);
    }

    @Override
    public List<T> postorder() {
        ArrayList<T> list = new ArrayList<T>(size);
        recursivePostOrder(root, list);
        return list;
    }

    /**
     * helper method for postOrder()
     * @param current root node
     * @param list postorder list of nodes
     */
    private void recursivePostOrder(AVLNode<T> current, ArrayList<T> list) {
        if (current != null) {
            recursivePostOrder(current.getLeft(), list);
            recursivePostOrder(current.getRight(), list);
            list.add(current.getData());
        }
    }

    @Override
    public List<T> inorder() {
        ArrayList<T> list = new ArrayList<T>(size);
        recursiveInorder(root, list);
        return list;
    }

    /**
     * helper method for inorder()
     * @param current root node
     * @param list inorder list of nodes
     */
    private void recursiveInorder(AVLNode<T> current, ArrayList<T> list) {
        if (current != null) {
            recursiveInorder(current.getLeft(), list);
            list.add(current.getData());
            recursiveInorder(current.getRight(), list);
        }
    }

    @Override
    public List<T> levelorder() {
        ArrayList<T> list = new ArrayList<T>(size);
        recursiveLevelOrder(list, root);
        return list;
    }

    /**
     * helper method for levelOrder()
     * @param node root node
     * @param list levelorder list of nodes
     */
    private void recursiveLevelOrder(List<T> list, AVLNode<T> node) {
        if (node != null) {
            LinkedList<AVLNode<T>> levelList = new LinkedList<>();
            levelList.add(node);
            while (!levelList.isEmpty()) {
                AVLNode<T> start = levelList.poll();
                if (start.getLeft() != null) {
                    levelList.add(start.getLeft());
                }
                if (start.getRight() != null) {
                    levelList.add(start.getRight());
                }
                list.add(start.getData());
            }
        }
    }

    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    @Override
    public int height() {
        if (root == null) {
            return -1;
        } else {
            return root.getHeight();
        }
    }

    /**
     * fixes the height of the node
     * @param current that needs to be fixed
     */
    private void fixHeight(AVLNode<T> current) {
        if (current.getLeft() == null && current.getRight() == null) {
            current.setHeight(0);
        } else if (current.getLeft() == null) {
            current.setHeight(current.getRight().getHeight() + 1);
        } else if (current.getRight() == null) {
            current.setHeight(current.getLeft().getHeight() + 1);
        } else if (current.getLeft().getHeight()
                == current.getRight().getHeight()) {
            current.setHeight(current.getLeft().getHeight() + 1);
        } else if (current.getLeft().getHeight()
                > current.getRight().getHeight()) {
            current.setHeight(current.getLeft().getHeight() + 1);
        } else {
            current.setHeight(current.getRight().getHeight() + 1);
        }
    }

    /**
     * Rotates the right of the AVL tree to the left
     *
     * @param current node at which to rotate
     * @return final tree
     */
    private AVLNode<T> rotateLeftRight(AVLNode<T> current) {
        current.setLeft(rotateLeft(current.getLeft()));
        increaseHeight(current.getLeft());
        current = rotateRight(current);
        return current;

    }

    /**
     * rotates the left of the AVL tree to the right
     * @param current node at which to rotate
     * @return final tree
     */
    private AVLNode<T> rotateRightLeft(AVLNode<T> current) {
        current.setRight(rotateRight(current.getRight()));
        increaseHeight(current.getRight());
        current = rotateLeft(current);
        return current;

    }

    /**
     * rotates the AVL tree to the left
     * @param current node from which the tree is rotated left
     * @return final tree
     */
    private AVLNode<T> rotateLeft(AVLNode<T> current) {
        AVLNode<T> answer = current.getRight();
        current.setRight(answer.getLeft());
        decreaseHeight(current);
        decreaseHeight(current);
        if (current.getRight() != null) {
            fixHeight(current.getRight());
            fixBalanceFactor(current.getRight());
        }
        if (current.getLeft() != null) {
            fixHeight(current.getLeft());
            fixBalanceFactor(current.getLeft());
        }
        fixBalanceFactor(current);
        answer.setLeft(current);
        fixHeight(answer);
        fixBalanceFactor(answer);
        return answer;

    }

    /**
     * rotates the AVL tree to the right
     * @param current node from which tree will be rotated
     * @return final tree
     */
    private AVLNode<T> rotateRight(AVLNode<T> current) {
        AVLNode<T> answer = current.getLeft();
        current.setLeft(answer.getRight());
        decreaseHeight(current);
        decreaseHeight(current);
        if (current.getLeft() != null) {
            fixHeight(current.getLeft());
            fixBalanceFactor(current.getLeft());
        }
        if (current.getRight() != null) {
            fixHeight(current.getRight());
            fixBalanceFactor(current.getRight());
        }
        fixBalanceFactor(current);
        answer.setRight(current);
        fixHeight(answer);
        fixBalanceFactor(answer);
        return answer;

    }

    /**
     * decreases height of all nodes
     * @param node root of the AVL
     */
    private void decreaseHeight(AVLNode<T> node) {
        if (node != null) {
            node.setHeight(node.getHeight() - 1);
            decreaseHeight(node.getRight());
            decreaseHeight(node.getLeft());
        }
    }

    /**
     * Increases height of all nodes
     * @param node root of the AVL
     */
    private void increaseHeight(AVLNode<T> node) {
        if (node != null) {
            node.setHeight(node.getHeight() + 1);
            increaseHeight(node.getRight());
            increaseHeight(node.getLeft());
        }
    }

    /**
     * Fixes the balance factor of the tree
     * @param node that needs to be balanced
     */
    private void fixBalanceFactor(AVLNode<T> node) {
        int rHeight = -1;
        if (node.getRight() != null) {
            rHeight = node.getRight().getHeight();
        }
        int lHeight = -1;
        if (node.getLeft() != null) {
            lHeight = node.getLeft().getHeight();
        }
        node.setBalanceFactor(lHeight - rHeight);
    }

    /**
     * Compares two AVLs and checks to see if the trees are the same.  If
     * the trees have the same data in a different arrangement, this method
     * should return false.  This will only return true if the tree is in the
     * exact same arrangement as the other tree.
     *
     * You may assume that you won't get an AVL with a different generic type.
     * For example, if this AVL holds Strings, then you will not get as an input
     * an AVL that holds Integers.
     * 
     * Be sure to also implement the other general checks that .equals() should
     * check as well.
     * 
     * @param other the Object we are comparing this AVL to
     * @return true if other is equal to this AVL, false otherwise.
     */
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (!(other instanceof AVL)) {
            return false;
        }
        AVLNode<T> node1 = root;
        AVL<T> bst2 = (AVL<T>) other;
        AVLNode<T> node2 = bst2.root;
        if (node1 == null && bst2 == null) {
            return false;
        }
        return equalsRecursive(node1, node2);
    }

    /**
     * Helper method for the equals method to compare two AVLs
     * @param node1 root node of first BST
     * @param node2 root node of the second BST
     * @return boolean that checks if two BSTs are same
     */
    private boolean equalsRecursive(AVLNode<T> node1, AVLNode<T> node2) {

        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 != null && node2 != null) {
            return (node1.getData().equals(node2.getData())
                    && equalsRecursive(node1.getLeft(), node2.getLeft())
                    && equalsRecursive(node1.getRight(), node2.getRight()));
        }
        return false;
    }

    @Override
    public AVLNode<T> getRoot() {
        // DO NOT EDIT THIS METHOD!
        return root;
    }
}