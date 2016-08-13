package com.leeduan.utils;

import com.leeduan.utils.transformers.Transformer;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Objects;

public class FileReader {

    /**
     * Helper function to read a file containing an value of transformer type per line.
     * @param path
     * @return
     */
    public static <T> List<T> lineFileScanner(URL path, Transformer<String, T> transformer) {
        Objects.requireNonNull(path, "File path cannot be null");
        Objects.requireNonNull(transformer, "Transformer cannot be null");

        final List<T> list = new ArrayList<>();
        try {
            final Scanner scanner = new Scanner(new File(path.getFile()));
            while (scanner.hasNextInt()) {
                list.add(transformer.transform(scanner.next()));
            }
        } catch (IOException e) {
            throw new RuntimeException("File could not be read");
        }

        return list;
    }

}
