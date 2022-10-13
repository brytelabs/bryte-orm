package org.brytelabs.orm.core.builders;

import org.brytelabs.orm.api.*;
import org.brytelabs.orm.core.domain.LinkedConjunction;
import org.brytelabs.orm.core.operations.ConjunctionOperation;

import java.util.List;

public record ConjunctionBuilderImpl(Query query, LinkedConjunction linkedConjunction) implements ConjunctionBuilder {

  @Override
  public WhereBuilder and(String field) {
    return new WhereBuilderImpl(query, Field.with(field), linkedConjunction, ConjunctionOperation.AND);
  }

  @Override
  public WhereBuilder or(String field) {
    return new WhereBuilderImpl(query, Field.with(field), linkedConjunction, ConjunctionOperation.OR);
  }

  @Override
  public OrderByBuilder orderBy(Order... orders) {
    return new OrderByBuilderImpl(query, List.of(orders));
  }

  @Override
  public GroupByBuilder groupBy(String... fields) {
    return GroupByBuilderImpl.of(query, fields);
  }

  @Override
  public OffsetBuilder offset(int offset) {
    return new OffsetBuilderImpl(query, offset);
  }

  @Override
  public LimitBuilder limit(int limit) {
    return new LimitBuilderImpl(query, limit);
  }

  @Override
  public Query build() {
    return query;
  }
}
