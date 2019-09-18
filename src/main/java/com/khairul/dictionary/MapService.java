package com.khairul.dictionary;

import java.util.Map;

public interface MapService {
    String store(Map[] arr);
    Map[] load(String text);
}
