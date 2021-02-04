package org.brytelabs.orm.core;

import org.brytelabs.orm.core.operations.Sign;

public class Expression {
    final Sign sign;
    final String field;
    final Object value;

    public Expression(Sign sign, String field, Object value) {
        this.sign = sign;
        this.field = field;
        this.value = value;
    }

    public Sign getSign() {
        return sign;
    }

    public String getField() {
        return field;
    }

    public Object getValue() {
        return value;
    }
}
