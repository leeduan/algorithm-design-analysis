package com.leeduan.graph;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.stream.IntStream;

public class Karger {

    /**
     * Runs Karger's contraction algorithm the number of vertices times and return the number of edges
     * of each iteration as the min cut.
     * @param graphData
     * @param <T>
     * @return
     */
    public static <T> int minCutRepeated(Map<T, List<T>> graphData) {
        Objects.requireNonNull(graphData, "Graph data cannot be null");

        return IntStream.rangeClosed(0, graphData.size())
                .map(i -> minCut(graphData))
                .min()
                .orElseThrow(() -> new IllegalStateException("Unable to retrieve min of repeated min cut checks"));
    }

    /**
     * Runs Karger's contraction algorithm once and return the number of edges upon completion.
     * @param graphData
     * @param <T>
     * @return
     */
    public static <T> int minCut(Map<T, List<T>> graphData) {
        Objects.requireNonNull(graphData, "Graph data cannot be null");

        final Graph<T> graph = new Graph<>(graphData);
        while(!graph.isFullyContracted()) {
            graph.merge(pickRandomEdge(graph.getEdges()));
        }

        return graph.getEdges().size();
    }

    private static <T> Edge<T> pickRandomEdge(List<Edge<T>> edges) {
        return edges.get(new Random().nextInt(edges.size()));
    }

    /**
     * Graph containing a set of vertices and its edges.
     * @param <T>
     */
    protected static class Graph<T> {
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

    /**
     * A vertex (node) on a graph. It points to the edges incident on it.
     * @param <T>
     */
    private static class Vertex<T> {
        private final T value;
        private final List<Edge<T>> edges;

        public Vertex(T value) {
            Objects.requireNonNull(value, "Value cannot be null");

            this.value = value;
            this.edges = new ArrayList<>();
        }

        public T getValue() {
            return value;
        }

        public List<Edge<T>> getEdges() {
            return edges;
        }

        public void addEdge(Edge<T> edge) {
            Objects.requireNonNull(edge, "Edge cannot be null");

            this.edges.add(edge);
        }

        public Optional<Edge<T>> getEdge(Vertex<T> vertex) {
            Objects.requireNonNull(vertex, "Vertex cannot be null");

            return this.edges.stream().filter(e -> e.contains(this, vertex)).findFirst();
        }

        @Override
        public String toString() {
            return "Vertex{value=" + value + "}";
        }
    }

    /**
     * An edge which points to its endpoints.
     * @param <T>
     */
    private static class Edge<T> {
        private final List<Vertex<T>> pair;

        public Edge(Vertex<T> left, Vertex<T> right) {
            Objects.requireNonNull(left, "Left vertex argument cannot be null");
            Objects.requireNonNull(right, "Right vertex argument cannot be null");

            this.pair = Arrays.asList(left, right);
        }

        public List<Vertex<T>> getPair() {
            return pair;
        }

        public Vertex<T> getRandom() {
            return pair.get(new Random().nextInt(pair.size()));
        }

        public Vertex<T> getOther(Vertex<T> random) {
            Objects.requireNonNull(random, "Randomly calculated vertex cannot be null");

            final int index = this.pair.indexOf(random);
            if (index < 0) {
                throw new IllegalArgumentException("Edge does not contain vertex with value " + random.getValue());
            }

            return pair.get(1 - index); // find other pair
        }

        public void replace(Vertex<T> fromVertex, Vertex<T> toVertex) {
            Objects.requireNonNull(fromVertex, "From vertex cannot be null");
            Objects.requireNonNull(toVertex, "To vertex cannot be null");

            final int index = this.pair.indexOf(fromVertex);
            if (index < 0) {
                throw new IllegalArgumentException("Edge does not contain vertex with value " + fromVertex.getValue());
            }

            pair.set(index, toVertex); // swap from with to
        }

        public boolean contains(Vertex<T> vertex) {
            Objects.requireNonNull(vertex, "Vertex cannot be null");

            return pair.contains(vertex);
        }

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
}
