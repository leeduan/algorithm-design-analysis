package com.leeduan.graph;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Objects;

/**
 * Graph containing a set of vertices and its directed edges.
 * @param <T>
 */
class DirectedGraph<T extends Comparable<T>> extends AbstractGraph<T> implements Graph<T> {
    private DirectedVertex<T> sourceVertex;
    private List<T> verticesSorted;

    public DirectedGraph(Map<T, Vertex<T>> verticesMap, List<Edge<T>> edges) {
        this.verticesMap = verticesMap;
        this.edges = edges;
    }

    public DirectedVertex<T> getSourceVertex() {
        return sourceVertex;
    }

    public void setSourceVertex(DirectedVertex<T> sourceVertex) {
        this.sourceVertex = sourceVertex;
    }

    public T shiftVerticesSorted() {
        if (this.verticesSorted.isEmpty()) {
            throw new IllegalStateException("Cannot shift empty vertices");
        }

        final T head = this.verticesSorted.get(0);
        this.verticesSorted = this.verticesSorted.subList(1, this.verticesSorted.size());
        return head;
    }

    public List<T> getVerticesSorted() {
        return verticesSorted;
    }

    public void setVerticesSorted(List<T> verticesSorted) {
        this.verticesSorted = verticesSorted;
    }

    public void sortedVerticesByValue() {
        setVerticesSorted(this.verticesSorted = this.verticesMap.keySet().stream()
                .sorted((v1, v2) -> v2.compareTo(v1))
                .collect(Collectors.toList()));
    }

    public void sortedVerticesByFinishingTime() {
        setVerticesSorted(this.verticesMap.values().stream()
                .sorted((v1, v2) -> Integer.compare(v2.getFinishingTime(), v1.getFinishingTime()))
                .map(Vertex::getValue)
                .collect(Collectors.toList()));
    }
}
