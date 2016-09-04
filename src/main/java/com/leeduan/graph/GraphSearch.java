package com.leeduan.graph;

import java.util.Objects;

/**
 * Graph search algorithm implementations.
 */
// TODO: Methods should be reviewed for generalization.
public class GraphSearch {

    /**
     * Depth first search with a finishing time incrementer.
     * @param graph
     * @param vertex
     * @param <T>
     */
    public static <T extends Comparable<T>> void depthFirstSearchFinishingTimes(DirectedGraph<T> graph,
                    DirectedVertex<T> vertex) {
        Objects.requireNonNull(graph, "Graph cannot be null");
        Objects.requireNonNull(vertex, "Vertex cannot be null");
        if (vertex.isExplored()) {
            throw new IllegalArgumentException("Cannot search through explored vertex " + vertex.getValue());
        }

        vertex.setExplored(true);
        vertex.setLeaderVertex(graph.getSourceVertex());
        vertex.getEdges().stream()
                .map(e -> (DirectedVertex<T>)e.getHead())
                .filter(v -> !v.equals(vertex))
                .forEach(v -> {
                    if (!v.isExplored()) {
                        depthFirstSearchFinishingTimes(graph, v);
                    }
                });
        graph.incrementFinishingTime(vertex);
    }

    /**
     * Depth first search with leader vertex accumulator.
     * @param graph
     * @param vertex
     * @param <T>
     */
    public static <T extends Comparable<T>> void depthFirstSearchLeaders(DirectedGraph<T> graph,
                    DirectedVertex<T> vertex) {
        Objects.requireNonNull(graph, "Graph cannot be null");
        Objects.requireNonNull(vertex, "Vertex cannot be null");
        if (vertex.isExplored()) {
            throw new IllegalArgumentException("Cannot search through explored vertex " + vertex.getValue());
        }

        vertex.setExplored(true);
        vertex.setLeaderVertex(graph.getSourceVertex());
        vertex.getEdges().stream()
                .map(e -> (DirectedVertex<T>)e.getTail())
                .filter(v -> !v.equals(vertex))
                .forEach(v -> {
                    if (!v.isExplored()) {
                        depthFirstSearchLeaders(graph, v);
                    }
                });
    }

}
