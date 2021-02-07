package org.brytelabs.orm.core.operations;

import lombok.Getter;

public enum ConjunctionOperation {
    AND(" and "),
    OR(" or ");

    @Getter
    private final String value;

    ConjunctionOperation(String value) {
        this.value = value;
    }
}
