package org.brytelabs.orm.core.builders;

import java.util.List;

import org.brytelabs.orm.api.*;

public record OrderByBuilderImpl(Query query, List<Order> orders) implements OrderByBuilder {

  public static OrderByBuilder of(Field field, Direction direction, Query query) {
      return new OrderByBuilderImpl(query, List.of(new Order(field, direction)));
  }

  public static OrderByBuilder of(Query query, Order... orders) {
      return new OrderByBuilderImpl(query, List.of(orders));
  }

  @Override
  public LimitBuilder limit(int limit) {
    return new LimitBuilderImpl(query, limit);
  }

  @Override
  public OffsetBuilder offset(int offset) {
    return new OffsetBuilderImpl(query, offset);
  }

  @Override
  public Query build() {
    return new QueryImpl(query.selectBuilder(), query.whereBuilder(), query.joinBuilder(),
                         this, query.groupByBuilder(), query.onBuilder(), query.limitBuilder(),
                         query.offsetBuilder(), query.subQuery());
  }
}
