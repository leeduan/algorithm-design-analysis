package com.leeduan.utils.transformers;

/**
 * Interface for transforming a type to another type.
 * @param <U>
 * @param <T>
 */
public interface Transformer<U, T> {

    T transform(U input);

}
