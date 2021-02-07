package org.brytelabs.orm.core.domain;

import lombok.Getter;
import org.brytelabs.orm.api.Field;
import org.brytelabs.orm.api.Sign;

@Getter
public class Expression {
    private final Sign sign;
    private final Field field;
    private final Object value;

    public Expression(Sign sign, String field, Object value) {
        this(sign, Field.with(field), value);
    }

    public Expression(Sign sign, Field field, Object value) {
        this.sign = sign;
        this.field = field;
        this.value = value;
    }
}
