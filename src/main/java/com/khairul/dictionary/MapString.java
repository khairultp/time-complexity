package com.khairul.dictionary;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class MapString implements MapService {

    @Override
    public String store(Map[] arr) {
        return Arrays.stream(arr)
                .map(item -> combinesKeyValue(item))
                .collect(Collectors.joining("\n"));
    }

    private String combinesKeyValue(Map item) {
        return item.keySet().stream()
                .map(key -> key + "=" + item.get(key))
                .collect(Collectors.joining(";")).toString();
    }

    @Override
    public Map[] load(String text) {
        return new Map[0];
    }
}
