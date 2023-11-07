package oy.interact.tira.student;

import oy.interact.tira.util.Pair;

public class TreeNode<K extends Comparable<K>, V> {
    Pair<K, V> pair;
    TreeNode <K, V> leftChild;
    TreeNode <K, V> rightChild;
    TreeNode <K, V> parentNode;
    int childCount;

    public TreeNode(Pair<K, V> pair) {
        this.pair = pair;
        leftChild = null;
        rightChild = null;
        childCount = 0;
    }

    
}
