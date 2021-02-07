package org.brytelabs.orm.api;

import lombok.Getter;

public enum Direction {
    ASC("asc"),
    DESC("desc");

    @Getter
    private final String value;

    Direction(String value) {
        this.value = value;
    }
}
