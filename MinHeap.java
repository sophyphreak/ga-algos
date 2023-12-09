import java.util.NoSuchElementException;

import javax.swing.plaf.synth.SynthEditorPaneUI;

/**
 * Your implementation of a MinHeap.
 */
public class MinHeap<T extends Comparable<? super T>> {

    /**
     * The initial capacity of the MinHeap.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * This is the constructor that constructs a new MinHeap.
     *
     * Recall that Java does not allow for regular generic array creation,
     * so instead we cast a Comparable[] to a T[] to get the generic typing.
     */
    public MinHeap() {
        // DO NOT MODIFY THIS METHOD!
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * Adds an item to the heap. If the backing array is full (except for
     * index 0) and you're trying to add a new item, then double its capacity.
     *
     * Method should run in amortized O(log n) time.
     *
     * @param data The data to add.
     * @throws java.lang.IllegalArgumentException If the data is null.
     */
    public void add(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new IllegalArgumentException();
        }
        if (this.size == this.backingArray.length - 1) {
            T[] newBackingArray = (T[]) new Comparable[this.backingArray.length * 2];
            for (int i = 0; i < this.backingArray.length; i++) {
                newBackingArray[i] = this.backingArray[i];
            }
            this.backingArray = newBackingArray;
        }
        this.size++;
        this.backingArray[this.size] = data;
        int i = this.size;
        while (i > 1 && this.backingArray[i / 2].compareTo(this.backingArray[i]) > 0) {
            T temp = this.backingArray[i / 2];
            this.backingArray[i / 2] = this.backingArray[i];
            this.backingArray[i] = temp;
            i /= 2;
        }
    }

    /**
     * Removes and returns the min item of the heap. As usual for array-backed
     * structures, be sure to null out spots as you remove. Do not decrease the
     * capacity of the backing array.
     *
     * Method should run in O(log n) time.
     *
     * @return The data that was removed.
     * @throws java.util.NoSuchElementException If the heap is empty.
     */
    public T remove() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (this.size == 0) {
            throw new NoSuchElementException();
        }
        T result = this.backingArray[1];
        this.backingArray[1] = this.backingArray[this.size];
        this.backingArray[size] = null;
        this.size--;
        int i = 1;
        while (true) {
            if (i * 2 > this.size) {
                break; // i is leaf node
            }
            if (this.backingArray[i * 2].compareTo(this.backingArray[i]) < 0 && i * 2 + 1 > this.size) {
                T temp = this.backingArray[i];
                this.backingArray[i] = this.backingArray[i * 2];
                this.backingArray[i * 2] = temp;
                break; // because i*2 is leaf node
            }
            if (i * 2 + 1 > this.size) {
                break; // because i is in right place
            }
            if (this.backingArray[i * 2].compareTo(this.backingArray[i]) >= 0
                    && this.backingArray[i * 2 + 1].compareTo(this.backingArray[i]) >= 0) {
                break; // because i is now placed correctly
            }

            if (this.backingArray[i * 2].compareTo(this.backingArray[i * 2 + 1]) < 0) {
                T temp = this.backingArray[i];
                this.backingArray[i] = this.backingArray[i * 2];
                this.backingArray[i * 2] = temp;
                i = i * 2;
            } else {
                T temp = this.backingArray[i];
                this.backingArray[i] = this.backingArray[i * 2 + 1];
                this.backingArray[i * 2 + 1] = temp;
                i = i * 2 + 1;
            }

        }
        return result;
    }

    /**
     * Returns the backing array of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    public static void main(String[] args) {
        MinHeap<Integer> h = new MinHeap<>();
        h.add(13);
        h.add(12);
        h.add(11);
        h.add(10);
        h.add(9);
        h.add(8);
        h.add(7);
        h.add(6);
        h.add(5);
        h.add(4);
        h.add(3);
        h.add(2);
        h.add(1);
        h.add(0);
        System.out.println(h.remove());
        System.out.println(h.remove());
        System.out.println(h.remove());
        System.out.println(h.remove());
        System.out.println(h.remove());
        System.out.println(h.remove());
        System.out.println(h.remove());
        System.out.println(h.remove());
        System.out.println(h.remove());
        System.out.println(h.remove());
        System.out.println(h.remove());
        System.out.println(h.remove());
        System.out.println(h.remove());
        System.out.println(h.remove());
        System.out.println(h.remove());
    }
}