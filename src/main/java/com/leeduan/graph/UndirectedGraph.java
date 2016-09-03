package com.leeduan.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Graph containing a set of vertices and its edges.
 * @param <T>
 */
class UndirectedGraph<T> implements Graph<T> {
    private final List<Vertex<T>> vertices;
    private final List<Edge<T>> edges;

    public UndirectedGraph(Map<T, List<T>> graphData) {
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();

        // add every vertex to vertices list
        graphData.entrySet().forEach(e -> this.vertices.add(new Vertex<>(e.getKey())));

        for (Vertex<T> vertex : this.vertices) {
            // add edges to vertex
            for (T value : graphData.get(vertex.getValue())) {
                final Vertex<T> adjVertex = getVertex(value)
                        .orElseThrow(() -> new IllegalArgumentException("Could not find vertex with value " + value));

                // find or else get edge
                final Edge<T> edge = adjVertex.getEdge(vertex)
                        .orElseGet(() -> {
                            final Edge<T> newEdge = new UndirectedEdge<>(vertex, adjVertex);
                            this.edges.add(newEdge);
                            return newEdge;
                        });
                vertex.addEdge(edge);
            }
        }
    }

    @Override
    public Optional<Vertex<T>> getVertex(T value) {
        Objects.requireNonNull(value, "Value cannot be null");

        return this.vertices.stream().filter(v -> v.getValue().equals(value)).findFirst();
    }

    @Override
    public List<Vertex<T>> getVertices() {
        return this.vertices;
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
        Objects.requireNonNull(edge, "Edge cannot be null");

        final Vertex<T> toVertex = edge.getRandom();
        final Vertex<T> fromVertex = edge.getOther(toVertex);

        removeEdge(edge, fromVertex, toVertex);
        mergeEdges(fromVertex, toVertex);
    }

    private void removeEdge(Edge<T> edge, Vertex<T> fromVertex, Vertex<T> toVertex) {
        // remove from both instance vertices and edges
        this.vertices.remove(fromVertex);
        this.edges.remove(edge);
        // remove from both edge vertices
        fromVertex.getEdges().remove(edge);
        toVertex.getEdges().remove(edge);
    }

    private void mergeEdges(Vertex<T> fromVertex, Vertex<T> toVertex) {
        for (Edge<T> fromEdge : fromVertex.getEdges()) {
            if (fromEdge.contains(toVertex)) {
                // delete self loops
                toVertex.getEdges().remove(fromEdge);
                this.edges.remove(fromEdge);
            } else {
                // merge from vertex to to vertex
                fromEdge.replace(fromVertex, toVertex);
                toVertex.addEdge(fromEdge);
            }
        }
    }

    @Override
    public String toString() {
        return "Graph{" + "vertices=" + vertices + ", edges=" + edges + "}";
    }
}