package oy.interact.tira.student;

import java.util.Comparator;
import java.util.function.Predicate;

import oy.interact.tira.util.Pair;
import oy.interact.tira.util.TIRAKeyedOrderedContainer;
import oy.interact.tira.util.Visitor;

public class BinarySearchTreeContainer<K extends Comparable<K>, V> implements TIRAKeyedOrderedContainer<K, V> {

    private class TreeNode<K extends Comparable<K>, V> {
        Pair<K, V> pair;
        TreeNode <K, V> leftChild;
        TreeNode <K, V> rightChild;
        TreeNode <K, V> parentNode;
        int childCount;

        public TreeNode(Pair<K, V> pair) {
            this.pair = pair;
            leftChild = null;
            rightChild = null;
            parentNode = null;
            childCount = 0;
        }
    }

    TreeNode<K, V> root;     // Root node of the tree
	private Comparator<K> comparator;  // The comparator used to determine if new node will go to left or right subtree.

	public BinarySearchTreeContainer(Comparator<K> comparator) {
		this.comparator = comparator;
        root = null;
	}

    @Override
	public int indexOf(K itemKey) {
        return findIndexKey(itemKey, root, comparator);
    }

    private int findIndexKey(K itemKey, TreeNode<K, V> node, Comparator <K> comparator) {
        /* A private method for indexOf() to use */
        if (node == null) {
            return -1;
        }
        else if (comparator.compare(itemKey, node.pair.getKey()) == 0) {
            return sizePartTree(node.leftChild);
        }
        else if (comparator.compare(node.pair.getKey(), itemKey) < 0) {
            return findIndexKey(itemKey, node.rightChild, comparator);
        }
        else return findIndexKey(itemKey, node.leftChild, comparator);
    }

    @Override
	public Pair<K,V> getIndex(int index) throws IndexOutOfBoundsException {
        TreeNode<K, V> node;
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException(index);
        }
        else {
            node = getNodeKey(root, index);
            return node.pair;
        }
    }

    private TreeNode<K, V> getNodeKey(TreeNode<K, V> node, int index) {
        /* A private method for getIndex() to use */
        if (node.childCount == index) {
            return node;
        }
        else if (node.childCount > index) {
            return getNodeKey(node.leftChild, index);
        } else {
            return getNodeKey(node.rightChild, index);
        }
    }

    @Override
	public int findIndex(Predicate<V> searcher) {
        TreeNode<K, V> node = findNodeValue(root, searcher);
        if (node == null) {
            return -1;
        } else return sizePartTree(node.leftChild);
    }

	@Override
	public V find(Predicate<V> searcher) {
        return findNodeValue(root, searcher).pair.getValue();
    }

    private TreeNode<K, V> findNodeValue(TreeNode<K, V> node, Predicate<V> searcher) {
        if (node == null) {
            return null;
        }
        else if (!node.pair.getValue().equals(searcher)) {
            findNodeValue(node.leftChild, searcher);
            findNodeValue(node.rightChild, searcher);
        }
        return node;
    }

	@Override
	public void add(K key, V value) throws OutOfMemoryError, IllegalArgumentException {
        if (key == null || value == null) {
            throw new IllegalArgumentException("Either key or value was null, add to BST not excecuted");
        } else {
            try {
                addNode(root, key, value, comparator);
            } catch (Exception OutOfMemoryError) {
                throw new OutOfMemoryError("Ran out of memory while adding to BST");
            }
        }
    }

    private void addNode(TreeNode<K, V> root, K key,V value, Comparator<K> comparator) {
        /* A private method for add() to use */
        TreeNode<K, V> parent = null;
        TreeNode<K, V> tempNode = root;
        Pair<K, V> pair = new Pair<>(key, value);
        TreeNode<K, V> node = new TreeNode<>(pair);
        
        while (tempNode != null) {
            if (node.pair.getValue().equals(tempNode.pair.getValue())) {
                node = tempNode; // if the added element already exists in the BST, swap old values with the new one
                return;
            }
            tempNode.childCount++;
            parent = tempNode;
            if (comparator.compare(key, tempNode.pair.getKey()) <= 0) {
                tempNode = tempNode.leftChild;
            }
            else if (comparator.compare(key, tempNode.pair.getKey()) > 0) {
                tempNode = tempNode.rightChild;
            }
        }
        node.parentNode = parent;
        if (root == null) {
            root = node;
        }
        else if (comparator.compare(key, parent.pair.getKey()) > 0) {
            parent.leftChild = node;
        } else {
            parent.rightChild = node;
        }
        return;
    }

	@Override
	public V get(K key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("Trying to get() with null key", null);
        }
        else {
            TreeNode<K, V> node = root;
            while (comparator.compare(key, node.pair.getKey()) != 0) {
                if (comparator.compare(key, node.pair.getKey()) < 0) {
                    node = node.leftChild;
                }
                else if (comparator.compare(key, node.pair.getKey()) > 0){
                    node = node.rightChild;
                }
            }
            return node.pair.getValue();
        }
    }

    @Override
	public int size() {
        return sizePartTree(root);
    }

    private int sizePartTree(TreeNode<K, V> node) {
        /* Returns the size of a part of the BST starting from given node */
        return node.childCount + 1;
    }

	@Override
	public void clear() {
        clearBST(root);
    }

    private void clearBST(TreeNode<K, V> node) {
        /* A recursive algorithm to clear the BST */
        if (node != null) {
            clearBST(node.leftChild);
            clearBST(node.rightChild);
            node = null;
        }
    }

    @SuppressWarnings("unchecked")
	@Override
	public Pair<K,V> [] toArray() throws Exception {
        try {
            Pair<K,V> [] array = (Pair<K, V> []) new Pair[size()];
            BSTToArray(root, array);
            return array;
        } catch (Exception OutOfMemoryError) {
            throw new OutOfMemoryError("Ran out of memory while trying to create array");
        } 
    }

    private Pair<K,V> [] BSTToArray(TreeNode<K, V> node, Pair<K,V> [] array) throws Exception {
        /* An recursive algorithm for method toArray() */
        if (node != null) {
            BSTToArray(node.leftChild, array);
            BSTToArray(node.rightChild, array);
            array[node.leftChild.childCount] = node.pair;
        }
        return array;
    }

    /**
	 * Removes and retrieves a value assosicated with the key (keys are equal), or null.
	 * 
	 * The key-value pair is removed from the container, if found.
	 * 
	 * @param key The key to use in searching.
	 * @return The value or null if one is not found.
	 * @throws IllegalArgumentException If the key is null.
	 */
	@Override
	public V remove(K key) throws IllegalArgumentException {
        return null;
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
