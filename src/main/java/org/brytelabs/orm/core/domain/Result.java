package org.brytelabs.orm.core.domain;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Result {
    private final Map<String, Object> map = new ConcurrentHashMap<>();

    public void add(String column, Object value) {
        map.put(column, value);
    }

    public int getInt(String column) {
        return Integer.parseInt(String.valueOf(map.get(column)));
    }
}
