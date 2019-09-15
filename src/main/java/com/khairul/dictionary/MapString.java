package com.khairul.dictionary;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapString implements MapService {

    @Override
    public String store(Map[] arr) {
        if (arr == null || arr.length == 0)
            return null;

        return Stream.of(arr)
                .map(item -> toFlatString(item))
                .collect(Collectors.joining("\n"));
    }

    @Override
    public Map[] load(String text) {
        if (text == null || text.trim().length() == 0)
            return null;

        return Stream.of(text.split("\n"))
                .map(line -> toMap(line))
                .toArray(Map[]::new);
    }

    private String toFlatString(Map item) {
        return item.keySet().stream()
                .map(key -> key + "=" + item.get(key))
                .collect(Collectors.joining(";")).toString();
    }

    private Map toMap(String line) {
        return Stream.of(line.split(";"))
                .map(item -> item.split("="))
                .collect(Collectors.toMap(kv -> kv[0], kv -> kv[1]));
    }
}
