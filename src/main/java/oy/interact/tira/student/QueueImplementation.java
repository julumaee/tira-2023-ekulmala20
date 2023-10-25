package oy.interact.tira.student;

import oy.interact.tira.util.QueueInterface;

public class QueueImplementation<E> implements QueueInterface<E> {
    
    private static final int DEFAULT_QUEUE_SIZE = 10;
    private Object [] itemArray = null;
    private int head, tail, itemCount = 0;
    
    public QueueImplementation() {
        itemArray = new Object[DEFAULT_QUEUE_SIZE];
    }
    public QueueImplementation(int capacity) {
        itemArray = new Object[capacity];
    }

    private void reAllocate(int newSize) {
        /* Reallocates the array with the new size determided by parameter newSize */
        Object [] newArray = new Object [newSize];
        int newIndex = 0;
        int counter = itemCount;
        int index = head;

        while (counter > 0) {
            newArray[newIndex] = itemArray[index];
            index++;
            if (index >= capacity()) {
                index = 0;
            }
            counter--;
            newIndex++;
        }
        head = 0;
        tail = itemCount;
        itemArray = newArray;
    }

    @Override
    public String toString() {
        /* Converts the array to a String */
        StringBuilder builder = new StringBuilder("[");

        int counter = itemCount;
        int index = head;
        while (counter > 0) {
            builder.append(itemArray[index]);
            index++;
            if (index >= capacity()) {
                index = 0;
            }
            if (counter > 1) {
                builder.append(", ");
             }
            counter--;
        }

        builder.append("]");
        return builder.toString();
    }

    @Override
    public int capacity() {
        /* For querying the capacity of the queue. */
        return itemArray.length;
    }

    @Override
    public void enqueue(E element) throws OutOfMemoryError, NullPointerException {
        /*
        Add an element to the queue
        The element to add must not be null, if it is, throw NullPointerException.
        throws OutOfMemoryError if no additional room can be allocated for the queue
        */
        if (size() == capacity()) {
            try {
                reAllocate(capacity()*2);                
            } catch (Exception QueueAllocationException) {
                throw new OutOfMemoryError();
            }
        }
        if (element == null) {
            throw new NullPointerException("Element with value null cannot be enqued");
        } else {
            itemArray[tail] = element;
            itemCount++;
            if (tail == capacity() - 1) {
                tail = 0;
            } else {
                tail++;
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public E dequeue() throws IllegalStateException {
        /*
        Removes an element out of the stack and returns it.
        Throws IllegalStateException if the stack is empty.
        */
        if (isEmpty()) {
            throw new IllegalStateException("Popping from an empty array");
        } else {
            Object temp = itemArray[head];
            itemArray[head] = null;
            head++;
            itemCount--;
            if (head >= capacity()) {
                head = 0;
            }
            return (E) temp;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public E element() throws IllegalStateException {
        /*
        Returns the element at the head of the queue, not removing it from the queue.
        throws IllegalStateException if the queue is empty.
        */
        if (isEmpty()) {
            throw new IllegalStateException("Trying to return from an empty queue");
        }
        else {
            return (E) itemArray[head];
        }
    }

    @Override
    public int size() {
        /* Returns the count of elements currently in the queue. */
        return itemCount;
    }

    @Override
    public boolean isEmpty() {
        /*
        Can be used to check if the queue is empty.
        return True if the queue is empty, false otherwise.
        */
        if (size() == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void clear() {
        /*
        Clears the queue so that it does not contain any elements.
        */
        Object [] newArray = new Object [DEFAULT_QUEUE_SIZE];
        itemArray = newArray;
        itemCount = 0;
        head = 0;
        tail = 0;
    }
}
