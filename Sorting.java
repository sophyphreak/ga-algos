import java.util.Comparator;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Your implementation of various divide & conquer sorting algorithms.
 */

public class Sorting {

    /**
     * Implement merge sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of: O(n log n)
     * And a best case running time of: O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * Hint: You may need to create a helper method that merges two arrays
     * back into the original T[] array. If two data are equal when merging,
     * think about which subarray you should pull from first.
     *
     * You may assume that the passed in array and comparator are both valid
     * and will not be null.
     *
     * @param <T>        Data type to sort.
     * @param arr        The array to be sorted.
     * @param comparator The Comparator used to compare the data in arr.
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        if (arr.length < 2) {
            return;
        }
        int length = arr.length;
        int midIndex = arr.length / 2;
        T[] left = sliceArray(arr, 0, midIndex);
        T[] right = sliceArray(arr, midIndex, length);
        mergeSort(left, comparator);
        mergeSort(right, comparator);
        int i = 0;
        int j = 0;
        while (i < left.length && j < right.length) {
            if (comparator.compare(left[i], right[j]) <= 0) {
                arr[i + j] = left[i];
                i++;
            } else {
                arr[i + j] = right[j];
                j++;
            }
        }
        while (i < left.length) {
            arr[i + j] = left[i];
            i++;
        }
        while (j < right.length) {
            arr[i + j] = right[j];
            j++;
        }
    }

    private static <T> T[] sliceArray(T[] arr, int start, int end) {
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[end - start];
        for (int i = 0; i < end - start; i++) {
            result[i] = arr[i + start];
        }
        return result;
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of: O(kn)
     * And a best case running time of: O(kn)
     *
     * Feel free to make an initial O(n) passthrough of the array to
     * determine k, the number of iterations you need.
     *
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     *
     * You may use an ArrayList or LinkedList if you wish, but it should only
     * be used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with merge sort. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     *
     * Do NOT use anything from the Math class except Math.abs().
     *
     * You may assume that the passed in array is valid and will not be null.
     *
     * @param arr The array to be sorted.
     */
    public static void lsdRadixSort(int[] arr) {
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
        @SuppressWarnings("unchecked")
        LinkedList<Integer>[] buckets = new LinkedList[19];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<Integer>();
        }
        int k = -1;
        for (int i = 0; i < arr.length; i++) {
            if (String.valueOf(arr[i]).length() > k) {
                if (Character.toString(String.valueOf(arr[i]).charAt(0)) == "-") {
                    k = String.valueOf(arr[i]).length() - 1;
                } else {
                    k = String.valueOf(arr[i]).length();
                }
            }
        }
        for (int iteration = 0; iteration < k; iteration++) {
            for (int i = 0; i < arr.length; i++) {
                int digit;
                if (iteration == 9) {
                    digit = arr[i] / pow(iteration);
                } else {
                    digit = arr[i] % pow(iteration + 1) / pow(iteration);
                }
                buckets[digit + 9].add(arr[i]);
            }
            int idx = 0;
            for (LinkedList<Integer> bucket : buckets) {
                while (bucket.size() != 0) {
                    arr[idx] = bucket.remove();
                    idx++;
                }
            }
        }
    }

    private static int pow(int n) {
        int result = 1;
        for (int i = 0; i < n; i++) {
            result *= 10;
        }
        return result;
    }

}