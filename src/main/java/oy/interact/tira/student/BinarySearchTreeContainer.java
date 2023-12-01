package oy.interact.tira.student;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

import oy.interact.tira.util.Pair;
import oy.interact.tira.util.TIRAKeyedOrderedContainer;
import oy.interact.tira.util.Visitor;

public class BinarySearchTreeContainer<K extends Comparable<K>, V> implements TIRAKeyedOrderedContainer<K, V> {

    TreeNode<K, V> root;     // Root node of the tree
    int maxDepth;
    int currentDepth = 0;
	private Comparator<K> comparator;  // The comparator used to determine if new node will go to left or right subtree.

	public BinarySearchTreeContainer(Comparator<K> comparator) {
		this.comparator = comparator;
        root = null;
	}

    // BST private methods:

    private int findIndexWithKey(K itemKey, TreeNode<K, V> node, Comparator <K> comparator) {
        /* A private method for indexOf() to use */
        int indexAdder = 0;
        while (node != null) {
            if (node.leftChild != null) {
                if (comparator.compare(node.pair.getKey(), itemKey) == 0) {
                    return sizePartTree(node.leftChild) + indexAdder;
                } else if (comparator.compare(node.pair.getKey(), itemKey) > 0) {
                    node = node.leftChild;
                } else {
                    if (node.rightChild != null) {
                        indexAdder = indexAdder + sizePartTree(node.leftChild) + 1;
                        node = node.rightChild;
                    } else return -1; // Node with the given key not found at the leaf
                }
            } else if (comparator.compare(node.pair.getKey(), itemKey) == 0) {
                return sizePartTree(node.leftChild) + indexAdder;
            } else if (node.rightChild != null) {
                indexAdder++;
                node = node.rightChild;
            } else return -1; // Node with the given index not found at the leaf
        }
        return -1; // Node with the given index not found
    }

    private TreeNode<K, V> getNodeWithIndex(TreeNode<K, V> node, int index) {
        /* A private method for getIndex() to use */
        int indexAdder = 0;
        while (node != null) {
            if (node.leftChild != null) {
                if (sizePartTree(node.leftChild) + indexAdder == index) {
                    return node;
                } else if (sizePartTree(node.leftChild) + indexAdder > index) {
                    node = node.leftChild;
                } else {
                    if (node.rightChild != null) {
                        indexAdder = indexAdder + sizePartTree(node.leftChild) + 1;
                        node = node.rightChild;
                    } else return null; // Node with the given index not found at the leaf
                }
            } else if (index == indexAdder) {
                return node;
            } else if (node.rightChild != null) {
                indexAdder++;
                node = node.rightChild;
            } else return null; // Node with the given index not found at the leaf
        }
        return null; // Node with the given index not found
    }

    private int searchIndex(TreeNode<K, V> node, Predicate<V> searcher, StackImplementation<TreeNode<K, V>> nodeStack, int index, boolean bottom) {
        /* A private method for method findIndex() */
        if (bottom && node.leftChild != null) {
            nodeStack.push(node);
            return searchIndex(node.leftChild, searcher, nodeStack, index, true);
        } else if (nodeStack.isEmpty() && node.rightChild == null) {
            if (searcher.test(node.pair.getValue())) {
                return index;
            }
            return -1;
        } else {
            if (searcher.test(node.pair.getValue())) {
                return index;
            }
            index++;
            if (node.rightChild != null) {
                return searchIndex(node.rightChild, searcher, nodeStack, index, true);
            } else {
                return searchIndex(nodeStack.pop(), searcher, nodeStack, index, false);
            }
        }
    }

