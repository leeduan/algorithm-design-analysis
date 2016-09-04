package com.leeduan.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Base graph implementation.
 * @param <T>
 */
abstract class AbstractGraph<T> implements Graph<T> {
    Map<T, Vertex<T>> verticesMap;
    List<Edge<T>> edges;
    int finishingTime;

    public AbstractGraph() {
        this.verticesMap = new HashMap<>();
        this.edges = new ArrayList<>();
    }

    @Override
    public Optional<Vertex<T>> getVertex(T value) {
        Objects.requireNonNull(value, "Value cannot be null");

        return Optional.ofNullable(this.verticesMap.get(value));
    }

    @Override
    public List<Vertex<T>> getVertices() {
        return new ArrayList<>(this.verticesMap.values());
    }

    @Override
    public Optional<Edge<T>> getEdge(T value1, T value2) {
        Objects.requireNonNull(value1, "First value cannot be null");
        Objects.requireNonNull(value2, "Second value cannot be null");

        final Vertex<T> vertex1 = getVertex(value1).orElse(null);
        final Vertex<T> vertex2 = getVertex(value2).orElse(null);
        if (vertex1 == null || vertex2 == null) {
            return Optional.empty();
        }

        return this.edges.stream().filter(e -> e.contains(vertex1, vertex2)).findFirst();
    }

    @Override
    public List<Edge<T>> getEdges() {
        return edges;
    }

    @Override
    public void merge(Edge<T> edge) {
        throw new UnsupportedOperationException("Not implemented by default");
    }

    @Override
    public void incrementFinishingTime(Vertex<T> vertex) {
        this.finishingTime++;
        vertex.setFinishingTime(this.finishingTime);
    }

    @Override
    public void resetExplored() {
        this.verticesMap.values().forEach(v -> v.setExplored(false));
    }

    @Override
    public String toString() {
        return "Graph{" + "vertices=" + verticesMap.values() + ", edges=" + edges + "}";
    }
}
