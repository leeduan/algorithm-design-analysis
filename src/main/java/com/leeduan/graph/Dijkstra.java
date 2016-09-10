package com.leeduan.graph;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Dijkstra {

    /**
     * Run Dijkstra's shortest path algorithm on a given graph.
     * @param graph
     * @param <T>
     * @return
     */
    // TODO: Implement this algorithm using more optimized heap data structure.
    public static <T> int shortestPath(Graph<T> graph, T sourceValue, T destinationValue) {
        Objects.requireNonNull(graph, "Graph cannot be null");
        Objects.requireNonNull(sourceValue, "Source value cannot be null");
        Objects.requireNonNull(destinationValue, "Destination value cannot be null");

        final Vertex<T> sourceVertex = graph.getVertex(sourceValue)
                .orElseThrow(() -> new IllegalArgumentException("Invalid source value " + sourceValue));
        final Vertex<T> destinationVertex = graph.getVertex(destinationValue)
                .orElseThrow(() -> new IllegalArgumentException("Invalid destination value " + destinationValue));

        // map of computed shortest path distances per vertex in x grouping
        final Map<Vertex<T>, Integer> xVerticesMap = new HashMap<>();
        xVerticesMap.put(sourceVertex, 0);

        while(xVerticesMap.get(destinationVertex) == null) {
            Edge<T> greedyEdge = null;
            // find greedy score for (v, w)
            for (Vertex<T> vertex : xVerticesMap.keySet()) {
                final Optional<Edge<T>> edgeOpt = vertex.getTraversableEdges().stream()
                        .filter(e -> xVerticesMap.get(e.getOther(vertex)) == null) // filter out those in x
                        .reduce((e1, e2) -> e2.getDistance() > e1.getDistance() ? e1 : e2);
                if (!edgeOpt.isPresent()) {
                    continue;
                }

                greedyEdge = Optional.ofNullable(greedyEdge)
                        .map(e -> computeDistance(xVerticesMap, e) > computeDistance(xVerticesMap, edgeOpt.get())
                                ? edgeOpt.get() : e)
                        .orElse(edgeOpt.get());
            }

            Optional.ofNullable(greedyEdge).orElseThrow(() -> new IllegalArgumentException("Cannot reach destination"));
            addEdgeToMap(xVerticesMap, greedyEdge);
        }

        return xVerticesMap.get(destinationVertex);
    }

    /**
     * Compute the distance of the edge from the souce vertex from the previously computed shortest
     * distance to source of the vertex already in group x and the distance of the edge.
     * @param xVerticesMap
     * @param edge
     * @param <T>
     * @return
     */
    private static <T> int computeDistance(Map<Vertex<T>, Integer> xVerticesMap, Edge<T> edge) {
        final Vertex<T> vertex = getVertexInMap(xVerticesMap, edge);

        return xVerticesMap.get(vertex) + edge.getDistance();
    }

    /**
     * Add the vertex of the edge pair that is not already in group x into the group, with its computed
     * distance to the source vertex.
     * @param xVerticesMap
     * @param edge
     * @param <T>
     */
    private static <T> void addEdgeToMap(Map<Vertex<T>, Integer> xVerticesMap, Edge<T> edge) {
        final Vertex<T> vertex = getVertexInMap(xVerticesMap, edge);
        xVerticesMap.put(edge.getOther(vertex), computeDistance(xVerticesMap, edge));
    }

    /**
     * Grab the vertex of an edge already in group x map.
     * @param xVerticesMap
     * @param edge
     * @param <T>
     * @return
     */
    private static <T> Vertex<T> getVertexInMap(Map<Vertex<T>, Integer> xVerticesMap, Edge<T> edge) {
        final List<Vertex<T>> pair = edge.getPair();

        return Optional.ofNullable(xVerticesMap.get(pair.get(0)))
                .map(i -> pair.get(0))
                .orElseGet(() -> pair.get(1));
    }
}
