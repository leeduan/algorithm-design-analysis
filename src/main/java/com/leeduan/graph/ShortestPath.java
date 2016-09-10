package com.leeduan.graph;

import com.leeduan.utils.FileReader;
import com.leeduan.utils.transformers.StringListListIntTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Container for running shortest path algorithms.
 */
public class ShortestPath {
    private static final Logger log = LoggerFactory.getLogger(StrongComponents.class);
    private static final URL SHORT_PATH = StrongComponents.class.getClassLoader().getResource("shortpath.txt");

    public static UndirectedGraph<Integer> readData() {
        final Map<Integer, Vertex<Integer>> verticesMap = IntStream.rangeClosed(1, 200).boxed()
                .collect(Collectors.toMap(Function.identity(), UndirectedVertex::new));

        final List<Edge<Integer>> edges = new ArrayList<>();
        final List<List<List<Integer>>> fileData = FileReader.lineFileScanner(SHORT_PATH,
                new StringListListIntTransformer("\t"));
        for (List<List<Integer>> row : fileData) {
            Optional<Vertex<Integer>> optVertex = Optional.empty();
            for (List<Integer> valueOrPair : row) {
                if (valueOrPair.size() == 1) {
                    optVertex = Optional.of(verticesMap.get(valueOrPair.get(0)));
                    continue;
                }

                final Vertex<Integer> vertex1 = optVertex
                        .orElseThrow(() -> new IllegalStateException("No vertex found for row " + row));
                final Vertex<Integer> vertex2 = Optional.of(verticesMap.get(valueOrPair.get(0)))
                        .orElseThrow(() -> new IllegalStateException("No vertex found for value " + valueOrPair.get(0)));
                if (!vertex1.getEdge(vertex2).isPresent()) {
                    final int distance = Optional.ofNullable(valueOrPair.get(1))
                            .orElseThrow(() -> new IllegalStateException("No distance found for row " + row));
                    final Edge<Integer> edge = new UndirectedEdge<>(vertex1, vertex2, distance);
                    vertex1.addEdge(edge);
                    vertex2.addEdge(edge);
                    edges.add(edge);
                }
            }
        }

        return new UndirectedGraph<>(verticesMap, edges);
    }

    public static void run() {
        // programming assignment #5
        final Integer sourceValue = 1;
        final List<Integer> destinationValues = Arrays.asList(7, 37, 59, 82, 99, 115, 133, 165, 188, 197);
        final List<Integer> shortestPaths = destinationValues.stream()
                .map(v -> Dijkstra.shortestPath(readData(), sourceValue, v))
                .collect(Collectors.toList());
        log.info("Shortest paths for destinations {} are {}", destinationValues, shortestPaths);
    }
}
