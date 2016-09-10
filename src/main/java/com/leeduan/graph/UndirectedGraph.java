package com.leeduan.graph;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Graph containing a set of vertices and its edges.
 * @param <T>
 */
class UndirectedGraph<T> extends AbstractGraph<T> implements Graph<T> {

    public UndirectedGraph(Map<T, Vertex<T>> verticesMap, List<Edge<T>> edges) {
        this.verticesMap = verticesMap;
        this.edges = edges;
    }

    public UndirectedGraph(Map<T, List<T>> graphData) {
        super();
        Objects.requireNonNull(graphData, "Graph data cannot be null");

        // add every vertex to vertices list
        graphData.keySet().stream().distinct().forEach(k -> this.verticesMap.put(k, new UndirectedVertex<>(k)));

        // add every vertex to vertices list
        for (Vertex<T> vertex : this.verticesMap.values()) {
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
}