package com.leeduan.graph;

import com.leeduan.utils.FileReader;
import com.leeduan.utils.transformers.StringListIntTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Contraction {
    private static final Logger log = LoggerFactory.getLogger(Contraction.class);
    private static final URL MINCUT_PATH = Contraction.class.getClassLoader().getResource("mincut.txt");
    private static final Map<Integer, List<Integer>> MAP_VERTEX_ADJACENCY_LIST;

    // sample min data conversion to map
    static {
        MAP_VERTEX_ADJACENCY_LIST = new HashMap<>();
        FileReader.lineFileScanner(MINCUT_PATH, new StringListIntTransformer()).stream()
                .filter(l -> Objects.nonNull(l) && l.size() > 0)
                .forEach(l -> MAP_VERTEX_ADJACENCY_LIST.put(l.get(0), l.subList(1, l.size())));
    }

    public static void run() {
        // programming assignment #3
        log.info("Number of edges from min cut graph is {}", Karger.minCutRepeated(MAP_VERTEX_ADJACENCY_LIST));
    }
}
