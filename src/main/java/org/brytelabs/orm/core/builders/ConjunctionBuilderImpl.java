package org.brytelabs.orm.core.builders;

import org.brytelabs.orm.api.*;
import org.brytelabs.orm.core.domain.LinkedConjunction;
import org.brytelabs.orm.core.operations.ConjunctionOperation;

public final class ConjunctionBuilderImpl implements ConjunctionBuilder {
  private final LinkedConjunction linkedConjunction;
  private final Query query;

  public ConjunctionBuilderImpl(LinkedConjunction linkedConjunction, Query query) {
    this.linkedConjunction = linkedConjunction;
    this.query = query;
  }

  @Override
  public WhereBuilder and(String field) {
    return new WhereBuilderImpl(
        Field.with(field), ConjunctionOperation.AND, linkedConjunction, query);
  }

  @Override
  public WhereBuilder or(String field) {
    return new WhereBuilderImpl(
        Field.with(field), ConjunctionOperation.OR, linkedConjunction, query);
  }

  @Override
  public OrderByBuilder orderBy(Order... orders) {
    return new OrderByBuilderImpl(query, orders);
  }

  @Override
  public GroupByBuilder groupBy(String... fields) {
    return new GroupByBuilderImpl(fields, query);
  }

  @Override
  public OffsetBuilder offset(int offset) {
    return new OffsetBuilderImpl(offset, query);
  }

  @Override
  public LimitBuilder limit(int limit) {
    return new LimitBuilderImpl(limit, query);
  }

  @Override
  public Query build() {
    return query;
  }
}
