package com.leeduan.sort;

import com.leeduan.utils.FileReader;
import com.leeduan.utils.operators.IntegerOperator;
import com.leeduan.utils.transformers.StringIntTransformer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.List;

/**
 * Contains programs written for week 1 of the course.
 */
public class DivideAndConquer {
    private static final Logger log = LoggerFactory.getLogger(DivideAndConquer.class);
    private static final URL FILE_PATH = DivideAndConquer.class.getClassLoader().getResource("integers.txt");

    // sample list with 100,000 unordered integers
    protected static final List<Integer> INTEGER_LIST = FileReader.lineFileScanner(FILE_PATH, new StringIntTransformer());

    public static void run() {
        // programming assignment #1
        final MergeSorter<Integer> sorter = new MergeSorter<>(INTEGER_LIST, new IntegerOperator());
        log.info("Number of inversions {} for merge sorted list with range {} - {}, executed in {}ms",
                        sorter.getInversions(), sorter.getFirst(), sorter.getLast(), sorter.getExecutionTime());
    }
}
