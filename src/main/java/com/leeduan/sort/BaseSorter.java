package com.leeduan.sort;

import java.util.List;

/**
 * Base implementation of sorter interface.
 * @param <T>
 */
public abstract class BaseSorter<T> implements Sorter<T> {
    protected List<T> sortedList;
    protected Long executionTime;

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
