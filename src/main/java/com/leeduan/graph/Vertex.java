package com.leeduan.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * A vertex (node) on a graph. It points to the edges incident on it.
 * @param <T>
 */
class Vertex<T> {
    private final T value;
    private final List<Edge<T>> edges;

    public Vertex(T value) {
        Objects.requireNonNull(value, "Value cannot be null");

        this.value = value;
        this.edges = new ArrayList<>();
    }

    public T getValue() {
        return value;
    }

    public List<Edge<T>> getEdges() {
        return edges;
    }

    public void addEdge(Edge<T> edge) {
        Objects.requireNonNull(edge, "Edge cannot be null");

        this.edges.add(edge);
    }

    public Optional<Edge<T>> getEdge(Vertex<T> vertex) {
        Objects.requireNonNull(vertex, "Vertex cannot be null");

        return this.edges.stream().filter(e -> e.contains(this, vertex)).findFirst();
    }

    @Override
    public String toString() {
        return "Vertex{value=" + value + "}";
    }
}
