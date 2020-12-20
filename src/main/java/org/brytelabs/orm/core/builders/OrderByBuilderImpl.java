package org.brytelabs.orm.core.builders;

import org.brytelabs.orm.core.operations.Direction;
import org.brytelabs.orm.core.Query;
import org.brytelabs.orm.core.Select;

public class OrderByBuilderImpl implements Select.OrderByBuilder {
    private String field;
    private Direction direction;

    public OrderByBuilderImpl(String field, Direction direction) {
        this.field = field;
        this.direction = direction;
    }

    @Override
    public Select.GroupByBuilder groupBy(String... fields) {
        return new GroupByBuilderImpl(fields);
    }

    @Override
    public Query build() {
        return null;
    }
}
