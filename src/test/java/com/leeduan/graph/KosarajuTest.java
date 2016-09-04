package com.leeduan.graph;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class KosarajuTest {
    private Map<Integer, List<Integer>> graphData;

    @Before
    public void setup() {
        graphData = new HashMap<>();
        graphData.put(1, Collections.singletonList(7));
        graphData.put(2, Collections.singletonList(5));
        graphData.put(3, Collections.singletonList(9));
        graphData.put(4, Collections.singletonList(1));
        graphData.put(5, Collections.singletonList(8));
        graphData.put(6, Arrays.asList(3, 8));
        graphData.put(7, Arrays.asList(4, 9));
        graphData.put(8, Collections.singletonList(2));
        graphData.put(9, Collections.singletonList(6));
    }

    @Test
    public void testFinishingTimeFirstPass() {
        DirectedGraph<Integer> graph = new DirectedGraph<>(graphData);

        Kosaraju.strongComponentsBySize(graph);

        assertEquals(Arrays.asList(7, 3, 1, 8, 2, 5, 9, 4, 6), graph.getVertices().stream()
                .sorted((v1, v2) -> v1.getValue().compareTo(v2.getValue()))
                .map(Vertex::getFinishingTime)
                .collect(Collectors.toList()));
    }

    @Test
    public void testLeaderSecondPass() {
        DirectedGraph<Integer> graph = new DirectedGraph<>(graphData);

        assertEquals(Arrays.asList(3, 3, 3), Kosaraju.strongComponentsBySize(graph));
    }
}
