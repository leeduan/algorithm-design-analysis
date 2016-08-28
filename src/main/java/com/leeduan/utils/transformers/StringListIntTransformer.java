package com.leeduan.utils.transformers;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StringListIntTransformer implements Transformer<String, List<Integer>> {

    @Override
    public List<Integer> transform(String input) {
        Objects.requireNonNull(input, "Input cannot be null");

        return Arrays.stream(input.split("\t", -1))
                .filter(i -> !i.isEmpty())
                .map(Integer::valueOf)
                .collect(Collectors.toList());
    }

}
