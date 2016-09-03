package com.leeduan.graph;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * An undirected edge which points to its endpoints.
 * @param <T>
 */
class UndirectedEdge<T> implements Edge<T> {
    private final List<Vertex<T>> pair;

    public UndirectedEdge(Vertex<T> left, Vertex<T> right) {
        Objects.requireNonNull(left, "Left vertex argument cannot be null");
        Objects.requireNonNull(right, "Right vertex argument cannot be null");

        this.pair = Arrays.asList(left, right);
    }

    @Override
    public List<Vertex<T>> getPair() {
        return pair;
    }

    @Override
    public Vertex<T> getRandom() {
        return pair.get(new Random().nextInt(pair.size()));
    }

    @Override
    public Vertex<T> getOther(Vertex<T> random) {
        Objects.requireNonNull(random, "Randomly calculated vertex cannot be null");

        final int index = this.pair.indexOf(random);
        if (index < 0) {
            throw new IllegalArgumentException("Edge does not contain vertex with value " + random.getValue());
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

    @Override
    public boolean contains(Vertex<T> vertex) {
        Objects.requireNonNull(vertex, "Vertex cannot be null");

        return pair.contains(vertex);
    }

    @Override
    public boolean contains(Vertex<T> vertex1, Vertex<T> vertex2) {
        Objects.requireNonNull(vertex1, "First vertex cannot be null");
        Objects.requireNonNull(vertex2, "Second vertex cannot be null");

        return pair.contains(vertex1) && pair.contains(vertex2);
    }

    @Override
    public String toString() {
        return "Edge{pair=" + pair + "}";
    }
}