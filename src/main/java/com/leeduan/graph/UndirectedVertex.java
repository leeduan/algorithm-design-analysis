package com.leeduan.graph;

import java.util.Collections;
import java.util.List;

/**
 * A vertex (node) on a graph. It points to the edges incident on it.
 * @param <T>
 */
class UndirectedVertex<T> extends AbstractVertex<T> implements Vertex<T> {

    public UndirectedVertex(T value) {
        super(value);
    }

    @Override
    public List<Edge<T>> getTraversableEdges() {
        return getEdges();
    }

    @Override
    public List<Edge<T>> getNonTraversableEdges() {
        return Collections.emptyList();
    }
}
