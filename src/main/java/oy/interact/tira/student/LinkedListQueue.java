package oy.interact.tira.student;

import oy.interact.tira.util.QueueInterface;

public class LinkedListQueue<E> implements QueueInterface<E> {

    private class Node<T> {
        T data;
        Node<T> next;
        Node<T> previous;

        public Node(T data) {
            this.data = data;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public LinkedListQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public String toString() {
        /* Converts the array to a String */
        StringBuilder builder = new StringBuilder("[");
        int counter = size;
        if (!isEmpty()) {
            Node<E> tmp = new Node<>(head.data);
            while (counter > 0) {
                builder.append(tmp.data);
                if (counter > 1) {
                    builder.append(", ");
                }
                tmp = tmp.next;
                counter--;
            }
        }
        builder.append("]");
        return builder.toString();
    }

    @Override
    public int capacity() {
        return Integer.MAX_VALUE; // Linked list doesn't have a capacity
    }

    @Override
    public void enqueue(E element) throws OutOfMemoryError, NullPointerException {
        if (element == null) {
            throw new NullPointerException("Trying to enqueue null element");
        } else {
            Node<E> node = new Node<E>(element);
            if (size == 0) {
                head = node;
                tail = node;
            } else {
                tail.next = node;
                node.previous = tail;
                tail = node;
            }
            size++;
        }
    }

    @Override
    public E dequeue() throws IllegalStateException {
        if (size == 0) {
            throw new IllegalStateException("Trying to deque from empty list");
        } else {
            E headData = head.data;
            head = head.next;
            size--;
            if (head != null) {
                head.previous = null;
            } else tail = null;
            return headData;
        }
    }

    @Override
    public E element() throws IllegalStateException {
        if(size == 0) {
            throw new IllegalStateException("Trying to peek from empty list");
        } else return head.data;
    }

    @Override
    public int size() {
        return size;    
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else return false;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }
}