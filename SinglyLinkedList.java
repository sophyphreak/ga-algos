import java.util.NoSuchElementException;

/**
 * Your implementation of a Singly-Linked List.
 */
public class SinglyLinkedList<T> {

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private SinglyLinkedListNode<T> head;
    private SinglyLinkedListNode<T> tail;
    private int size;

    /*
     * Do not add a constructor.
     */

    /**
     * Adds the element to the front of the list.
     *
     * Method should run in O(1) time.
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new IllegalArgumentException();
        }
        SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<>(data);
        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
            size = 1;
            return;
        }
        newNode.setNext(this.head);
        this.head = newNode;
        size++;
        return;
    }

    /**
     * Adds the element to the back of the list.
     *
     * Method should run in O(1) time.
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (data == null) {
            throw new IllegalArgumentException();
        }
        SinglyLinkedListNode<T> newNode = new SinglyLinkedListNode<>(data);
        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
            size = 1;
            return;
        }
        this.tail.setNext(newNode);
        this.tail = newNode;
        this.size++;
        return;
    }

    /**
     * Removes and returns the first data of the list.
     *
     * Method should run in O(1) time.
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (this.head == null) {
            throw new NoSuchElementException();
        }
        if (this.head.getNext() == null) {
            T result = this.head.getData();
            this.head = null;
            this.tail = null;
            this.size = 0;
            return result;
        }
        T result = this.head.getData();
        this.head = this.head.getNext();
        this.size--;
        return result;
    }

    /**
     * Removes and returns the last data of the list.
     *
     * Method should run in O(n) time.
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (this.head == null) {
            throw new NoSuchElementException();
        }
        if (this.head.getNext() == null) {
            T result = this.head.getData();
            this.head = null;
            this.tail = null;
            this.size = 0;
            return result;
        }
        SinglyLinkedListNode<T> current = this.head;
        while (current.getNext().getNext() != null) {
            current = current.getNext();
        }
        T result = current.getNext().getData();
        current.setNext(null);
        this.tail = current;
        this.size--;
        return result;
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public SinglyLinkedListNode<T> getHead() {
        // DO NOT MODIFY THIS METHOD!
        return head;
    }

    /**
     * Returns the tail node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the tail of the list
     */
    public SinglyLinkedListNode<T> getTail() {
        // DO NOT MODIFY THIS METHOD!
        return tail;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    public void addAtIndex(int index, T data) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        var newNode = new SinglyLinkedListNode<T>(data);
        if (index == 0) {
            newNode.setNext(this.head);
            this.head = newNode;
            this.size++;
            if (this.size == 1)
                this.tail = newNode;
            return;
        }
        int currentIndex = 0;
        var current = this.head;
        while (currentIndex < index - 1) {
            currentIndex++;
            current = current.getNext();
        }
        newNode.setNext(current.getNext());
        current.setNext(newNode);
        if (index == this.size)
            this.tail = newNode;
        this.size++;
    }

    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();
        list.addToBack(0);
        list.addToBack(1);
        list.addToBack(2);
        list.addToBack(3);
        list.addToBack(4);
        list.addAtIndex(2, 10);
        var current = list.getHead();
        while (current != null) {
            System.out.println(current.getData());
            current = current.getNext();
        }
        // System.out.println(list.size);
        // System.out.println(list.getHead().getData());
        // System.out.println(list.getTail().getData());
        // System.out.println(list.removeFromFront());
        // System.out.println(list.removeFromFront());
        // System.out.println(list.removeFromFront());
        // System.out.println(list.removeFromFront());
        // System.out.println(list.removeFromFront());
        // System.out.println(list.size);
        // System.out.println(list.getHead());
        // System.out.println(list.getTail());
        // list.addToFront(0);
        // list.addToFront(1);
        // list.addToFront(2);
        // list.addToFront(3);
        // list.addToFront(4);
        // System.out.println(list.size);
        // System.out.println(list.getHead().getData());
        // System.out.println(list.getTail().getData());
        // System.out.println(list.removeFromBack());
        // System.out.println(list.removeFromBack());
        // System.out.println(list.removeFromBack());
        // System.out.println(list.removeFromBack());
        // System.out.println(list.removeFromBack());
        // System.out.println(list.size);
        // System.out.println(list.getHead());
        // System.out.println(list.getTail());
    }
}