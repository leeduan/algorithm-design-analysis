package com.leeduan.graph;

import java.util.Objects;
import java.util.Random;

/**
 * An undirected edge which points to its endpoints.
 * @param <T>
 */
class UndirectedEdge<T> extends AbstractEdge<T> implements Edge<T> {

    public UndirectedEdge(Vertex<T> left, Vertex<T> right) {
        super(left, right);
    }

    public UndirectedEdge(Vertex<T> left, Vertex<T> right, int distance) {
        super(left, right, distance);
    }

    @Override
    public Vertex<T> getRandom() {
        return pair.get(new Random().nextInt(pair.size()));
    }

    @Override
    public Vertex<T> getOther(Vertex<T> oneVertex) {
        Objects.requireNonNull(oneVertex, "One cannot be null");

        final int index = this.pair.indexOf(oneVertex);
        if (index < 0) {
            throw new IllegalArgumentException("Edge does not contain vertex with value " + oneVertex.getValue());
        }

        return pair.get(1 - index); // find other pair
    }

    @Override
    public void replace(Vertex<T> fromVertex, Vertex<T> toVertex) {
        Objects.requireNonNull(fromVertex, "From vertex cannot be null");
        Objects.requireNonNull(toVertex, "To vertex cannot be null");

        final int index = this.pair.indexOf(fromVertex);
        if (index < 0) {
            throw new IllegalArgumentException("Edge does not contain vertex with value " + fromVertex.getValue());
        }

        pair.set(index, toVertex); // swap from with to
    }
}