    private V findAndRemoveNode(TreeNode<K, V> node, K itemKey, Comparator <K> comparator) {
        /* A private method for indexOf() to use */
        TreeNode<K, V> parent = null;
        int indexAdder = 0;
        V value = null;
        while (node != null) {
            if (node.leftChild != null) {
                if (comparator.compare(node.pair.getKey(), itemKey) == 0) {
                    node.parentNode.leftChild = node.leftChild;
                    node.parentNode.rightChild = node.rightChild;
                    node.parentNode.pair = node.pair;
                    node.parentNode.childCount = node.childCount;

                    parent.leftChild = node.leftChild;
                    parent.rightChild = node.rightChild;
                    parent.pair = node.pair;

                    value = node.pair.getValue();

                    node.childCount = 0;
                    node.leftChild = null;
                    node.rightChild = null;
                    node.parentNode = null;
                    node.pair = null;
                    node = null;
                } else if (comparator.compare(node.pair.getKey(), itemKey) > 0) {
                    parent = node;
                    node = node.leftChild;
                } else {
                    if (node.rightChild != null) {
                        indexAdder = indexAdder + sizePartTree(node.leftChild) + 1;
                        parent = node;
                        node = node.rightChild;
                    } else return null; // Node with the given key not found at the leaf
                }
            } else if (comparator.compare(node.pair.getKey(), itemKey) == 0) {
                node.parentNode.leftChild = node.leftChild;
                node.parentNode.rightChild = node.rightChild;
                node.parentNode.pair = node.pair;
                node.parentNode.childCount = node.childCount;

                parent.leftChild = node.leftChild;
                parent.rightChild = node.rightChild;
                parent.pair = node.pair;

                value = node.pair.getValue();

                node.childCount = 0;
                node.leftChild = null;
                node.rightChild = null;
                node.parentNode = null;
                node.pair = null;
                node = null;
            } else if (node.rightChild != null) {
                indexAdder++;
                parent = node;
                node = node.rightChild;
            } else return null; // Node with the given key not found at the leaf
        }
        if (node == null) {
            while (parent != root) { // Updating the childcounts
                parent.childCount--;
                parent = parent.parentNode;
            }
            return value;
        }
        return null;
    }

    private TreeNode<K, V> findNodeValue(TreeNode<K, V> node, Predicate<V> searcher, StackImplementation<TreeNode<K, V>> nodeStack, boolean bottom) {
        if (bottom && node.leftChild != null) {
            nodeStack.push(node);
            return findNodeValue(node.leftChild, searcher, nodeStack, true);
        } else if (nodeStack.isEmpty() && node.rightChild == null) {
            if (searcher.test(node.pair.getValue())) {
                return node;
            } else {
                return null;
            }
        } else {
            if (searcher.test(node.pair.getValue())) {
                return node;
            }
            if (node.rightChild != null) {
                return findNodeValue(node.rightChild, searcher, nodeStack, true);
            } else {
                return findNodeValue(nodeStack.pop(), searcher, nodeStack, false);
            }
        }
    }

    private void addNode(K key,V value, Comparator<K> comparator) {
        /* A private method for add() to use */
        TreeNode<K, V> parent = null;
        TreeNode<K, V> tempNode = root;
        Pair<K, V> pair = new Pair<>(key, value);
        TreeNode<K, V> node = new TreeNode<>(pair);
        currentDepth = 0;
        
        while (tempNode != null) {
            if (node.pair.getValue().equals(tempNode.pair.getValue())) {
                if (tempNode == root) {
                    root = node;
                    return;
                } else {
                    if (comparator.compare(key, parent.pair.getKey()) <= 0) {
                        node.parentNode = parent;
                        parent.leftChild = node;
                    } else if (comparator.compare(key, parent.pair.getKey()) > 0) {
                        node.parentNode = parent;
                        parent.rightChild = node;
                    }
                }
            }
            tempNode.childCount++;
            currentDepth++;
            
            parent = tempNode;
            if (comparator.compare(key, tempNode.pair.getKey()) <= 0) {
                tempNode = tempNode.leftChild;
            } else if (comparator.compare(key, tempNode.pair.getKey()) > 0) {
                tempNode = tempNode.rightChild;
            }
        }
        if (currentDepth > maxDepth) {
                maxDepth = currentDepth;
        }
        if (root == null) {
            root = node;
            maxDepth = 1;
        } else if (comparator.compare(key, parent.pair.getKey()) <= 0) {
            node.parentNode = parent;
            parent.leftChild = node;
        } else if (comparator.compare(key, parent.pair.getKey()) > 0) {
            node.parentNode = parent;
            parent.rightChild = node;
        }
        return;
    }

