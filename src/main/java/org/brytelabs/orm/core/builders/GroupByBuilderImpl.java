package org.brytelabs.orm.core.builders;

import org.brytelabs.orm.api.GroupByBuilder;
import org.brytelabs.orm.api.OrderByBuilder;
import org.brytelabs.orm.api.Order;

public final class GroupByBuilderImpl implements GroupByBuilder {
    private final String[] fields;
    private final QueryImpl query;

    public GroupByBuilderImpl(String[] fields, QueryImpl query) {
        this.fields = fields;
        this.query = query;
        this.query.setGroupByBuilder(this);
    }

    @Override
    public OrderByBuilder orderBy(Order... orders) {
        return new OrderByBuilderImpl(query, orders);
    }

    @Override
    public QueryImpl build() {
        return query;
    }

    public String[] getFields() {
        return fields;
    }
}
