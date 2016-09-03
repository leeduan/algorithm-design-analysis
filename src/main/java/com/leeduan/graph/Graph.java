package com.leeduan.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Graph containing a set of vertices and its edges.
 * @param <T>
 */
class Graph<T> {
    private static final int MIN_VERTICES = 2;

    private final Map<T, Vertex<T>> verticesMap;
    private final List<Edge<T>> edges;

    public Graph(Map<T, List<T>> graphData) {
        this.verticesMap = new HashMap<>();
        this.edges = new ArrayList<>();

        // add every vertex to vertices map
        graphData.entrySet().forEach(e -> this.verticesMap.put(e.getKey(), new Vertex<>(e.getKey())));

        for (Vertex<T> vertex : this.verticesMap.values()) {
            // add edges to vertex
            for (T value : graphData.get(vertex.getValue())) {
                final Vertex<T> adjVertex = getVertex(value)
                        .orElseThrow(() -> new IllegalArgumentException("Could not find vertex with value " + value));

                // find or else get edge
                final Edge<T> edge = adjVertex.getEdge(vertex)
                        .orElseGet(() -> {
                            final Edge<T> newEdge = new Edge<>(vertex, adjVertex);
                            this.edges.add(newEdge);
                            return newEdge;
                        });
                vertex.addEdge(edge);
            }
        }
    }

    public Optional<Vertex<T>> getVertex(T value) {
        Objects.requireNonNull(value, "Value cannot be null");

        return Optional.ofNullable(this.verticesMap.get(value));
    }

    public List<Vertex<T>> getVertices() {
        return new ArrayList<>(verticesMap.values());
    }

    public List<Edge<T>> getEdges() {
        return edges;
    }

    public boolean isFullyContracted() {
        return this.verticesMap.size() <= MIN_VERTICES;
    }

    public void merge(Edge<T> edge) {
        Objects.requireNonNull(edge, "Edge cannot be null");

        final Vertex<T> toVertex = edge.getRandom();
        final Vertex<T> fromVertex = edge.getOther(toVertex);

        removeEdge(edge, fromVertex, toVertex);
        mergeEdges(fromVertex, toVertex);
    }

    private void removeEdge(Edge<T> edge, Vertex<T> fromVertex, Vertex<T> toVertex) {
        // remove from both instance vertices and edges
        this.verticesMap.remove(fromVertex.getValue());
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
        return "Graph{" + "vertices=" + verticesMap.values() + ", edges=" + edges + "}";
    }
}