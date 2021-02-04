package org.brytelabs.orm.core.builders;

import org.brytelabs.orm.core.operations.Direction;
import org.brytelabs.orm.core.Select;
import org.brytelabs.orm.core.operations.Order;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OrderByBuilderImpl implements Select.OrderByBuilder {
    private final List<Order> orders;
    private final QueryBuilder queryBuilder;

    public OrderByBuilderImpl(String field, Direction direction, QueryBuilder queryBuilder) {
        this(queryBuilder, Order.of(field, direction));
    }

    public OrderByBuilderImpl(QueryBuilder queryBuilder, Order... orders) {
        this. orders = Stream.of(orders).collect(Collectors.toList());
        this.queryBuilder = queryBuilder;
        this.queryBuilder.setOrderByBuilder(this);
    }

    @Override
    public QueryBuilder build() {
        return queryBuilder;
    }
}
