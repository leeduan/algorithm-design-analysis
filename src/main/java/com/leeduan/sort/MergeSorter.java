package com.leeduan.sort;

import com.leeduan.utils.operators.RationalOperator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class that sorts a list of numbers using merge sort algorithm.
 * While sorting, keep a count of the number of inversions.
 */
class MergeSorter<T> extends AbstractSorter<T> implements Sorter<T> {
    private final RationalOperator<T> operator;
    private Long inversions;

    public MergeSorter(List<T> list, RationalOperator<T> operator) {
        Objects.requireNonNull(list, "Cannot pass a null list");
        Objects.requireNonNull(operator, "Cannot pass a null operator");

        this.operator = operator;
        this.inversions = 0L;

        // calculate timing while sorting
        final long startTime = System.currentTimeMillis();
        this.sortedList = mergeSort(list);
        final long endTime = System.currentTimeMillis();
        this.executionTime = endTime - startTime;
    }

    public Long getInversions() {
        return inversions;
    }

    private List<T> mergeSort(List<T> list) {
        final int halfIndex = list.size() / 2;
        // base case
        if (halfIndex == 0) {
            return list;
        }

        // divide list by equal-sized sub-lists, then recursively sort
        final List<T> leftList = mergeSort(list.subList(0, halfIndex));
        final List<T> rightList = mergeSort(list.subList(halfIndex, list.size()));

        // merge sorted sub-lists
        return merge(leftList, rightList);
    }

    // merge left and right sub-lists
    private List<T> merge(List<T> leftList, List<T> rightList) {
        final List<T> mergedList = new ArrayList<>();

        // append smaller or first item left in left or right list to sorted list
        while (isNotEmpty(leftList) && isNotEmpty(rightList)) {
            final T headLeft = head(leftList);
            final T headRight = head(rightList);
            if (operator.lessThan(headLeft, headRight)) {
                mergedList.add(headLeft);
                leftList = tail(leftList);
            } else {
                mergedList.add(headRight);
                rightList = tail(rightList);
                // increment inversions
                inversions = inversions + leftList.size();
            }
        }

        // append what is left either in left or right list
        mergedList.addAll(leftList.isEmpty() ? rightList : leftList);

        return mergedList;
    }

    private boolean isNotEmpty(List<T> list) {
        return !list.isEmpty();
    }

    private T head(List<T> list) {
        return list.get(0);
    }

    private List<T> tail(List<T> list) {
        return list.subList(1, list.size());
    }
}
