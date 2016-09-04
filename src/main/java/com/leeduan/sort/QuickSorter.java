package com.leeduan.sort;

import com.leeduan.utils.operators.RationalOperator;
import com.leeduan.utils.operators.RationalOperatorMath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Class that sorts a list of numbers using quick sort algorithm.
 * While sorting, keep a count of the number of comparisons.
 */
class QuickSorter<T> extends AbstractSorter<T> implements Sorter<T> {
    private final RationalOperator<T> operator;
    private final RationalOperatorMath<T> math;
    private final Pivot pivot;
    private Long comparisons;

    public enum Pivot {
        FIRST, LAST, MEDIAN_OF_THREE
    }

    public QuickSorter(List<T> list, RationalOperator<T> operator, Pivot pivot) {
        Objects.requireNonNull(list, "Cannot pass a null list");
        Objects.requireNonNull(operator, "Cannot pass a null operator");
        Objects.requireNonNull(pivot, "Cannot pass a null pivot");

        this.operator = operator;
        this.math = new RationalOperatorMath<>(operator);
        this.pivot = pivot;
        this.comparisons = 0L;

        // do not mutate argument list
        final List<T> unsortedList = new ArrayList<>(list);
        // calculate timing while sorting
        final long startTime = System.currentTimeMillis();
        // base case
        if (!unsortedList.isEmpty()) {
            quickSort(unsortedList, 0, unsortedList.size() - 1);
        }
        final long endTime = System.currentTimeMillis();
        this.sortedList = unsortedList;
        this.executionTime = endTime - startTime;
    }

    public Long getComparisons() {
        return comparisons;
    }

    private void quickSort(List<T> list, int lowIndex, int highIndex) {
        if (lowIndex < highIndex) {
            comparisons += (highIndex - lowIndex);
            final int pivotIndex = calculatePivot(list, lowIndex, highIndex);
            final int partitionIndex = partition(list, lowIndex, highIndex, pivotIndex);
            quickSort(list, lowIndex, partitionIndex - 1);
            quickSort(list, partitionIndex + 1, highIndex);
        }
    }

    private int partition(List<T> list, int lowIndex, int highIndex, int pivotIndex) {
        final T pivotItem = list.get(pivotIndex);
        Collections.swap(list, pivotIndex, lowIndex); // swap pivot item with low item before partitioning

        int i = lowIndex + 1;
        for (int j = i; j <= highIndex; j++) {
            if (operator.lessThan(list.get(j), pivotItem)) {
                Collections.swap(list, j, i);
                i++;
            }
        }

        final int newPivotIndex = i - 1;
        Collections.swap(list, lowIndex, newPivotIndex); // place pivot item in correct position

        return newPivotIndex;
    }

    private int calculatePivot(List<T> list, int lowIndex, int highIndex) {
        final int pivotIndex;
        switch (this.pivot) {
            case FIRST:
                pivotIndex = lowIndex;
                break;
            case LAST:
                pivotIndex = highIndex;
                break;
            case MEDIAN_OF_THREE:
                // Calculate the "middle" index. If odd length, round down. For example,
                // `4 5 6 7` has a middle of 5. Divide by 2 is sufficient since it rounds
                // dividing by int rounds down.
                final int middleIndex = highIndex - (highIndex - lowIndex + 1) / 2;
                pivotIndex = list.indexOf(math.median(list.get(lowIndex), list.get(middleIndex), list.get(highIndex)));
                break;
            default:
                throw new UnsupportedOperationException("Unsupported pivot");
        }

        return pivotIndex;
    }
}
