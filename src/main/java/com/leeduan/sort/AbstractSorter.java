package com.leeduan.sort;

import java.util.List;

/**
 * Base implementation of sorter interface.
 * @param <T>
 */
abstract class AbstractSorter<T> implements Sorter<T> {
    List<T> sortedList;
    Long executionTime;

    @Override
    public List<T> getSortedList() {
        return sortedList;
    }

    @Override
    public Long getExecutionTime() {
        return executionTime;
    }

    @Override
    public T getFirst() {
        return isNotEmpty() ? sortedList.get(0) : null;
    }

    @Override
    public T getLast() {
        return isNotEmpty() ? sortedList.get(sortedList.size() - 1) : null;
    }

    private boolean isNotEmpty() {
        return !sortedList.isEmpty();
    }
}
