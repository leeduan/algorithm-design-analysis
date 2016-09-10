package com.leeduan.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Abstract vertex implementation.
 * @param <T>
 */
abstract class AbstractVertex<T> implements Vertex<T> {
    final T value;
    final List<Edge<T>> edges;
    boolean explored;
    int finishingTime;

    public AbstractVertex(T value) {
        Objects.requireNonNull(value, "Value cannot be null");

        this.value = value;
        this.edges = new ArrayList<>();
    }

    @Override
    public T getValue() {
        return value;
    }

    @Override
    public List<Edge<T>> getEdges() {
        return edges;
    }

    @Override
    public List<Edge<T>> getTraversableEdges() {
        throw new UnsupportedOperationException("Not implemented by default");
    }

    @Override
    public List<Edge<T>> getNonTraversableEdges() {
        throw new UnsupportedOperationException("Not implemented by default");
    }

    @Override
    public void addEdge(Edge<T> edge) {
        Objects.requireNonNull(edge, "Edge cannot be null");

        this.edges.add(edge);
    }

    @Override
    public Optional<Edge<T>> getEdge(Vertex<T> vertex) {
        Objects.requireNonNull(vertex, "Vertex cannot be null");

        return this.edges.stream().filter(e -> e.equals(this, vertex)).findFirst();
    }

    @Override
    public void setExplored(boolean explored) {
        this.explored = explored;
    }

    @Override
    public boolean isExplored() {
        return explored;
    }

    @Override
    public void setFinishingTime(int finishingTime) {
        this.finishingTime = finishingTime;
    }

    @Override
    public int getFinishingTime() {
        return finishingTime;
    }

    @Override
    public String toString() {
        return "Vertex{value=" + value + ", explored=" + explored + ", finishingTime=" + finishingTime + "}";
    }
}
