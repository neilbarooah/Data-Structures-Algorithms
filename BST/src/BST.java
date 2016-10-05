import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Queue;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Your implementation of a binary search tree.
 *
 * @author Neil Barooah
 * @version 1.0
 */
public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty BST.
     * YOU DO NOT NEED TO IMPLEMENT THIS CONSTRUCTOR!
     */
    public BST() {
        size = 0;
        root = null;
    }

    /**
     * Initializes the BST with the data in the Collection. The data in the BST
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Data provided is null");
        }
        for (T currentData : data) {
            if (currentData == null) {
                throw new IllegalArgumentException("Data provided is null");
            }
            add(currentData);
        }

    }

    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data provided is null");
        }
        BSTNode<T> newNode = new BSTNode<T>(data);
        if (root == null) {
            root = newNode;
            size++;
        } else {
            addHelper(root, data);
        }

    }

    /**
     * Helper method for add data to the BST
     * @param currentNode "root" node for current recursion
     * @param data the data to remove from the tree.
     */
    private void addHelper(BSTNode<T> currentNode, T data) {
        if (data.compareTo(currentNode.getData()) < 0) {
            if (currentNode.getLeft() == null) {
                BSTNode<T> newNode = new BSTNode<T>(data);
                currentNode.setLeft(newNode);
                size++;
            } else {
                addHelper(currentNode.getLeft(), data);
            }
        } else if (data.compareTo(currentNode.getData()) > 0) {
            if (currentNode.getRight() == null) {
                BSTNode<T> newNode = new BSTNode<T>(data);
                currentNode.setRight(newNode);
                size++;
            } else {
                addHelper(currentNode.getRight(), data);
            }
        }
    }

    @Override
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data given cannot be null");
        }
        return removeHelper(root, data, null);
    }

    /**
     * need to use predecessor method and the way to do it is by keeping track
     * of the parent node in the recursion call. (might check child's data
     * rather than current node)
     * Helper method for removing data from BST
     * @param currentNode "root" node in the current recursion
     * @param data to be removed
     * @param parentNode of currentNode
     * @throws java.util.NoSuchElementException when data is not found in BST
     * @return value removed from the BST
     */
    private T removeHelper(BSTNode<T> currentNode, T data, BSTNode<T>
            parentNode) {
        if (currentNode == null) {
            throw new NoSuchElementException("Data doesn't exist in BST");
        }
        if ((currentNode.getData().compareTo(data)) > 0) {
            if (currentNode.getLeft() == null) {
                throw new NoSuchElementException("Data doesn't exist in BST");
            } else {
                removeHelper(currentNode.getLeft(), data, currentNode);
            }
        } else if ((currentNode.getData().compareTo(data)) < 0) {
            if (currentNode.getRight() == null) {
                throw new NoSuchElementException("Data doesn't exist in BST");
            } else {
                removeHelper(currentNode.getRight(), data, currentNode);
            }
        } else if (data.equals(currentNode.getData())) {
            // no children
            if (currentNode.getLeft() == null
                    && currentNode.getRight() == null) {
                // remove root node
                if (parentNode == null) {
                    T answer = root.getData();
                    root = null;
                    size--;
                    return answer;
                } else {
                    // currentNode is right child of parent node
                    if (currentNode.getData().compareTo(parentNode.getData())
                            > 0) {
                        T answer = currentNode.getData();
                        parentNode.setRight(null);
                        size--;
                        return answer;
                    } else {
                        T answer = currentNode.getData();
                        parentNode.setLeft(null);
                        size--;
                        return answer;
                    }
                }
            } else if (currentNode.getLeft() != null
                    && currentNode.getRight() == null) { // only left child
                // root node
                if (parentNode == null) {
                    T answer = currentNode.getData();
                    root = currentNode.getLeft();
                    size--;
                    return answer;
                } else {
                    if (currentNode.getData().compareTo(parentNode.getData())
                            > 0) {
                        T answer = currentNode.getData();
                        parentNode.setRight(currentNode.getLeft());
                        size--;
                        return answer;
                    } else {
                        T answer = currentNode.getData();
                        parentNode.setLeft(currentNode.getLeft());
                        size--;
                        return answer;
                    }
                }
            } else if (currentNode.getLeft() == null
                    && currentNode.getRight() != null) { // only right child
                // root node
                if (parentNode == null) {
                    T answer = currentNode.getData();
                    root = currentNode.getRight();
                    size--;
                    return answer;
                } else {
                    if (currentNode.getData().compareTo(parentNode.getData())
                            > 0) {
                        T answer = currentNode.getData();
                        parentNode.setRight(currentNode.getRight());
                        size--;
                        return answer;
                    } else {
                        T answer = currentNode.getData();
                        parentNode.setLeft(currentNode.getRight());
                        size--;
                        return answer;
                    }
                }
            } else if (currentNode.getLeft() != null
                    && currentNode.getRight() != null) { // two children
                // remove root
                if (parentNode == null) {
                    T answer = currentNode.getData();
                    BSTNode<T> newRoot = getPredecessor(currentNode.getLeft(),
                            currentNode);
                    root = newRoot;
                    newRoot.setRight(currentNode.getRight());
                    newRoot.setLeft(currentNode.getLeft());
                    size--;
                    return answer;
                } else {
                    if (currentNode.getData().compareTo(parentNode.getData())
                            > 0) {
                        T answer = currentNode.getData();
                        BSTNode<T> newNode = getPredecessor(
                                currentNode.getLeft(), currentNode);
                        parentNode.setRight(newNode);
                        newNode.setLeft(currentNode.getLeft());
                        newNode.setRight(currentNode.getRight());

                        size--;
                        return answer;
                    } else {
                        T answer = currentNode.getData();
                        BSTNode<T> newNode =
                                getPredecessor(
                                        currentNode.getLeft(), currentNode);
                        parentNode.setLeft(newNode);
                        newNode.setLeft(currentNode.getLeft());
                        newNode.setRight(currentNode.getRight());
                        size--;
                        return answer;
                    }
                }
            }
        }
        return null;
    }

    /**
     * private method to get the predecessor of current node and fix
     * connections of predecessor's parent to it's child nodes it they exist
     * @param currentNode of the tree
     * @param parentNode of currentNode
     * @return predecessor of current node
     */
    private BSTNode<T> getPredecessor(BSTNode<T> currentNode,
                                      BSTNode<T> parentNode) {
        BSTNode<T> parent = parentNode;
        int counter = 0;
        while (currentNode.getRight() != null) {
            currentNode = currentNode.getRight();
            if (counter == 0) {
                parent = parent.getLeft();
            } else {
                parent = parent.getRight();
            }
            counter++;
        }
        if (counter == 0) {
            if (currentNode.getLeft() != null) {
                parent.setLeft(currentNode.getLeft());
            } else {
                parent.setLeft(null);
            }
        } else {
            if (currentNode.getLeft() != null) {
                parent.setRight(currentNode.getLeft());
            } else {
                parent.setRight(null);
            }
        }
        return currentNode;
    }

    @Override
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data provided is null");
        }
        if (root == null) {
            throw new NoSuchElementException("BST is empty");
        }
        return getHelper(root, data);
    }

    /**
     * recursive helper method for get
     * @param currentNode "root" of subtree of the BST in recursive iteration
     * @param data the data to search for in the tree.
     * @return data inside node, if found
     */
    private T getHelper(BSTNode<T> currentNode, T data) {
        if (currentNode == null) {
            throw new NoSuchElementException(data + " cannot be found in BST");
        }
        if (currentNode.getData().equals(data)) {
            return currentNode.getData();
        } else if ((currentNode.getData().compareTo(data)) > 0) {
            return getHelper(currentNode.getLeft(), data);
        } else if ((currentNode.getData().compareTo(data)) < 0) {
            return getHelper(currentNode.getRight(), data);
        }
        throw new NoSuchElementException(data + " cannot be found in BST");
    }

    @Override
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data provided is null");
        }
        return containsHelper(root, data);
    }

    /**
     * helper method for contains
     * @param currentNode "root" of the subtree in recursive call
     * @param data data we're looking for
     * @return boolean indicating if data is in the BST
     */
    private boolean containsHelper(BSTNode<T> currentNode, T data) {
        if (currentNode == null) {
            return false;
        }
        if ((currentNode.getData().compareTo(data)) > 0) {
            return containsHelper(currentNode.getLeft(), data);
        } else if (currentNode.getData().equals(data)) {
            return true;
        } else if ((currentNode.getData().compareTo(data)) < 0) {
            return containsHelper(currentNode.getRight(), data);
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<T> preorder() {
        List<T> dataList = new ArrayList<>();
        return preorderHelper((ArrayList<T>) dataList, root);
    }

    /**
     * helper method for preorder
     * @param dataList list of data in preorder traversal
     * @param currentNode node traversing in recursive call
     * @return nodeList
     */
    private List<T> preorderHelper(ArrayList<T> dataList,
                                   BSTNode<T> currentNode) {
        if (currentNode != null) {
            dataList.add(currentNode.getData());
            preorderHelper(dataList, currentNode.getLeft());
            preorderHelper(dataList, currentNode.getRight());
        }
        return dataList;
    }

    @Override
    public List<T> postorder() {
        List<T> dataList = new ArrayList<>();
        return postorderHelper((ArrayList<T>) dataList, root);
    }

    /**
     * helper method for postorder
     * @param dataList list of data in postorder traversal
     * @param currentNode node traversing in recursive call
     * @return nodeList
     */
    private List<T> postorderHelper(ArrayList<T> dataList,
                                    BSTNode<T> currentNode) {
        if (currentNode != null) {
            postorderHelper(dataList, currentNode.getLeft());
            postorderHelper(dataList, currentNode.getRight());
            dataList.add(currentNode.getData());
        }
        return dataList;
    }

    @Override
    public List<T> inorder() {
        List<T> dataList = new ArrayList<>();
        return inorderHelper((ArrayList<T>) dataList, root);
    }

    /**
     * helper method for inorder
     * @param dataList list of data in postorder traversal
     * @param currentNode node traversing in recursive call
     * @return nodeList
     */
    private List<T> inorderHelper(ArrayList<T> dataList,
                                  BSTNode<T> currentNode) {
        if (currentNode != null) {
            inorderHelper(dataList, currentNode.getLeft());
            dataList.add(currentNode.getData());
            inorderHelper(dataList, currentNode.getRight());
        }
        return dataList;
    }

    @Override
    public List<T> levelorder() {
        List<T> list = new ArrayList<>();
        Queue<BSTNode<T>> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }
        while (!queue.isEmpty()) {
            BSTNode<T> currentNode = queue.remove();
            list.add(currentNode.getData());
            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }
            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
        }
        return list;
    }

    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    @Override
    public int height() {
        return heightHelper(root);
    }

    /**
     * helper method to get height of the BST
     * @param currentNode "root" of the BST in recursive call
     * @return height of the BSt
     */
    private int heightHelper(BSTNode<T> currentNode) {
        if (currentNode == null) {
            return -1;
        } else {
            return 1 + Math.max(heightHelper(currentNode.getLeft()),
                    heightHelper(currentNode.getRight()));
        }
    }

    @Override
    public BSTNode<T> getRoot() {
        // DO NOT EDIT THIS METHOD!
        return root;
    }
}
