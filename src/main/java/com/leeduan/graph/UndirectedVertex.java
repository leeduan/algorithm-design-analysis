package com.leeduan.graph;

/**
 * A vertex (node) on a graph. It points to the edges incident on it.
 * @param <T>
 */
class UndirectedVertex<T> extends AbstractVertex<T> implements Vertex<T> {

    public UndirectedVertex(T value) {
        super(value);
    }
}
