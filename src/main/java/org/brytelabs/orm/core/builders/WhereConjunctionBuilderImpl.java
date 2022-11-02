package org.brytelabs.orm.core.builders;

import java.util.List;
import org.brytelabs.orm.api.ConjunctionBuilder;
import org.brytelabs.orm.api.Field;
import org.brytelabs.orm.api.GroupByBuilder;
import org.brytelabs.orm.api.LimitBuilder;
import org.brytelabs.orm.api.OffsetBuilder;
import org.brytelabs.orm.api.Order;
import org.brytelabs.orm.api.OrderBy;
import org.brytelabs.orm.api.OrderByBuilder;
import org.brytelabs.orm.api.Query;
import org.brytelabs.orm.api.WhereConjunctionBuilder;
import org.brytelabs.orm.api.WhereExpressionBuilder;
import org.brytelabs.orm.core.domain.LinkedConjunction;
import org.brytelabs.orm.core.operations.ConjunctionOperation;

public record WhereConjunctionBuilderImpl(Query query, LinkedConjunction linkedConjunction) implements WhereConjunctionBuilder {

  @Override
  public WhereExpressionBuilder and(String field) {
    return new WhereExpressionBuilderImpl(query, Field.with(field), linkedConjunction, ConjunctionOperation.AND);
  }

  @Override
  public WhereExpressionBuilder or(String field) {
    return new WhereExpressionBuilderImpl(query, Field.with(field), linkedConjunction, ConjunctionOperation.OR);
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
