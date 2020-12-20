package org.brytelabs.orm.core.builders;

import org.brytelabs.orm.core.operations.Direction;
import org.brytelabs.orm.core.Query;
import org.brytelabs.orm.core.Select;

public class ConjunctionBuilderImpl implements Select.ConjunctionBuilder {
    private final Object value;

    public ConjunctionBuilderImpl(Object value) {
        this.value = value;
    }

    @Override
    public Select.WhereBuilder and(String field) {
        return new WhereBuilderImpl(field);
    }

    @Override
    public Select.WhereBuilder or(String field) {
        return new WhereBuilderImpl(field);
    }

    @Override
    public Select.OrderByBuilder orderBy(String field, Direction direction) {
        return new OrderByBuilderImpl(field, direction);
    }

    @Override
    public Select.GroupByBuilder groupBy(String... field) {
        return null;
    }

    @Override
    public Query build() {
        return null;
    }
}
