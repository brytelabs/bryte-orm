package org.brytelabs.orm.api;

import lombok.Value;

@Value(staticConstructor = "of")
public class Order {
    Field field;
    Direction direction;

    public static Order asc(String field) {
        return Order.of(Field.with(field), Direction.ASC);
    }

    public static Order desc(String field) {
        return Order.of(Field.with(field), Direction.DESC);
    }

    public static Order asc(Field field) {
        return Order.of(field, Direction.ASC);
    }

    public static Order desc(Field field) {
        return Order.of(field, Direction.DESC);
    }
}
