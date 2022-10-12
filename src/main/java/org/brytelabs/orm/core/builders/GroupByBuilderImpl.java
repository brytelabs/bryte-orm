package org.brytelabs.orm.core.builders;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.brytelabs.orm.api.*;

public final class GroupByBuilderImpl implements GroupByBuilder {
  private final List<Field> fields;
  private final Query query;

  public GroupByBuilderImpl(String[] fields, Query query) {
    this(Stream.of(fields).map(Field::with).collect(Collectors.toList()), query);
  }

    public GroupByBuilderImpl(List<Field> fields, Query query) {
    this.fields = fields;
    this.query = new QueryImpl(query.selectBuilder(), query.whereBuilder(), query.joinBuilder(),
            query.orderByBuilder(), this, query.onBuilder(), query.limitBuilder(),
            query.offsetBuilder(), query.subQuery());
  }

  @Override
  public OrderByBuilder orderBy(Order... orders) {
    return new OrderByBuilderImpl(query, orders);
  }

  @Override
  public Query build() {
    return query;
  }

  @Override
  public List<Field> fields() {
    return fields;
  }
}
