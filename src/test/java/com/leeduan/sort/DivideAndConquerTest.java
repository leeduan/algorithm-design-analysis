package com.leeduan.sort;

import static org.junit.Assert.assertEquals;

import com.leeduan.utils.operators.IntegerOperator;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DivideAndConquerTest {

    @Test
    public void testMergeSort() {
        final List<Integer> sorted = DivideAndConquer.INTEGER_LIST
                .stream()
                .sorted(Integer::compare)
                .collect(Collectors.toList());

        final MergeSorter<Integer> intSort = new MergeSorter<>(DivideAndConquer.INTEGER_LIST, new IntegerOperator());
        assertEquals("Sorted numbers equals stream sorted", sorted, intSort.getSortedList());
    }

    @Test
    public void testInversions() {
        final MergeSorter<Integer> smallIntSort = new MergeSorter<>(Arrays.asList(1, 3, 5, 2, 4, 6),
                        new IntegerOperator());
        assertEquals("Number of inversions is 3", new Long(3L), smallIntSort.getInversions());

        final MergeSorter<Integer> intSort = new MergeSorter<>(DivideAndConquer.INTEGER_LIST, new IntegerOperator());
        assertEquals("Number of inversions is 2407905288", new Long(2407905288L), intSort.getInversions());
    }

}
