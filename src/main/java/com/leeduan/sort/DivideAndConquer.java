package com.leeduan.sort;

import com.leeduan.sort.QuickSorter.Pivot;
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
    private static final URL INTEGER_PATH = DivideAndConquer.class.getClassLoader().getResource("integers.txt");
    private static final URL QUICK_SORT_PATH = DivideAndConquer.class.getClassLoader().getResource("quicksort.txt");

    // sample list with 100,000 unordered integers
    protected static final List<Integer> INTEGER_LIST = FileReader.lineFileScanner(INTEGER_PATH,
                    new StringIntTransformer());

    // sample list with 10,000 unordered integers
    protected static final List<Integer> QUICK_LIST = FileReader.lineFileScanner(QUICK_SORT_PATH,
                    new StringIntTransformer());

    public static void run() {
        // programming assignment #1
        final MergeSorter<Integer> mSorter = new MergeSorter<>(INTEGER_LIST, new IntegerOperator());
        log.info("Number of inversions {} for merge sorted list with range {} - {}, executed in {}ms",
                        mSorter.getInversions(), mSorter.getFirst(), mSorter.getLast(), mSorter.getExecutionTime());

        // programming assignment #2
        final QuickSorter<Integer> qSorter = new QuickSorter<>(QUICK_LIST, new IntegerOperator(), Pivot.MEDIAN_OF_THREE);
        log.info("Number of conversions {} for quick sorted list with range {} - {}, executed in {}ms",
                        qSorter.getComparisons(), qSorter.getFirst(), qSorter.getLast(), qSorter.getExecutionTime());
    }
}
