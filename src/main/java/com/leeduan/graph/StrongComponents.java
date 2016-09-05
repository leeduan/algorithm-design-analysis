package com.leeduan.graph;

import com.leeduan.utils.FileReader;
import com.leeduan.utils.transformers.StringListIntTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Container for running strongly connected component algorithms.
 */
public class StrongComponents {
    private static final Logger log = LoggerFactory.getLogger(StrongComponents.class);
    private static final URL SCC_PATH = StrongComponents.class.getClassLoader().getResource("scc.txt");

    // large sample strong components file conversion to map
    public static DirectedGraph<Integer> readData() {
        // file does not contain all vertices so start with range
        final Map<Integer, Vertex<Integer>> verticesMap = IntStream.rangeClosed(1, 875714).boxed()
                .collect(Collectors.toMap(Function.identity(), DirectedVertex::new));

        // XXX This is a very large file; this process should be optimized.
        final long startFileReadTime = System.currentTimeMillis();
        final List<Edge<Integer>> edges = FileReader.lineFileScanner(SCC_PATH, new StringListIntTransformer(" "))
                .stream()
                .map(l -> {
                    final Vertex<Integer> vertex1 = verticesMap.get(l.get(0));
                    final Vertex<Integer> vertex2 = verticesMap.get(l.get(1));
                    final DirectedEdge<Integer> edge = new DirectedEdge<>(vertex1, vertex2);
                    vertex1.addEdge(edge);
                    vertex2.addEdge(edge);
                    return edge;
                })
                .collect(Collectors.toList());
        final long endFileReadTime = System.currentTimeMillis();
        log.info("Took {}ms to read strong components file into edges", endFileReadTime - startFileReadTime);

        final long startGraphTime = System.currentTimeMillis();
        final DirectedGraph<Integer> graph = new DirectedGraph<>(verticesMap, edges);
        final long endGraphTime = System.currentTimeMillis();
        log.info("Took {}ms to start strong components graph", endGraphTime - startGraphTime);

        return graph;
    }

    public static List<Integer> run() {
        // programming assignment #4
        final List<Integer> strongComponentsBySize = Kosaraju.strongComponentsBySize(readData());
        log.info("Strong components sorted by size {}",
                        strongComponentsBySize.subList(0, Math.min(5, strongComponentsBySize.size())));
        return strongComponentsBySize;
    }
}
