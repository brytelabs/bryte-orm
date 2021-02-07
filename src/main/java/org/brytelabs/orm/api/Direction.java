package org.brytelabs.orm.api;

public enum Direction {
    ASC("asc"),
    DESC("desc");
    private final String value;

    Direction(String value) {
        this.value = value;
    }
}
