package org.brytelabs.orm.api;

public enum Sign {
    EQUAL(" = "),
    LESS_THAN(" < "),
    LESS_THAN_OR_EQUAL(" <= "),
    GREATER_THAN(" > "),
    GREATER_THAN_OR_EQUAL(" >= "),
    IN(" in ");

    private final String value;

    Sign(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
