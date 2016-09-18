package com.leeduan.structures;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Class that maintains value of the median of all its values using two evenly sized
 * heaps for min, max extraction.
 */
public class MedianHeap {
    private static final int INITIAL_CAPCITY = 11;
    private static final Comparator<Integer> ASC_COMPARATOR = (o1, o2) -> o1.compareTo(o2);
    private static final Comparator<Integer> DESC_COMPARATOR = (o1, o2) -> o2.compareTo(o1);

    private final PriorityQueue<Integer> lowHeap;
    private final PriorityQueue<Integer> highHeap;

    public MedianHeap() {
        this.lowHeap = new PriorityQueue<>(INITIAL_CAPCITY, DESC_COMPARATOR);
        this.highHeap = new PriorityQueue<>(INITIAL_CAPCITY, ASC_COMPARATOR);
    }

    public void add(Integer number) {
        if (highHeap.isEmpty() || greaterThan(number, highHeap.peek())) {
            highHeap.add(number);
        } else {
            lowHeap.add(number);
        }

        if (lowHeap.size() > highHeap.size() + 1) {
            highHeap.add(lowHeap.poll());
        } else if (highHeap.size() > lowHeap.size() + 1) {
            lowHeap.add(highHeap.poll());
        }
    }

    public Integer median() {
        final int size = size();
        final int n = isOdd(size) ? ((size + 1) / 2) : (size / 2);

        return lowHeap.size() == n ? lowHeap.peek() : highHeap.peek();
    }

    private int size() {
        return lowHeap.size() + highHeap.size();
    }

    private boolean greaterThan(Integer left, Integer right) {
        return left.compareTo(right) > 0;
    }

    private boolean isOdd(int size) {
        return size % 2 != 0;
    }
}