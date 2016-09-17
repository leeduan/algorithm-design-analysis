package com.leeduan.utils.transformers;

import java.util.Objects;

public class StringLongTransformer implements Transformer<String, Long> {

    @Override
    public Long transform(String input) {
        Objects.requireNonNull(input, "Input cannot be null");

        return Long.valueOf(input);
    }
}
