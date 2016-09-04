package com.leeduan.sort;

import java.util.List;

/**
 * Generic interface of a sorting algorithm.
 */
public interface Sorter<T> {

    /**
     * Get the sorted list.
     * @return
     */
    List<T> getSortedList();

    /**
     * Get the time taken to sort list.
     * @return
     */
    Long getExecutionTime();

    /**
     * Get the first item of the sorted list.
     * @return
     */
    T getFirst();

    /**
     * Get the last item of the sorted list.
     * @return
     */
    T getLast();
}
