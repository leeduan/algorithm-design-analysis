package com.leeduan.graph;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KargerTest {
    private Map<Integer, List<Integer>> graphData;

    @Before
    public void setup() {
        graphData = new HashMap<>();
        graphData.put(1, Arrays.asList(2, 3));
        graphData.put(2, Arrays.asList(1, 3, 4));
        graphData.put(3, Arrays.asList(1, 2, 4));
        graphData.put(4, Arrays.asList(2, 3));
    }

    @Test
    public void testNodeCount() {
        assertTrue(new UndirectedGraph<>(graphData).getVertices().size() == 4);
    }

    @Test
    public void testEdgeCount() {
        assertTrue(new UndirectedGraph<>(graphData).getEdges().size() == 5);
    }

    @Test
    public void testMinCut() {
        assertTrue("Min cut is 2", Karger.minCutRepeated(graphData) == 2);
    }
}
