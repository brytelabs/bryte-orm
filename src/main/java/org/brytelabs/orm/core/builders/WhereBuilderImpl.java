package org.brytelabs.orm.core.builders;

import org.brytelabs.orm.core.operations.Direction;
import org.brytelabs.orm.core.Select;

public class WhereBuilderImpl implements Select.WhereBuilder {
    private final String field;
    public WhereBuilderImpl(String field) {
        this.field = field;
    }

    @Override
    public Select.ConjunctionBuilder eq(Object value) {
        return new ConjunctionBuilderImpl(value);
    }

    @Override
    public Select.OrderByBuilder orderBy(String field, Direction direction) {
        return null;
    }

    @Override
    public Select.GroupByBuilder groupBy(String... field) {
        return null;
    }
}
