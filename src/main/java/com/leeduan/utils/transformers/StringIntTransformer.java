package com.leeduan.utils.transformers;

import java.util.Objects;

public class StringIntTransformer implements Transformer<String, Integer> {

    @Override
    public Integer transform(String input) {
        Objects.requireNonNull(input, "Input cannot be null");

        return Integer.valueOf(input);
    }
}
