package org.brytelabs.orm.core.builders;

import org.brytelabs.orm.api.Field;
import org.brytelabs.orm.api.GroupByBuilder;
import org.brytelabs.orm.api.OrderByBuilder;
import org.brytelabs.orm.api.Order;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class GroupByBuilderImpl implements GroupByBuilder {
    private final List<Field> fields;
    private final QueryImpl query;

    public GroupByBuilderImpl(String[] fields, QueryImpl query) {
        this(Stream.of(fields)
                .map(Field::with)
                .collect(Collectors.toList()), query);
    }

    public GroupByBuilderImpl(List<Field> fields, QueryImpl query) {
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

    public List<Field> getFields() {
        return fields;
    }
}
