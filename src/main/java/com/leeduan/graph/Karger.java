package com.leeduan.graph;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

public class Karger {

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

        final Graph<T> graph = new Graph<>(graphData);
        while(!graph.isFullyContracted()) {
            graph.merge(pickRandomEdge(graph.getEdges()));
        }

        return graph.getEdges().size();
    }

    private static <T> Edge<T> pickRandomEdge(List<Edge<T>> edges) {
        return edges.get(new Random().nextInt(edges.size()));
    }
}
