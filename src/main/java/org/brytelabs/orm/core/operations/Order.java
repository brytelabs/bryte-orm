package org.brytelabs.orm.core.operations;

import lombok.Value;

@Value(staticConstructor = "of")
public class Order {
    String field;
    Direction direction;

    public static Order asc(String field) {
        return Order.of(field, Direction.ASC);
    }

    public static Order desc(String field) {
        return Order.of(field, Direction.DESC);
    }
}
