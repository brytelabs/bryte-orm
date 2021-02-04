package org.brytelabs.orm.core.builders;

import org.brytelabs.orm.core.Select;
import org.brytelabs.orm.core.operations.Order;

public class GroupByBuilderImpl implements Select.GroupByBuilder {
    private final String[] fields;
    private final QueryBuilder queryBuilder;

    public GroupByBuilderImpl(String[] fields, QueryBuilder queryBuilder) {
        this.fields = fields;
        this.queryBuilder = queryBuilder;
        this.queryBuilder.setGroupByBuilder(this);
    }

    @Override
    public Select.OrderByBuilder orderBy(Order... orders) {
        return new OrderByBuilderImpl(queryBuilder, orders);
    }

    @Override
    public QueryBuilder build() {
        return queryBuilder;
    }

    public String[] getFields() {
        return fields;
    }
}
