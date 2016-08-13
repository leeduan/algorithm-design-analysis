package com.leeduan.utils.operators;

/**
 * Rational operator implementation for Integer class.
 */
public class IntegerOperator implements RationalOperator<Integer> {

    @Override
    public boolean equalTo(Integer i1, Integer i2) {
        return i1.equals(i2);
    }

    @Override
    public boolean notEqualTo(Integer i1, Integer i2) {
        return !equalTo(i1, i2);
    }

    @Override
    public boolean lessThan(Integer i1, Integer i2) {
        return i1.compareTo(i2) < 0;
    }

    @Override
    public boolean lessThanOrEqualTo(Integer i1, Integer i2) {
        return i1.compareTo(i2) <= 0;
    }

    @Override
    public boolean greaterThan(Integer i1, Integer i2) {
        return i1.compareTo(i2) > 0;
    }

    @Override
    public boolean greaterThanOrEqualTo(Integer i1, Integer i2) {
        return i1.compareTo(i2) >= 0;
    }

}
