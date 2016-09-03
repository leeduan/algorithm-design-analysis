package com.leeduan.graph;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.stream.IntStream;

public class Karger {
    private static final int MIN_VERTICES = 2;

    /**
     * Runs Karger's contraction algorithm the number of vertices times and return the number of edges
     * of each iteration as the min cut.
     * @param graphData
     * @param <T>
     * @return
     */
    public static <T> int minCutRepeated(Map<T, List<T>> graphData) {
        Objects.requireNonNull(graphData, "Graph data cannot be null");

        return IntStream.rangeClosed(0, graphData.size())
                .map(i -> minCut(graphData))
                .min()
                .orElseThrow(() -> new IllegalStateException("Unable to retrieve min of repeated min cut checks"));
    }

    /**
     * Runs Karger's contraction algorithm once and return the number of edges upon completion.
     * @param graphData
     * @param <T>
     * @return
     */
    public static <T> int minCut(Map<T, List<T>> graphData) {
        Objects.requireNonNull(graphData, "Graph data cannot be null");

        final Graph<T> graph = new UndirectedGraph<>(graphData);
        while(graph.getVertices().size() > MIN_VERTICES) {
            graph.merge(pickRandomEdge(graph.getEdges()));
        }

        return graph.getEdges().size();
    }

    private static <T> Edge<T> pickRandomEdge(List<Edge<T>> edges) {
        return edges.get(new Random().nextInt(edges.size()));
    }
}
