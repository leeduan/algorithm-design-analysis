package com.leeduan.structures;

import com.leeduan.utils.FileReader;
import com.leeduan.utils.transformers.StringLongTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.IntStream;

public class TwoSum {
    private static final Logger log = LoggerFactory.getLogger(TwoSum.class);
    private static final URL SUM_PATH = TwoSum.class.getClassLoader().getResource("sum.txt");

    public static List<Long> readData() {
        final long startFileReadTime = System.currentTimeMillis();
        final List<Long> longList = FileReader.lineFileScanner(SUM_PATH, new StringLongTransformer());
        final long endFileReadTime = System.currentTimeMillis();

        log.info("Took {}ms to read sum.txt file", endFileReadTime - startFileReadTime);
        return longList;
    }

    /**
     * Solves the 2-Sum problem using a hash table for a specified value t where a
     * x, y both exist in the hash table set of integers, and x, y are distinct.
     * @param longSet
     * @param t
     * @return
     */
    public static boolean twoDistinctSum(Set<Long> longSet, Long t) {
        Objects.requireNonNull(longSet, "Integers cannot be null");
        Objects.requireNonNull(t, "Integers cannot be null");

        return longSet.parallelStream().anyMatch(i -> !i.equals(t - i) && longSet.contains(t - i));
    }

    public static void run() {
        // programming assignment #6.1
        final Set<Long> longSet = new HashSet<>(readData());
        final long count = IntStream.rangeClosed(-10000, 10000)
                .mapToLong(t -> twoDistinctSum(longSet, (long)t) ? 1 : 0)
                .sum();
        // 427 (takes many minutes to run)
        log.info("{} number of target values t in the interval -10000, 10000 where distinct x, y satisfies x + y = t",
                        count);
    }
}