    private int sizePartTree(TreeNode<K, V> node) {
        /* Returns the size of a part of the BST starting from given node */
        if (node != null) {
            return node.childCount + 1;            
        }
        else return 0;
    }

    private Pair<K,V> [] BSTToArray(TreeNode<K, V> node, Pair<K,V> [] array, AtomicInteger atomicIndex) {
        /* An recursive algorithm for method toArray() */
        if (node.leftChild != null) {
            BSTToArray(node.leftChild, array, atomicIndex);    
        }
        array[atomicIndex.incrementAndGet()] = node.pair;
        if (node.rightChild != null) {
            BSTToArray(node.rightChild, array, atomicIndex);
        }
        return array;
    }

    // BST public methods:

    public int maxDepth() {
        return maxDepth;
    }

    @Override
	public int indexOf(K itemKey) {

        if (root == null) {
            return -1;
        } else {
            return findIndexWithKey(itemKey, root, comparator);
        }
    }

    @Override
	public Pair<K,V> getIndex(int index) throws IndexOutOfBoundsException {
        TreeNode<K, V> node;
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException(index);
        }
        else {
            node = getNodeWithIndex(root, index);
            if (node == null) {
                return null;
            } else return node.pair;
        }
    }

    @Override
	public int findIndex(Predicate<V> searcher) {
        if (root == null) {
            return -1;
        } else { 
        StackImplementation<TreeNode<K, V>> nodeStack = new StackImplementation<TreeNode<K, V>>(size());
        int index;
        index = searchIndex(root, searcher, nodeStack, 0, true);
        return index;
        }
    }

	@Override
	public V find(Predicate<V> searcher) {
        if (root == null) {
            return null;
        } else {
            StackImplementation<TreeNode<K, V>> nodeStack = new StackImplementation<TreeNode<K, V>>(size());
            return findNodeValue(root, searcher, nodeStack, true).pair.getValue();
        }
    }

	@Override
	public void add(K key, V value) throws OutOfMemoryError, IllegalArgumentException {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Either key or value was null, add to BST not excecuted");
        } else {
            addNode(key, value, comparator);
        }
    }

	@Override
	public V get(K key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("Trying to get() with null key", null);
        }
        else {
            TreeNode<K, V> node = root;
            while (node != null && comparator.compare(key, node.pair.getKey()) != 0) {
                if (comparator.compare(key, node.pair.getKey()) < 0) {
                    node = node.leftChild;
                }
                else if (comparator.compare(key, node.pair.getKey()) > 0){
                    node = node.rightChild;
                }
            }
            if (node == null) {
                return null;
            } else {
                return node.pair.getValue();
            }
        }
    }

    @Override
	public int size() {
        return sizePartTree(root);
    }

	@Override
	public void clear() {
        root = null;
    }

    @SuppressWarnings("unchecked")
	@Override
	public Pair<K,V> [] toArray() throws Exception {
        AtomicInteger atomicIndex = new AtomicInteger(-1);
        Pair<K,V> [] array = (Pair<K, V> []) new Pair[size()];
        BSTToArray(root, array, atomicIndex);
        return array;
    }

	@Override
	public V remove(K key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("Trying to remove with null key");
        } else return findAndRemoveNode(root, key, comparator);
    }

	@Override
	public int capacity() {
        return -1; // NOT NEEDED FOR BST, IT HAS NO CAPACITY!
    }

	@Override
	public void ensureCapacity(int capacity) throws OutOfMemoryError, IllegalArgumentException {
        // NOT NEEDED FOR BST, IT HAS NO CAPACITY!
    }

	@Override
	public void accept(Visitor<K,V> visitor) throws Exception {
        return;
    }

}
