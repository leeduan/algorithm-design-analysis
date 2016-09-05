package com.leeduan.graph;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class KosarajuTest {
    private Map<Integer, Vertex<Integer>> verticesMap;
    private DirectedGraph<Integer> graph;

    @Before
    public void setup() {
        verticesMap = IntStream.rangeClosed(1, 9).boxed()
                .collect(Collectors.toMap(Function.identity(), DirectedVertex::new));
        final List<Edge<Integer>> edges = Arrays.asList(createEdge(1, 7), createEdge(2, 5), createEdge(3, 9),
                        createEdge(4, 1), createEdge(5, 8), createEdge(6, 3), createEdge(6, 8), createEdge(7, 4),
                        createEdge(7, 9), createEdge(8, 2), createEdge(9, 6));

        graph = new DirectedGraph<>(verticesMap, edges);
    }

    private DirectedEdge<Integer> createEdge(int value1, int value2) {
        final Vertex<Integer> vertex1 = verticesMap.get(value1);
        final Vertex<Integer> vertex2 = verticesMap.get(value2);
        final DirectedEdge<Integer> edge = new DirectedEdge<>(vertex1, vertex2);
        vertex1.addEdge(edge);
        vertex2.addEdge(edge);

        return edge;
    }

    @Test
    public void testFinishingTimeFirstPass() {
        Kosaraju.strongComponentsBySize(graph);

        assertEquals(Arrays.asList(7, 3, 1, 8, 2, 5, 9, 4, 6), graph.getVertices().stream()
                .sorted((v1, v2) -> v1.getValue().compareTo(v2.getValue()))
                .map(Vertex::getFinishingTime)
                .collect(Collectors.toList()));
    }

    @Test
    public void testLeaderSecondPass() {
        assertEquals(Arrays.asList(3, 3, 3), Kosaraju.strongComponentsBySize(graph));
    }
}
