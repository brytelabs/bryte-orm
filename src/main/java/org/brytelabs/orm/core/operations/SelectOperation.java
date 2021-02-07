package org.brytelabs.orm.core.operations;

import lombok.Getter;

public enum SelectOperation {
    ALL("*"),
    FIELDS(""),
    COUNT("count"),
    SUM("sum"),
    AVG("avg"),
    MAX("max"),
    MIN("min");

    @Getter
    private final String value;

    SelectOperation(String value) {
        this.value = value;
    }
}
