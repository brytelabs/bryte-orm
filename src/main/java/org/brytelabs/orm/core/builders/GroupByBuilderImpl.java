package org.brytelabs.orm.core.builders;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.brytelabs.orm.api.*;

public record GroupByBuilderImpl(Query query, List<Field> fields) implements GroupByBuilder {

  public static GroupByBuilder of(Query query, String[] fields) {
    return new GroupByBuilderImpl(query, Stream.of(fields).map(Field::with).toList());
  }

  @Override
  public OrderByBuilder orderBy(Order... orders) {
    return new OrderByBuilderImpl(query, List.of(orders));
  }

  @Override
  public Query build() {
    return new QueryImpl(query.selectBuilder(), query.whereBuilder(), query.joinBuilder(),
        query.orderByBuilder(), this, query.onBuilder(), query.limitBuilder(),
        query.offsetBuilder(), query.subQuery());
  }

  @Override
  public List<Field> fields() {
    return fields;
  }
}
