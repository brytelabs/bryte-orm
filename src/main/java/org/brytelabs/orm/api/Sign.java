package org.brytelabs.orm.api;

import lombok.Getter;

public enum Sign {
    EQUAL(" = "),
    LESS_THAN(" < "),
    LESS_THAN_OR_EQUAL(" <= "),
    GREATER_THAN(" > "),
    GREATER_THAN_OR_EQUAL(" >= "),
    BETWEEN(" between "),
    IN(" in "),
    NOT_IN(" not in "),
    NOT_EQUAL(" != ");

    @Getter
    private final String value;

    Sign(String value) {
        this.value = value;
    }
}
