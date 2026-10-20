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

    public CharQueue() {
        this(DEFAULT_CAPACITY);
    }

    public CharQueue(int capacity) {
        if (capacity < 1) {
            throw new IllegalArgumentException();
        }
        circularArray = new char[capacity];
        front = 0;
        rear = 0;
        length = 0;
    }

    public boolean isEmpty() {
        return length == 0;
    }

    public int size() {
        return length;
    }

    public void clear() {
        front = rear;
        length = 0;
    }

    public void enqueue(char elem) {
        if (length == circularArray.length) {
            doubleCapacity();
        }
        circularArray[rear] = elem;
        rear = (rear + 1) % circularArray.length;
        length++;
    }

    public char peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return circularArray[front];
    }

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
