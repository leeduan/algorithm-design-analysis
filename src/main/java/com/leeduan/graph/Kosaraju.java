package com.leeduan.graph;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Kosaraju {

    /**
     * Runs Kosaraju's two-pass algorithm and returns the size of each strong component
     * ordered by size desc.
     * @param graph
     * @return
     */
    public static <T extends Comparable<T>> List<Integer> strongComponentsBySize(DirectedGraph<T> graph) {
        Objects.requireNonNull(graph, "Graph cannot be null");

        // iterate through values first
        graph.sortedVerticesByValue();

        while (!graph.getVerticesSorted().isEmpty()) {
            final T value = graph.shiftVerticesSorted();
            final DirectedVertex<T> vertex = (DirectedVertex<T>)graph.getVertex(value)
                    .orElseThrow(() -> new IllegalArgumentException("Vertex not found"));
            if (!vertex.isExplored()) {
                GraphSearch.depthFirstSearchFinishingTimes(graph, vertex);
            }
        }

        // reset all as unexplored
        graph.resetExplored();

        // iterate through finishing time second
        graph.sortedVerticesByFinishingTime();
        while (!graph.getVerticesSorted().isEmpty()) {
            final T value = graph.shiftVerticesSorted();
            final DirectedVertex<T> vertex = (DirectedVertex<T>)graph.getVertex(value)
                    .orElseThrow(() -> new IllegalArgumentException("Vertex not found"));
            if (!vertex.isExplored()) {
                graph.setSourceVertex(vertex);
                GraphSearch.depthFirstSearchLeaders(graph, vertex);
            }
        }

        final Map<T, Integer> valueToLeaderCount = graph.verticesMap.entrySet().stream().collect(Collectors.toMap(
                e -> ((DirectedVertex<T>)e.getValue()).getLeaderVertex().getValue(),
                e -> 1, (v1, v2) -> v1 + 1));

        return valueToLeaderCount.values().stream().sorted((v1, v2) -> v2.compareTo(v1)).collect(Collectors.toList());
    }
}
