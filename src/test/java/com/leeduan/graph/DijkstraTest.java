package com.leeduan.graph;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DijkstraTest {

    @Test
    public void testShortestPathSample() {
        Map<Integer, Vertex<Integer>> verticesMap = IntStream.rangeClosed(1, 4).boxed()
                .collect(Collectors.toMap(Function.identity(), DirectedVertex::new));
        List<Edge<Integer>> edges = Arrays.asList(createEdge(verticesMap, 1, 2, 1),
                        createEdge(verticesMap, 1, 3, 4), createEdge(verticesMap, 2, 3, 2),
                        createEdge(verticesMap, 2, 4, 6), createEdge(verticesMap, 3, 4, 3));

        DirectedGraph<Integer> graph = new DirectedGraph<>(verticesMap, edges);

        assertTrue(Dijkstra.shortestPath(graph, 1, 4) == 6);
    }

    private DirectedEdge<Integer> createEdge(Map<Integer, Vertex<Integer>> verticesMap, int value1, int value2,
                    int distance) {
        final Vertex<Integer> vertex1 = verticesMap.get(value1);
        final Vertex<Integer> vertex2 = verticesMap.get(value2);
        final DirectedEdge<Integer> edge = new DirectedEdge<>(vertex1, vertex2, distance);
        vertex1.addEdge(edge);
        vertex2.addEdge(edge);

        return edge;
    }
}
