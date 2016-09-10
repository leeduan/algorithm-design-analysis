package com.leeduan.graph;

import java.util.List;
import java.util.Optional;

/**
 * Generic interface of a vertex.
 * @param <T>
 */
interface Vertex<T> {

    /**
     * Get the value of vertex.
     * @return
     */
    T getValue();

    /**
     * Get the edges of this vertex.
     * @return
     */
    List<Edge<T>> getEdges();

    /**
     * Get the edges this vertex can traverse to.
     * @return
     */
    List<Edge<T>> getTraversableEdges();

    /**
     * Get the edges this vertex cannot traverse to.
     * @return
     */
    List<Edge<T>> getNonTraversableEdges();

    /**
     * Add an edge to this vertex.
     * @param edge
     */
    void addEdge(Edge<T> edge);

    /**
     * Get the edge that matches this and the passed in vertex, if found.
     * @param vertex
     * @return
     */
    Optional<Edge<T>> getEdge(Vertex<T> vertex);

    /**
     * Set the explored flag from boolean.
     * @param explored
     */
    void setExplored(boolean explored);

    /**
     * Get whether vertex is explored.
     * @return
     */
    boolean isExplored();

    /**
     * Set the finishing time of the vertex.
     * @param finishingTime
     */
    void setFinishingTime(int finishingTime);

    /**
     * Get the finishing time of the vertex.
     * @return
     */
    int getFinishingTime();
}
