package com.leeduan.graph;

import java.util.Optional;

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
    public String toString() {
        return "Vertex{value=" + value + ", explored=" + explored + ", finishingTime=" + finishingTime + ", leader="
                + Optional.ofNullable(leaderVertex).map(Vertex::getValue).orElse(null) + "}";
    }
}
