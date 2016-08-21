package com.leeduan.sort;

import static org.junit.Assert.assertEquals;

import com.leeduan.sort.QuickSorter.Pivot;
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
    public void testMergeSortInversions() {
        final MergeSorter<Integer> smallIntSort = new MergeSorter<>(Arrays.asList(1, 3, 5, 2, 4, 6),
                        new IntegerOperator());
        assertEquals("Number of inversions is 3", new Long(3L), smallIntSort.getInversions());

        final MergeSorter<Integer> intSort = new MergeSorter<>(DivideAndConquer.INTEGER_LIST, new IntegerOperator());
        assertEquals("Number of inversions is 2407905288", new Long(2407905288L), intSort.getInversions());
    }

    @Test
    public void testQuickSort() {
        final QuickSorter<Integer> smallIntSort = new QuickSorter<>(Arrays.asList(3, 8, 2, 5, 1, 4, 7, 6),
                new IntegerOperator(), QuickSorter.Pivot.FIRST);
        assertEquals("Small sorted numbers match", Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8), smallIntSort.getSortedList());
    }

    @Test
    public void testQuickSortFirst() {
        final List<Integer> sorted = DivideAndConquer.QUICK_LIST
                .stream()
                .sorted(Integer::compare)
                .collect(Collectors.toList());

        final QuickSorter<Integer> intSort = new QuickSorter<>(DivideAndConquer.QUICK_LIST, new IntegerOperator(),
                        Pivot.FIRST);
        assertEquals("Sorted numbers equals stream sorted", sorted, intSort.getSortedList());
    }

    @Test
    public void testQuickSortComparisonsFirst() {
        final QuickSorter<Integer> intSort = new QuickSorter<>(DivideAndConquer.QUICK_LIST, new IntegerOperator(),
                        Pivot.FIRST);
        assertEquals("Number of conversions is 162085", new Long(162085L), intSort.getComparisons());
    }

    @Test
    public void testQuickSortLast() {
        final List<Integer> sorted = DivideAndConquer.QUICK_LIST
                .stream()
                .sorted(Integer::compare)
                .collect(Collectors.toList());

        final QuickSorter<Integer> intSort = new QuickSorter<>(DivideAndConquer.QUICK_LIST, new IntegerOperator(),
                        Pivot.LAST);
        assertEquals("Sorted numbers equals stream sorted", sorted, intSort.getSortedList());
    }

    @Test
    public void testQuickSortComparisonsLast() {
        final QuickSorter<Integer> intSort = new QuickSorter<>(DivideAndConquer.QUICK_LIST, new IntegerOperator(),
                Pivot.LAST);
        assertEquals("Number of conversions is 164123", new Long(164123L), intSort.getComparisons());
    }

    @Test
    public void testQuickSortMedianOfThree() {
        final List<Integer> sorted = DivideAndConquer.QUICK_LIST
                .stream()
                .sorted(Integer::compare)
                .collect(Collectors.toList());

        final QuickSorter<Integer> intSort = new QuickSorter<>(DivideAndConquer.QUICK_LIST, new IntegerOperator(),
                Pivot.MEDIAN_OF_THREE);
        assertEquals("Sorted numbers equals stream sorted", sorted, intSort.getSortedList());
    }

    @Test
    public void testQuickSortComparisonsMedianOfThree() {
        final QuickSorter<Integer> intSort = new QuickSorter<>(DivideAndConquer.QUICK_LIST, new IntegerOperator(),
                Pivot.MEDIAN_OF_THREE);
        assertEquals("Number of conversions is 138382", new Long(138382L), intSort.getComparisons());
    }

}
