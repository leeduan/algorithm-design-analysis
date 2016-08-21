package com.leeduan.utils.operators;

public class RationalOperatorMath<T> {
    public RationalOperator<T> operator;

    public RationalOperatorMath(RationalOperator<T> operator) {
        this.operator = operator;
    }

    public T min(T x1, T x2) {
        return operator.greaterThan(x1, x2) ? x2 : x1;
    }

    public T max(T x1, T x2) {
        return operator.greaterThan(x1, x2) ? x1 : x2;
    }

    // http://stackoverflow.com/questions/1582356/fastest-way-of-finding-the-middle-value-of-a-triple
    // return Math.max(Math.min(low, middle), Math.min(Math.max(low, middle), high));
    public T median(T x1, T x2, T x3) {
        return max(min(x1, x2), min(max(x1, x2), x3));
    }
}
