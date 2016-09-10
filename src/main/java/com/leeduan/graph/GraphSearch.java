package com.leeduan.graph;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * Graph search algorithm implementations.
 */
public class GraphSearch {

    /**
     * Depth first search with a finishing time incrementer.
     * @param graph
     * @param vertex
     * @param <T>
     */
    public static <T extends Comparable<T>> void depthFirstSearchFinishingTimes(DirectedGraph<T> graph,
                    DirectedVertex<T> vertex) {
        depthFirstSearch(graph, vertex, e -> (DirectedVertex<T>)e.getHead(), null,
                (g, v) -> g.incrementFinishingTime(v));
    }

    /**
     * Depth first search with leader vertex accumulator.
     * @param graph
     * @param vertex
     * @param <T>
     */
    public static <T extends Comparable<T>> void depthFirstSearchLeaders(DirectedGraph<T> graph,
                    DirectedVertex<T> vertex) {
        depthFirstSearch(graph, vertex, e -> (DirectedVertex<T>)e.getTail(),
                (g, v) -> v.setLeaderVertex(g.getSourceVertex()), null);
    }

    /**
     * Generic depth first search with edge to vertex function, and a pre/post bi-consumer.
     * @param graph
     * @param vertex
     * @param edgeToVertexFunction
     * @param preConsumer
     * @param postConsumer
     * @param <T>
     */
    // TODO: Implement depth first search iteratively using a stack, instead of via recursion.
    private static <T extends Comparable<T>> void depthFirstSearch(DirectedGraph<T> graph, DirectedVertex<T> vertex,
                    Function<Edge<T>, DirectedVertex<T>> edgeToVertexFunction,
                    BiConsumer<DirectedGraph<T>, DirectedVertex<T>> preConsumer,
                    BiConsumer<DirectedGraph<T>, DirectedVertex<T>> postConsumer) {
        Objects.requireNonNull(graph, "Graph cannot be null");
        Objects.requireNonNull(vertex, "Vertex cannot be null");
        if (vertex.isExplored()) {
            throw new IllegalArgumentException("Cannot search through explored vertex " + vertex.getValue());
        }

        Optional.ofNullable(preConsumer).ifPresent(b -> b.accept(graph, vertex));
        vertex.setExplored(true);
        vertex.getEdges().stream()
                .map(edgeToVertexFunction)
                .filter(v -> !v.equals(vertex))
                .forEach(v -> {
                    if (!v.isExplored()) {
                        depthFirstSearch(graph, v, edgeToVertexFunction, preConsumer, postConsumer);
                    }
                });
        Optional.ofNullable(postConsumer).ifPresent(b -> b.accept(graph, vertex));
    }

}
