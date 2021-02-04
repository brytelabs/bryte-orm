package org.brytelabs.orm.core.operations;

public enum ConjunctionOperation {
    WHERE(" where "),
    AND(" and "),
    OR(" or ");

    private final String value;

    ConjunctionOperation(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
