/*
    Name: Christina Kim Tran
    PID:  A18864701 / TSN: 200349533
 */

import java.util.NoSuchElementException;

/**
 * @author Christina Kim Tran
 * @since 10/18/26
 */

public class CharQueue {
    private static final int DEFAULT_CAPACITY = 5;
    private static final int DOUBLE_MULTIPLIER = 2;
    /* instance variables, feel free to add more if you need */
    private char[] circularArray;
    private int length;
    private int front;
    private int rear;

    // Creates new queue with default capacity 5.
    public CharQueue() {
        this(DEFAULT_CAPACITY);
    }
    // Creates new queue with given capacity.
    // @param capacity Integer, given capacity
    // @throws IllegalArgumentException if capacity is out of valid range
    public CharQueue(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException();
        }
        circularArray = new char[capacity];
        front = 0;
        rear = 0;
        length = 0;
    }
    // Checks if the queue is empty
    // @return true if empty, false if not
    public boolean isEmpty() {
        return length == 0;
    }
    // Returns number of elements currently stored in queue.
    // @return number of elements currently stored in queue.
    public int size() {
        return length;
    }
    // CLears all elements in the queue
    public void clear() {
        front = rear;
        length = 0;
    }
    // Adds a new elem to the end of the queue.
    // Doubles capacity when adding elem to a full queue.
    // @param elem New element to be added
    public void enqueue(char elem) {
        if (length == circularArray.length) {
            doubleCapacity();
        }
        circularArray[rear] = elem;
        rear = (rear + 1) % circularArray.length;
        length++;
    }
    // Returns the element at the front of the queue.
    // @throws NoSuchElementException if the queue is empty
    // @return the element at the front of the queue.
    public char peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return circularArray[front];
    }
    // Returns and removes the element at the front of queue.
    // @throws NoSuchElementException if the queue is empty
    // @return the element at the front of queue.
    public char dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        char elem = circularArray[front];
        front = (front + 1) % circularArray.length;
        length--;
        return elem;
    }

    private void doubleCapacity() {
        char[] expand = new char[circularArray.length * DOUBLE_MULTIPLIER];
        for (int i = 0; i < length; i++) {
            expand[i] = circularArray[(front + i) % circularArray.length];
        }
        circularArray = expand;
        front = 0;
        rear = length;
    }
}
