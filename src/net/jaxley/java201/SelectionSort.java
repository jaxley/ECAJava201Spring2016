package net.jaxley.java201;

import java.util.LinkedList;

/**
 * Created by jaxley on 6/8/16.
 */
public class SelectionSort {
    // sort from smallest to largest iteratively (done in class)
    public static int[] sort(int[] input) {
        // iterate over the left half of the array to find
        for (int n=0; n < input.length; n++) {
            int sortedPosition = n;
            int currentLowestPosition = n;
            System.out.println(String.format("Position %d, value %d", sortedPosition, input[sortedPosition]));
            // search in the right portion (unsorted) of the array for the lowest item
            for (int i = n; i < input.length; i++) {
                System.out.println(String.format("**** Unsorted side: %d, value %d", i, input[i]));
                if (input[i] < input[sortedPosition] && input[i] < input[currentLowestPosition]) {
                    System.out.println(String.format("Found lower value %d < %d", input[i], input[sortedPosition]));
                    currentLowestPosition = i;
                }
            }
            // do the swap
            // swap positions
            int original = input[sortedPosition];
            input[sortedPosition] = input[currentLowestPosition];
            input[currentLowestPosition] = original;
        }
        printArray(input);
        return input;
    }

    private static void printArray(int[] anArray) {
        for (int i = 0; i < anArray.length; i++) {
            if (i > 0) {
                System.out.print(", ");
            }
            System.out.print(anArray[i]);
        }
        System.out.println("");
    }
}
