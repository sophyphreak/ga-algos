import java.util.Comparator;

/**
 * Your implementation of various iterative sorting algorithms.
 */
public class Sorting1 {
    private static <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Implement bubble sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n)
     *
     * NOTE: You should implement bubble sort with the last swap optimization.
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void bubbleSort(T[] arr, Comparator<T> comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        int stopIndex = arr.length - 1;
        while (stopIndex != 0) {
            int i = 0;
            int lastSwapped = 0;
            while (i < stopIndex) {
                if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                    swap(arr, i, i + 1);
                    lastSwapped = i;
                }
                i++;
            }
            stopIndex = lastSwapped;
        }
    }

    /**
     * Implement selection sort.
     *
     * It should be:
     * in-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n^2)
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        for (int n = arr.length - 1; n > 0; n--) {
            int maxIndex = n;
            for (int i = 0; i < n; i++) {
                if (comparator.compare(arr[i], arr[maxIndex]) > 0) {
                    maxIndex = i;
                }
            }
            swap(arr, n, maxIndex);
        }
    }

    /**
     * Implement insertion sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of: O(n^2)
     * And a best case running time of: O(n)
     *
     * You may assume that the passed in array and comparator
     * are both valid and will never be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array that must be sorted after the method runs.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        for (int n = 1; n < arr.length; n++) {
            int i = n;
            while (i != 0 && comparator.compare(arr[i], arr[i - 1]) < 0) {
                swap(arr, i, i - 1);
                i--;
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
        Comparator<Integer> comp = new Comparator<Integer>() {
            @Override
            public int compare(Integer int1, Integer int2) {
                return int1.compareTo(int2);
            }
        };
        insertionSort(arr, comp);
    }
}