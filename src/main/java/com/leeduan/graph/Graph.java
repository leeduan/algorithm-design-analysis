package com.leeduan.graph;

import java.util.List;
import java.util.Optional;

/**
 * Generic interface of a graph.
 */
interface Graph<T> {

    /**
     * Return a vertex by its value.
     * @param value
     * @return
     */
    Optional<Vertex<T>> getVertex(T value);

    /**
     * Return all vertices of the graph.
     * @return
     */
    List<Vertex<T>> getVertices();

    /**
     * Returns an edge for two vertex values.
     * @param value1
     * @param value2
     * @return
     */
    Optional<Edge<T>> getEdge(T value1, T value2);

    /**
     * Return all the edges of the graph.
     * @return
     */
    List<Edge<T>> getEdges();

    /**
     * Merge an edge of the graph.
     * @param edge
     */
    void merge(Edge<T> edge);
}
