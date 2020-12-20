package org.brytelabs.orm.core.operations;

public enum SelectOperation {
    ALL("*"),
    FIELDS(""),
    COUNT("count"),
    SUM("sum"),
    AVG("avg"),
    MAX("max"),
    MIN("min");

    private final String value;

    SelectOperation(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
