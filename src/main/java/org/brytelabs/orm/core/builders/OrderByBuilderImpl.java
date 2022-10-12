package org.brytelabs.orm.core.builders;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.brytelabs.orm.api.*;

public final class OrderByBuilderImpl implements OrderByBuilder {
  private final List<Order> orders;
  private final Query query;

  public OrderByBuilderImpl(Field field, Direction direction, Query query) {
    this(query, new Order(field, direction));
  }

  public OrderByBuilderImpl(Query query, Order... orders) {
    this.orders = Stream.of(orders).collect(Collectors.toList());
    this.query = new QueryImpl(query.selectBuilder(), query.whereBuilder(), query.joinBuilder(),
                               this, query.groupByBuilder(), query.onBuilder(), query.limitBuilder(),
                               query.offsetBuilder(), query.subQuery());
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
  public Query build() {
    return query;
  }

  public List<Order> getOrders() {
    return orders;
  }
}
