package com.leeduan.utils.operators;

/**
 * Interface for rational operators that can be applied to a type.
 * @param <T>
 */
public interface RationalOperator<T> {

    boolean equalTo(T x1, T x2);

    boolean notEqualTo(T x1, T x2);

    boolean lessThan(T x1, T x2);

    boolean lessThanOrEqualTo(T x1, T x2);

    boolean greaterThan(T x1, T x2);

    boolean greaterThanOrEqualTo(T x1, T x2);

}
