package net.jaxley.java201;


import com.skoohgoli.java201.MyLinkedList;
import com.skoohgoli.java201.Node;

import java.util.Arrays;

public class Lesson8Assignment {

    public int[] mergeSort(int[] input) {
        int length = input.length;

        if (length == 1) {
            return input;
        }

        //Keep on splitting the case down to the smallest version (length 1)
        int[] left = mergeSort(Arrays.copyOfRange(input, 0, length/2));
        int[] right = mergeSort(Arrays.copyOfRange(input, length/2, length));

        return merge(left, right);
    }

    private int[] merge(int[] left, int[] right) {
        int leftLength = left.length;
        int rightLength = right.length;

        int leftIndex = 0;
        int rightIndex = 0;

        int[] mergedResult = new int[left.length + right.length];
        int mergedResultIndex = 0;

        //The main merging. Step through the elements one at a time
        while (leftIndex < leftLength && rightIndex < rightLength) {
            if (left[leftIndex] < right[rightIndex]) {
                mergedResult[mergedResultIndex] = left[leftIndex];
                leftIndex++;
            } else {
                mergedResult[mergedResultIndex] = right[rightIndex];
                rightIndex++;
            }
            mergedResultIndex++;
        }

        //If there are any leftover elements in the array, merge them together
        //This happens when the left array and the right array are not the same size.
        if (leftIndex == leftLength) {
            while (rightIndex < rightLength) {
                mergedResult[mergedResultIndex] = right[rightIndex];
                rightIndex++;
                mergedResultIndex++;
            }
        }
        if (rightIndex == rightLength) {
            while (leftIndex < leftLength) {
                mergedResult[mergedResultIndex] = left[leftIndex];
                leftIndex++;
                mergedResultIndex++;
            }
        }
        return mergedResult;
    }

    public int[] inPlaceMergeSort(int[] input) {
        //The public function should stay the same.
        // You can create inner recursive functions though

        // recursively divide the array into chunks
        // else, split array into chunks, and recursively sort each

        // kick off the recursive sort
        inPlaceMergeSort(input, 0, input.length - 1);

        // input reference passed by value has allowed input to be changed in-place in recursive calls. Just return it.
        return input;
    }

    // internal inPlaceMergeSort function that processes sub-sections of the input array
    private void inPlaceMergeSort(int[] input, int start, int end) {

        if (end-start+1 == 1) {
            return;
        }

        // e.g. start = 0, length = 6 (end = 6)
        // leftStart = 0; leftEnd = 6 / 2 + 0 = 3;
        // rightStart = leftEnd + 1 = 4;
        // rightEnd = start + length = 6;
        // e.g.
        // start = 4, length = 6 (end = 10)
        // leftStart = 4 (start); leftEnd = 6 / 2 + 4 = 7
        // rightStart = leftEnd + 1 = 8
        // rightEnd = start + length = 10;
        if (end-start+1 == 2) {
            // two unsorted elements - sort each
            if (input[end]<input[start]) {
                swap(input, start, end);
            }
        } else {
            // need to continue splitting before merging

            int leftStart = start;
            int leftEnd = (end - start) / 2 + start - 1;
            int rightStart = leftEnd + 1;
            int rightEnd = end;

            // recursively split
            inPlaceMergeSort(input, leftStart, leftEnd);
            inPlaceMergeSort(input, rightStart, rightEnd);

            doInPlaceMerge(input, leftStart, leftEnd, rightStart, rightEnd);
        }
    }
    
    // do an in-place merge directly in the input array.
    private void doInPlaceMerge(int[] input, int leftStart, int leftEnd, int rightStart, int rightEnd) {
        int leftPointer = leftStart;
        int rightPointer = rightStart;

        // step through left array and compare against the right array values but stop when you exhaust values in either array
        // something is wrong with loop termination - leftPointer can exceed leftEnd before we deal with the rest of the right-hand-side
        while (leftPointer <= leftEnd && rightPointer <= rightEnd) {

            // if left value is < right value, store in result - check next right value
            // split
            // input = {98, 7654, 2, 3456, 66}
            // left = {98, 7654}, right = {2, 3456, 66}
            // left = {98} right = {7654}
            // left = {2}, right = {3456, 66}
            // merge
            // {2}, {3456, 66} => {98, 7654, 2, 66, 3456}
            // {98, 7654} => {98, 7654, 2, 66, 3456}
            // {98, 7654}, {2, 66, 3456} => {2, 7654, 98, 66, 3456} => {2, 98, 7654, 66, 3456} => {} => {2, 66, 98, 3456, 7654}; 98/2 swap; 7654 / 98 swap;
            // otherwise, store all right values that are less than the left value.  Then, increment left pointer and continue loop
            if (input[leftPointer] < input[rightPointer]) {
                // leave it in place and move on to next value
                leftPointer++;
                // check right value next time through this loop against the next left value
            } else {
                // store the right value (it is less) and then check the next right value against the current left value
                // swap the values
                swap(input, leftPointer, rightPointer);
                // we didn't necessarily put the larger value in the proper place on the right. Since right was sorted, we need to make it sorted again
                int n = rightPointer;
                while (n++ < rightEnd && input[n] < input[rightPointer]) {
                    swap(input, n, n-1);
                }
            }
        }
    }

    private void swap(int[] input, int leftPosition, int rightPosition) {
        int oldLeftValue = input[leftPosition];
        input[leftPosition] = input[rightPosition];
        input[rightPosition] = oldLeftValue;
    }

    public MyLinkedList mergeLinkedListSort(MyLinkedList head) {
        return null;
    }

    private static boolean isSame(int[] expected, int[] result) {
        if (result.length != expected.length) {
            return false;
        }
        for (int i = 0; i < result.length; i++) {
            if (result[i] != expected[i]) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
        Lesson8Assignment sorter = new Lesson8Assignment();

        int[] input = {98, 7654, 2, 3456, 66};
        int[] expected = {2, 66, 98, 3456, 7654};

        int[] result = sorter.mergeSort(input);
        if (!isSame(expected, result)) {
            throw new RuntimeException("Array was not recursively sorted properly: " + result);
        }

        int[] input2 = {98, 7654, 2, 3456, 66};
        result = sorter.inPlaceMergeSort(input2);
        if (!isSame(expected, result)) {
            throw new RuntimeException("Array was not in-place sorted properly: " + result);
        }

        MyLinkedList linkedInput = new MyLinkedList();
        linkedInput.add(new Node(98))
                .add(new Node(7654))
                .add(new Node(2))
                .add(new Node(3456))
                .add(new Node(66));

        MyLinkedList linkedExpected = new MyLinkedList();
        linkedExpected.add(new Node(2))
                .add(new Node(66))
                .add(new Node(98))
                .add(new Node(3456))
                .add(new Node(7654));

        MyLinkedList linkedResult = sorter.mergeLinkedListSort(linkedInput);

        Node resultNode = linkedResult.head;
        Node expectedNode = linkedExpected.head;
        while (resultNode != null && expectedNode != null) {
            if (resultNode.value != expectedNode.value) {
                throw new RuntimeException("List was not sorted properly. Expected " + expectedNode.value
                        + "but got " + resultNode.value);
            }
            resultNode = resultNode.next;
            expectedNode = expectedNode.next;
        }

    }

}
