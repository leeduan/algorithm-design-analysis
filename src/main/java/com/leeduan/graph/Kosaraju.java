package com.leeduan.graph;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
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

        // iterate through values first, finding finishing times
        graph.sortedVerticesByValue();
        processDepthFirstSearch(graph, (g, v) -> GraphSearch.depthFirstSearchFinishingTimes(g, v));

        // reset all as unexplored
        graph.resetExplored();

        // iterate through finishing time second, finding leaders
        // TODO: Track finishing times through first loop, instead of sorting.
        graph.sortedVerticesByFinishingTime();
        processDepthFirstSearch(graph, (g, v) -> {
            g.setSourceVertex(v);
            GraphSearch.depthFirstSearchLeaders(g, v);
        });

        // sort leaders by value
        final Map<T, Integer> valueToLeaderCount = graph.verticesMap.entrySet().stream().collect(Collectors.toMap(
                e -> ((DirectedVertex<T>)e.getValue()).getLeaderVertex().getValue(),
                e -> 1, (v1, v2) -> v1 + 1));

        return valueToLeaderCount.values().stream().sorted((v1, v2) -> v2.compareTo(v1)).collect(Collectors.toList());
    }

    /**
     * Iterative depth first search from bi-consumer.
     * @param graph
     * @param dpsConsumer
     * @param <T>
     */
    private static <T extends Comparable<T>> void processDepthFirstSearch(DirectedGraph<T> graph,
                    BiConsumer<DirectedGraph<T>, DirectedVertex<T>> dpsConsumer) {
        while (!graph.getVerticesSorted().isEmpty()) {
            final T value = graph.shiftVerticesSorted();
            final DirectedVertex<T> vertex = (DirectedVertex<T>)graph.getVertex(value)
                    .orElseThrow(() -> new IllegalArgumentException("Vertex not found"));
            if (!vertex.isExplored()) {
                Optional.ofNullable(dpsConsumer).ifPresent(c -> c.accept(graph, vertex));
            }
        }
    }
}
