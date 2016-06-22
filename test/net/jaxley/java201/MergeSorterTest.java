package net.jaxley.java201;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jaxley on 6/15/16.
 */
public class MergeSorterTest {
    @Test
    public void mergeSort() throws Exception {

        int[] input = new int[] {3, 6, 5, 1, 4};
        int[] expected = new int[] {1, 3, 4, 5, 6};

        Assert.assertArrayEquals(expected, MergeSorter.mergeSort(input));
    }

}