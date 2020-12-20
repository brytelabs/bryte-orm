package org.brytelabs.orm.core.builders;

import org.brytelabs.orm.core.operations.Direction;
import org.brytelabs.orm.core.Query;
import org.brytelabs.orm.core.Select;

public class GroupByBuilderImpl implements Select.GroupByBuilder {
    private String[] fields;

    public GroupByBuilderImpl(String[] fields) {
        this.fields = fields;
    }

    @Override
    public Select.OrderByBuilder orderBy(String field, Direction direction) {
        return new OrderByBuilderImpl(field, direction);
    }

    @Override
    public Query build() {
        return null;
    }
}
