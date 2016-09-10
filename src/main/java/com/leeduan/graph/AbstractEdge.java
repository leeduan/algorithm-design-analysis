package com.leeduan.graph;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Abstract edge implementation.
 * @param <T>
 */
abstract class AbstractEdge<T> implements Edge<T> {
    final List<Vertex<T>> pair;
    int distance;

    public AbstractEdge(Vertex<T> left, Vertex<T> right) {
        Objects.requireNonNull(left, "Left vertex argument cannot be null");
        Objects.requireNonNull(right, "Right vertex argument cannot be null");

        this.pair = Arrays.asList(left, right);
    }

    public AbstractEdge(Vertex<T> left, Vertex<T> right, int distance) {
        this(left, right);

        this.distance = distance;
    }

    @Override
    public List<Vertex<T>> getPair() {
        return pair;
    }

    @Override
    public Vertex<T> getTail() {
        throw new UnsupportedOperationException("Not implemented by default");
    }

    @Override
    public Vertex<T> getHead() {
        throw new UnsupportedOperationException("Not implemented by default");
    }

    @Override
    public Vertex<T> getRandom() {
        throw new UnsupportedOperationException("Not implemented by default");
    }

    @Override
    public Vertex<T> getOther(Vertex<T> oneVertex) {
        throw new UnsupportedOperationException("Not implemented by default");
    }

    @Override
    public void replace(Vertex<T> fromVertex, Vertex<T> toVertex) {
        throw new UnsupportedOperationException("Not implemented by default");
    }

    @Override
    public boolean contains(Vertex<T> vertex) {
        Objects.requireNonNull(vertex, "Vertex cannot be null");

        return pair.contains(vertex);
    }

    @Override
    public boolean equals(Vertex<T> vertex1, Vertex<T> vertex2) {
        Objects.requireNonNull(vertex1, "First vertex cannot be null");
        Objects.requireNonNull(vertex2, "Second vertex cannot be null");

        return pair.contains(vertex1) && pair.contains(vertex2);
    }

    @Override
    public int getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "Edge{pair=" + pair + "}";
    }
}
