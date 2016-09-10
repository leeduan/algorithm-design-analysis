package com.leeduan.utils.transformers;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// XXX This only exists to piggy-back off of FileReader.lineFileScanner where list
// must be returned. For the file use case, we should ideally be returning a
// Map<Integer, Pair<Integer, Integer>>
public class StringListListIntTransformer implements Transformer<String, List<List<Integer>>> {
    private final String delimiter;

    public StringListListIntTransformer(String delimiter) {
        this.delimiter = delimiter;
    }

    @Override
    public List<List<Integer>> transform(String input) {
        Objects.requireNonNull(input, "Input cannot be null");

        return Arrays.stream(input.split(delimiter, -1))
                .filter(i -> !i.isEmpty())
                .map(s -> Stream.of(s.split(",", 2)).map(Integer::valueOf).collect(Collectors.toList()))
                .collect(Collectors.toList());
    }
}
