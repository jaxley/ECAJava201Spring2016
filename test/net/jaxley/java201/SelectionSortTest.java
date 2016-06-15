package net.jaxley.java201;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by jaxley on 6/8/16.
 */
public class SelectionSortTest {
    @Test
    public void sort() throws Exception {

        int[] really_unsorted = new int[] {59,60,2,60,17,32,89,71,54,8,15,41,49,87,16,42,70};
        int[] expected_sorted = new int[] {2, 8, 15, 16, 17, 32, 41, 42, 49, 54, 59, 60, 60, 70, 71, 87, 89};

        Assert.assertArrayEquals(expected_sorted, SelectionSort.sort(really_unsorted));
    }
}