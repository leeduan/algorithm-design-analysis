package com.leeduan.graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;

@Ignore
public class StrongComponentsTest {

    @Test
    public void testReadData() {
        final Graph<Integer> graph = StrongComponents.readData();
        assertTrue("Size was " + graph.getVertices().size(), graph.getVertices().size() == 875714);
    }

    @Test
    public void testRun() {
        // will pass with -Xss256m
        assertEquals(Arrays.asList(434821, 968, 459, 313, 211), StrongComponents.run().subList(0, 5));
    }
}
