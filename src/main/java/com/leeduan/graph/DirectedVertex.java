package com.leeduan.graph;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * A vertex (node) on a graph. It points to the directed edges from it.
 * @param <T>
 */
class DirectedVertex<T> extends AbstractVertex<T> implements Vertex<T> {
    private DirectedVertex<T> leaderVertex;

    public DirectedVertex(T value) {
        super(value);
    }

    public DirectedVertex<T> getLeaderVertex() {
        return leaderVertex;
    }

    public void setLeaderVertex(DirectedVertex<T> leaderVertex) {
        this.leaderVertex = leaderVertex;
    }

    @Override
    public List<Edge<T>> getTraversableEdges() {
        return getEdges().stream().filter(e -> !e.getHead().equals(this)).collect(Collectors.toList());
    }

    @Override
    public List<Edge<T>> getNonTraversableEdges() {
        final List<Edge<T>> traversableEdges = getTraversableEdges();
        return getEdges().stream().filter(e -> !traversableEdges.contains(e)).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Vertex{value=" + value + ", explored=" + explored + ", finishingTime=" + finishingTime + ", leader="
                + Optional.ofNullable(leaderVertex).map(Vertex::getValue).orElse(null) + "}";
    }
}
