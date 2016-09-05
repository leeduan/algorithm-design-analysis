package com.leeduan.graph;

import java.util.List;

/**
 * Generic interface of an ege.
 */
interface Edge<T> {

    /**
     * Return the pair of vertexes for the edge.
     * @return
     */
    List<Vertex<T>> getPair();

    /**
     * Return the head pair of the edge.
     * @return
     */
    Vertex<T> getHead();

    /**
     * Return the tail pair of the edge.
     * @return
     */
    Vertex<T> getTail();

    /**
     * Return a random vertex of the edge.
     * @return
     */
    Vertex<T> getRandom();

    /**
     * Given one of the pair, return the other.
     * @param random
     * @return
     */
    Vertex<T> getOther(Vertex<T> random);

    /**
     * Replace a vertex this contains with another vertex.
     * @param fromVertex
     * @param toVertex
     */
    void replace(Vertex<T> fromVertex, Vertex<T> toVertex);

    /**
     * Check if this contains a vertex.
     * @param vertex
     * @return
     */
    boolean contains(Vertex<T> vertex);

    /**
     * Check if this contains both vertices.
     * @param vertex1
     * @param vertex2
     * @return
     */
    boolean equals(Vertex<T> vertex1, Vertex<T> vertex2);
}
