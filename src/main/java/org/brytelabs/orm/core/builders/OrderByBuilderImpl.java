package org.brytelabs.orm.core.builders;

import org.brytelabs.orm.api.LimitBuilder;
import org.brytelabs.orm.api.OffsetBuilder;
import org.brytelabs.orm.api.OrderByBuilder;
import org.brytelabs.orm.api.Direction;
import org.brytelabs.orm.api.Order;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class OrderByBuilderImpl implements OrderByBuilder {
    private final List<Order> orders;
    private final QueryImpl query;

    public OrderByBuilderImpl(String field, Direction direction, QueryImpl query) {
        this(query, Order.of(field, direction));
    }

    public OrderByBuilderImpl(QueryImpl query, Order... orders) {
        this. orders = Stream.of(orders).collect(Collectors.toList());
        this.query = query;
        this.query.setOrderByBuilder(this);
    }

    @Override
    public LimitBuilder limit(int limit) {
        return new LimitBuilderImpl(limit, query);
    }

    @Override
    public OffsetBuilder offset(int offset) {
        return new OffsetBuilderImpl(offset, query);
    }

    @Override
    public QueryImpl build() {
        return query;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
