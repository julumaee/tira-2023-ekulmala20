package oy.interact.tira.util;

import oy.interact.tira.util.StackInterface;

public class StackImplementation<E> implements StackInterface<E> {

    private static final int DEFAULT_STACK_SIZE = 10;
    private Object [] itemArray = null;
    private int currentIndex = -1;

    public StackImplementation() {
        itemArray = new Object[DEFAULT_STACK_SIZE];
    }
    public StackImplementation(int capacity) {
        itemArray = new Object[capacity];
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        for (int index = 0; index <= currentIndex; index++) {
            builder.append(itemArray[index]);
            if (index < currentIndex) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }

    private void reAllocate(int newSize) {
        Object [] newArray = new Object [newSize];
        for (int index = 0; index<=currentIndex; index++) {
            newArray[index] = itemArray[index];
        }
        itemArray = newArray;
    }


    @Override
    public int capacity() {
        /* For querying the capacity of the stack. */
        return itemArray.length;
    }
   
    @Override
    public void push(E element) throws OutOfMemoryError, NullPointerException {
        /*
        Push an element to the stack
        The element to push must not be null, if it is, throw NullPointerException.
        throws OutOfMemoryError if no additional room can be allocated for the stack
        */
        if (element == null) {
            throw new NullPointerException("Element with value null cannot be pushed");
        }
        if (currentIndex >= capacity() -1) {
            try {
                reAllocate(capacity() * 2);
            } catch (Exception OutOfMemoryError) {
                throw new OutOfMemoryError();
            }
        }
        currentIndex++;        
        itemArray[currentIndex] = element;

    }

    @Override
    @SuppressWarnings("unchecked")
    public E pop() throws IllegalStateException {
        /*
        Pops an element out of the stack, removing it from the internal data storage.
        Throws IllegalStateException if the stack is empty.
        Returns The popped element.
        */
        if (isEmpty()) {
            throw new IllegalStateException("Popping from an empty array");
        }
        else {
            E element = (E) itemArray[currentIndex];
            itemArray[currentIndex] = null;
            currentIndex--;
            return element;
        }  
    }

    @Override
    @SuppressWarnings("unchecked")
    public E peek() throws IllegalStateException{
        /*
        Returns the element at the top of the stack, not removing it from the stack.
        throws IllegalStateException if the stack is empty.
        */
        if (isEmpty()) {
            throw new IllegalStateException("Peeking from an empty stack");
        }
        else {
            return (E) itemArray[currentIndex];
        }
    }

    @Override
    public int size() {
        /*
        Returns the size of the stack; the count of elements currently in the stack.
        */
        return currentIndex + 1;
    }

    @Override
    public boolean isEmpty() {
        /*
        Use to check if the stack is empty.
        */
        if (currentIndex < 0) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void clear() {
        /*
        Clears the stack so that it does not contain any elements.
        */
        Object [] newArray = null;
        newArray = new Object [DEFAULT_STACK_SIZE];
        itemArray = newArray;
        currentIndex = -1;
    }
}
