package net.jaxley.java201;

import java.util.Arrays;

/**
 * Created by jaxley on 6/15/16.
 */
public class MergeSorter {

    // in-class implementation
    public static int[] mergeSort(int[] input) {

        // if it's a size of one, we know the sorted result is itself
        int length = input.length;
        if (length == 1) {
            return input;
        }

        // else, split array into chunks, and recursively sort each
        int[] left = Arrays.copyOfRange(input, 0, length / 2);
        int[] right = Arrays.copyOfRange(input, length / 2, length);

        // recursively iterate through all of the blocks of data input to split them apart
        left = mergeSort(left);
        right = mergeSort(right);

        // Now, merge the split arrays back into sorted arrays, sorting along the way
        return doMergeSort(left, right);
    }

    // actually do the merge sort operation, comparing the left and right sorted arrays and returning the sorted combination of each
    private static int[] doMergeSort(int[] left, int[] right) {
        int leftPointer = 0;
        int leftLength = left.length;
        int rightPointer = 0;
        int rightLength = right.length;
        int resultLength = leftLength + rightLength;
        int result[] = new int[resultLength];
        int resultPointer = 0;

        // step through left array and compare against the right array values but stop when you exhaust values in either array
        while (leftPointer < leftLength && rightPointer < rightLength) {

            // if left value is < right value, store in result - check next right value
            // left = {0, 5, 10, 20}, right = { 1, 2, 3, 4, 6, 11, 19, 21}
            // 0 < 1 => result = {0}.  Move left pointer
            // 5 > 1 => result = {0, 1}.  Move right pointer.
            // 5 > 2 & 3 & 4 => result = {0, 1, 2, 3, 4}
            // 5 < 6 => result = {0, 1, 2, 3, 4, 5}
            // 10 > 6 => result = { ...
            // ...
            // otherwise, store all right values that are less than the left value.  Then, increment left pointer and continue loop
            if (left[leftPointer] < right[rightPointer]) {
                result[resultPointer++] = left[leftPointer++];
                // check right value next time through this loop against the next left value
            } else {
                // store the right value (it is less) and then check the next right value against the current left value
                result[resultPointer++] = right[rightPointer++];
            }
        }

        // take any remaining values from the right array and add them to the result
        while (rightPointer < rightLength) {
            result[resultPointer++] = right[rightPointer++];
        }
        // likewise, take any remaining values from the left array and add them to the result
        while (leftPointer < leftLength) {
            result[resultPointer++] = left[leftPointer++];
        }

        return result;
    }
}
