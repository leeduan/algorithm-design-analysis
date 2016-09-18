package com.leeduan.structures;

import com.leeduan.utils.FileReader;
import com.leeduan.utils.transformers.StringIntTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.List;

public class MedianMaintenance {
    private static final Logger log = LoggerFactory.getLogger(TwoSum.class);
    private static final URL MEDIAN_PATH = TwoSum.class.getClassLoader().getResource("median.txt");
    private static final List<Integer> INTEGER_LIST = FileReader.lineFileScanner(MEDIAN_PATH,
                    new StringIntTransformer());

    public static void run() {
        // programming assignment #6.2
        final int listSize = INTEGER_LIST.size();
        final MedianHeap medianHeap = new MedianHeap();
        final int sumOfMedians = INTEGER_LIST.stream().mapToInt(i -> {
            medianHeap.add(i);
            return medianHeap.median();
        }).sum();

        log.info("sum of {} medians, modulo {} is {}", listSize, listSize, sumOfMedians % listSize);
    }
}
