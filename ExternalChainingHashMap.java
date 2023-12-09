import java.util.NoSuchElementException;

/**
 * Your implementation of a ExternalChainingHashMap.
 */
public class ExternalChainingHashMap<K, V> {

    /*
     * The initial capacity of the ExternalChainingHashMap when created with the
     * default constructor.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * The max load factor of the ExternalChainingHashMap.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final double MAX_LOAD_FACTOR = 0.67;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private ExternalChainingMapEntry<K, V>[] table;
    private int size;

    /**
     * Constructs a new ExternalChainingHashMap with an initial capacity of
     * INITIAL_CAPACITY.
     */
    public ExternalChainingHashMap() {
        // DO NOT MODIFY THIS METHOD!
        table = (ExternalChainingMapEntry<K, V>[]) new ExternalChainingMapEntry[INITIAL_CAPACITY];
    }

    /**
     * Adds the given key-value pair to the map. If an entry in the map
     * already has this key, replace the entry's value with the new one
     * passed in.
     *
     * In the case of a collision, use external chaining as your resolution
     * strategy. Add new entries to the front of an existing chain, but don't
     * forget to check the entire chain for duplicate keys first.
     *
     * If you find a duplicate key, then replace the entry's value with the new
     * one passed in. When replacing the old value, replace it at that position
     * in the chain, not by creating a new entry and adding it to the front.
     *
     * Before actually adding any data to the HashMap, you should check to
     * see if the table would violate the max load factor if the data was
     * added. Resize if the load factor (LF) is greater than max LF (it is
     * okay if the load factor is equal to max LF). For example, let's say
     * the table is of length 5 and the current size is 3 (LF = 0.6). For
     * this example, assume that no elements are removed in between steps.
     * If another entry is attempted to be added, before doing anything else,
     * you should check whether (3 + 1) / 5 = 0.8 is larger than the max LF.
     * It is, so you would trigger a resize before you even attempt to add
     * the data or figure out if it's a duplicate. Be careful to consider the
     * differences between integer and double division when calculating load
     * factor.
     *
     * When regrowing, resize the length of the backing table to
     * (2 * old length) + 1. You should use the resizeBackingTable method to do so.
     *
     * @param key   The key to add.
     * @param value The value to add.
     * @return null if the key was not already in the map. If it was in the
     *         map, return the old value associated with it.
     * @throws java.lang.IllegalArgumentException If key or value is null.
     */
    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new java.lang.IllegalArgumentException("Key or value is null.");
        }
        // 1) check if load factor is greather than max load factor, if it is, increase
        // the size of backing array by (2 * old length) + 1
        if ((double) (this.size + 1) / this.table.length > MAX_LOAD_FACTOR) {
            resizeBackingTable((2 * this.table.length) + 1);
        }
        // 2) reduce key to index with % length, nagivate to index
        int index = Math.abs(key.hashCode() % this.table.length);

        // 3) check if there is a duplicate in there. If there is, replace value with
        // current value
        var current = this.table[index];
        while (current != null) {
            if (current.getKey().equals(key)) {
                V oldValue = current.getValue();
                current.setValue(value);
                return oldValue;
            }
            current = current.getNext();
        }
        var oldHead = this.table[index];
        this.table[index] = new ExternalChainingMapEntry<K, V>(key, value, oldHead);
        this.size++;
        // 4) if duplicate, return old value, otherwise return null
        return null;
    }

    /**
     * Removes the entry with a matching key from the map.
     *
     * @param key The key to remove.
     * @return The value associated with the key.
     * @throws java.lang.IllegalArgumentException If key is null.
     * @throws java.util.NoSuchElementException   If the key is not in the map.
     */
    public V remove(K key) {
        if (key == null) {
            throw new java.lang.IllegalArgumentException("Key is null.");
        }
        int index = Math.abs(key.hashCode() % this.table.length);

        if (this.table[index] == null) {
            throw new java.util.NoSuchElementException("The key is not in the map.");
        }

        // case where matching key is first in linked list
        if (this.table[index].getKey().equals(key)) {
            var value = this.table[index].getValue();
            this.table[index] = this.table[index].getNext();
            this.size--;
            return value;
        }

        var previous = this.table[index];
        var current = this.table[index].getNext();
        while (current != null) {
            if (current.getKey().equals(key)) {
                var value = current.getValue();
                previous.setNext(current.getNext());
                this.size--;
                return value;
            }
            previous = current;
            current = current.getNext();
        }

        throw new java.util.NoSuchElementException("The key is not in the map.");
    }

    /**
     * Helper method stub for resizing the backing table to length.
     *
     * This method should be called in put() if the backing table needs to
     * be resized.
     *
     * You should iterate over the old table in order of increasing index and
     * add entries to the new table in the order in which they are traversed.
     *
     * Since resizing the backing table is working with the non-duplicate
     * data already in the table, you won't need to explicitly check for
     * duplicates.
     *
     * Hint: You cannot just simply copy the entries over to the new table.
     *
     * @param Length The new length of the backing table.
     */
    private void resizeBackingTable(int length) {
        var newTable = (ExternalChainingMapEntry<K, V>[]) new ExternalChainingMapEntry[length];
        for (ExternalChainingMapEntry<K, V> entry : this.table) {
            var current = entry;
            while (current != null) {
                int index = Math.abs(current.getKey().hashCode() % length);
                ExternalChainingMapEntry<K, V> node = new ExternalChainingMapEntry<K, V>(current.getKey(),
                        current.getValue(), newTable[index]);
                newTable[index] = node;
                current = current.getNext();
            }
        }
        this.table = newTable;
    }

    /**
     * Returns the table of the map.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The table of the map.
     */
    public ExternalChainingMapEntry<K, V>[] getTable() {
        // DO NOT MODIFY THIS METHOD!
        return table;
    }

    /**
     * Returns the size of the map.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return The size of the map.
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    public static void main(String[] args) {
        System.out.println("Starting!");
        var a = new ExternalChainingHashMap<>();
        assert a.table.length == INITIAL_CAPACITY : "initial capacity not correct";
        assert a.size == 0;

        // TEST PUT 1

        assert a.put(5, 5) == null;
        assert (int) a.table[5].getKey() == 5 : "key 5 is not in index 5";
        assert (int) a.table[5].getValue() == 5 : "value 5 is not in index 5";
        assert a.size == 1;

        // TEST PUT 2

        var b = new ExternalChainingHashMap<>();
        b.put(19, 19);
        b.put(6, 6);
        b.put(8, 8);
        b.put(11, 11);
        b.put(25, 25);
        assert (int) b.table[6].getKey() == 6;
        assert (int) b.table[6].getValue() == 6;
        assert (int) b.table[6].getNext().getKey() == 19;
        assert (int) b.table[6].getNext().getValue() == 19;
        assert (int) b.table[8].getKey() == 8;
        assert (int) b.table[8].getValue() == 8;
        assert (int) b.table[11].getKey() == 11;
        assert (int) b.table[11].getValue() == 11;
        assert (int) b.table[12].getKey() == 25;
        assert (int) b.table[12].getValue() == 25;
        assert b.size == 5;

        b.put(32, 32);
        assert (int) b.table[6].getKey() == 32;
        assert (int) b.table[6].getValue() == 32;
        assert (int) b.table[6].getNext().getKey() == 6;
        assert (int) b.table[6].getNext().getValue() == 6;
        assert (int) b.table[6].getNext().getNext().getKey() == 19;
        assert (int) b.table[6].getNext().getNext().getValue() == 19;
        assert b.size == 6;

        // TEST PUT 3

        var c = new ExternalChainingHashMap<>();
        c.put(19, 19);
        c.put(6, 6);
        c.put(8, 8);
        c.put(37, 37);
        c.put(24, 23);
        c.put(11, 11);

        assert (int) c.table[6].getKey() == 6 : "Key at head of index 6 is not 6";
        assert (int) c.table[6].getValue() == 6;
        assert (int) c.table[6].getNext().getKey() == 19;
        assert (int) c.table[6].getNext().getValue() == 19;
        assert (int) c.table[8].getKey() == 8;
        assert (int) c.table[8].getValue() == 8;
        assert (int) c.table[11].getKey() == 11;
        assert (int) c.table[11].getValue() == 11;
        assert (int) c.table[11].getNext().getKey() == 24;
        assert (int) c.table[11].getNext().getValue() == 23;
        assert (int) c.table[11].getNext().getNext().getKey() == 37;
        assert (int) c.table[11].getNext().getNext().getValue() == 37;
        assert c.size == 6 : "size does not equal 6";

        assert (int) c.put(24, 24) == 23;

        assert (int) c.table[11].getNext().getKey() == 24;
        assert (int) c.table[11].getNext().getValue() == 24;
        assert c.size == 6;

        // TEST PUT 4 - Exceptions
        try {
            a.put(null, 0);
        } catch (IllegalArgumentException e) {
            assert e.getLocalizedMessage() == "Key or value is null.";
        }
        try {
            a.put(0, null);
        } catch (IllegalArgumentException e) {
            assert e.getLocalizedMessage() == "Key or value is null.";
        }

        // TEST REMOVE 1

        var d = new ExternalChainingHashMap<>();
        d.put(4, 4);

        assert (int) d.table[4].getKey() == 4;
        assert (int) d.table[4].getValue() == 4;
        assert d.size == 1;

        assert (int) d.remove(4) == 4;

        assert d.table[4] == null;
        assert d.size == 0 : "expected size to be 0, but it is not. Perhaps remove didn't decrement the size";

        // TEST REMOVE 2

        var e = new ExternalChainingHashMap<>();
        e.put(19, 19);
        e.put(6, 6);
        e.put(8, 8);
        e.put(37, 37);
        e.put(24, 24);
        e.put(11, 11);

        assert (int) e.table[6].getKey() == 6;
        assert (int) e.table[6].getNext().getKey() == 19;
        assert (int) e.table[8].getKey() == 8;
        assert (int) e.table[11].getKey() == 11;
        assert (int) e.table[11].getNext().getKey() == 24;
        assert (int) e.table[11].getNext().getNext().getKey() == 37;
        assert e.size == 6;

        e.remove(11);

        assert (int) e.table[11].getKey() == 24;
        assert (int) e.table[11].getNext().getKey() == 37;
        assert e.size == 5;

        // TEST REMOVE EXCEPTIONS

        try {
            e.remove(null);
        } catch (IllegalArgumentException ex) {
            assert ex.getLocalizedMessage() == "Key is null.";
        }
        try {
            e.remove(0); // in index where nothing exists
        } catch (NoSuchElementException ex) {
            assert ex.getLocalizedMessage() == "The key is not in the map.";
        }
        try {
            e.remove(7); // in index where something does exist but not that
        } catch (NoSuchElementException ex) {
            assert ex.getLocalizedMessage() == "The key is not in the map.";
        }

        var f = new ExternalChainingHashMap<>();

        f.put(0, 0);
        f.put(13, 13);
        f.put(26, 26);
        f.put(27, 27);
        f.put(1, 1);
        f.put(14, 14);
        f.put(2, 2);
        f.put(15, 15);

        assert f.table.length == 13;
        assert f.size == 8;
        assert (int) f.table[0].getKey() == 26;
        assert (int) f.table[0].getNext().getKey() == 13;
        assert (int) f.table[0].getNext().getNext().getKey() == 0;
        assert f.table[0].getNext().getNext().getNext() == null;
        assert (int) f.table[1].getKey() == 14;
        assert (int) f.table[1].getNext().getKey() == 1;
        assert (int) f.table[1].getNext().getNext().getKey() == 27;
        assert f.table[1].getNext().getNext().getNext() == null;
        assert (int) f.table[2].getKey() == 15;
        assert (int) f.table[2].getNext().getKey() == 2;
        assert f.table[2].getNext().getNext() == null;

        f.put(3, 3);

        assert f.table.length == 27;
        assert f.size == 9;
        assert (int) f.table[0].getKey() == 27;
        assert (int) f.table[0].getNext().getKey() == 0;
        assert (int) f.table[1].getKey() == 1;
        assert (int) f.table[2].getKey() == 2;
        assert (int) f.table[3].getKey() == 3;
        assert f.table[3].getNext() == null;
        assert f.table[4] == null;
        assert (int) f.table[13].getKey() == 13;
        assert (int) f.table[14].getKey() == 14;
        assert (int) f.table[15].getKey() == 15;
        assert (int) f.table[26].getKey() == 26;

        assert (int) f.remove(0) == 0;
        assert (int) f.remove(26) == 26;
        assert (int) f.remove(13) == 13;
        assert (int) f.remove(1) == 1;
        assert (int) f.remove(27) == 27;

        assert f.size == 4;
        assert f.table.length == 27;

        // System.out.println(e.remove(26));
        // System.out.println(e.remove(13));
        // System.out.println(e.remove(1));
        // System.out.println(e.remove(27));

        // System.out.println("");

        // for (var entry : f.table) {
        // var current = entry;
        // while (current != null) {
        // System.out.println(current);
        // current = current.getNext();
        // }
        // System.out.println(current);
        // System.out.println("");

        // }
        // System.out.printf("backing array length: %s\n\n", f.table.length);

        // TEST REMOVE EDGE CASE

        var g = new ExternalChainingHashMap<>();
        g.put(0, 0);
        g.put(13, 13);
        g.put(26, 26);

        g.remove(26);
        assert (int) g.table[0].getKey() == 13;
        assert (int) g.table[0].getNext().getKey() == 0;
        assert g.table[0].getNext().getNext() == null;

        var h = new ExternalChainingHashMap<>();
        h.put(0, 0);
        h.put(13, 13);
        h.put(26, 26);

        h.remove(13);
        assert (int) h.table[0].getKey() == 26;
        assert (int) h.table[0].getNext().getKey() == 0;
        assert h.table[0].getNext().getNext() == null;

        var i = new ExternalChainingHashMap<>();
        i.put(0, 0);
        i.put(13, 13);
        i.put(26, 26);

        i.remove(0);
        assert (int) i.table[0].getKey() == 26;
        assert (int) i.table[0].getNext().getKey() == 13;
        assert i.table[0].getNext().getNext() == null;

        System.out.println("Done!");
    }
    // [Executed at: Wed Jul 19 23:17:58 PDT 2023]

    // ============================================================
    // ExternalChainingHashMap.java successfully compiled.
    // ============================================================
    // Tests Passed: 24 / 25

    // [Test Failure: equals] [-0.4] : equals() was not used correctly when testing
    // the following method(s): put, remove.

    // Score: 9.6 / 10.0
    // ============================================================

    // [Executed at: Wed Jul 19 23:48:51 PDT 2023]

    // ============================================================
    // ExternalChainingHashMap.java successfully compiled.
    // ============================================================
    // Tests Passed: 23 / 25

    // [Test Failure: remove] [-0.4] : NoSuchElementException not thrown when
    // attempting to remove an element (with chaining) not in the HashMap.

    // [Test Failure: validSize] [-0.4] : Size variable could not be validated for
    // the following method(s) due to early test failure(s): remove.

    // Score: 9.2 / 10.0
    // ============================================================
}