package com.leeduan.utils.operators;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RationalOperatorMathTest {
    private static final RationalOperatorMath<Integer> MATH = new RationalOperatorMath<>(new IntegerOperator());

    @Test
    public void testMin() {
        assertEquals(new Integer(3), MATH.min(3, 5));
        assertEquals(new Integer(1), MATH.min(3, 1));
        assertEquals(new Integer(3), MATH.min(3, 3));
    }

    @Test
    public void testMax() {
        assertEquals(new Integer(5), MATH.max(3, 5));
        assertEquals(new Integer(3), MATH.max(3, 1));
        assertEquals(new Integer(3), MATH.max(3, 3));
    }

    @Test
    public void testMedian() {
        assertEquals(new Integer(3), MATH.median(3, 1, 8));
        assertEquals(new Integer(9), MATH.median(10, 9, 2));
        assertEquals(new Integer(9), MATH.median(2, 9, 12));
        assertEquals(new Integer(6), MATH.median(9, 4, 6));
    }

}
