package net.jaxley.java201;

import com.skoohgoli.java201.MyLinkedList;
import com.skoohgoli.java201.Node;

public class Lesson7Assignment {

    public int[] iterativeSort(int[] input) {
        if (input == null) {
            return input;
        }
        int length = input.length;
        for (int i = 0; i < length; i++) {
            int minIndex = i;

            //find the smallest number in the sub-array
            for (int j = i; j < length; j++) {
                if (input[j] < input[minIndex]) {
                    minIndex = j;
                }
            }

            //if the smallest number is not at input[i], swap so that it is
            if (minIndex != i) {
                int temp = input[i];
                input[i] = input[minIndex];
                input[minIndex] = temp;
            }
        }
        return input;
    }



    public MyLinkedList linkedListSort(MyLinkedList input) {
        return null;
    }

    // public interface that uses private recursive calls
    public int[] recursiveSort(int[] input) {
        return recursiveSort(0, input);
    }

    //You may want to change the arguments for the method, if it makes things easier
    private int[] recursiveSort(int startIdx, int[] input) {
        int[] sorted = input;
        if (input == null) {
            return input;
        }
        int length = input.length;
        // each execution of recursiveSort will process just the first character swap and then use recursion on the remaining unsorted array
        // this effectively replaces the outer loop with recursive calls instead
        // input = {1, 0, 3}
        // first call:  swap 1 and 0. Then sort {1, 3}

        int minIndex = startIdx;
        //find the smallest number in the sub-array
        for (int j = startIdx; j < length; j++) {
            if (input[j] < input[minIndex]) {
                minIndex = j;
            }
        }

        //if the smallest number is not at input[startIdx], swap so that it is
        if (minIndex != startIdx) {
            int temp = input[startIdx];
            input[startIdx] = input[minIndex];
            input[minIndex] = temp;
        }
        // now, process the rest of the unsorted data, if we're not already at the end of the array
        if (startIdx != length) {
            sorted = recursiveSort(startIdx + 1, input);
        }
        return sorted;
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
        Lesson7Assignment sorter = new Lesson7Assignment();

        int[] input = {98, 7654, 2, 3456, 66};
        int[] expected = {2, 66, 98, 3456, 7654};

        int[] result = sorter.iterativeSort(input);
        if (!isSame(expected, result)) {
            throw new RuntimeException("Array was not iteratively sorted properly: " + result);
        }

        result = sorter.recursiveSort(input);
        if (!isSame(expected, result)) {
            throw new RuntimeException("Array was not recursively sorted properly: " + result);
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

        MyLinkedList linkedResult = sorter.linkedListSort(linkedInput);

        Node resultNode = linkedResult.head;
        Node expectedNode = linkedExpected.head;
        while (resultNode != null && expectedNode != null) {
            if (resultNode.value != expectedNode.value) {
                throw new RuntimeException("List was not recursively sorted properly. Expected " + expectedNode.value
                        + "but got " + resultNode.value);
            }
            resultNode = resultNode.next;
            expectedNode = expectedNode.next;
        }

    }
}
