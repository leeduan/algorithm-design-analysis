package com.leeduan.sort;

import java.util.List;

/**
 * Generic interface of a sorting algorithm.
 */
public interface Sorter<T> {

    List<T> getSortedList();

    Long getExecutionTime();

    T getFirst();

    T getLast();

}
