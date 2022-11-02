package org.brytelabs.orm.core.builders;

import org.brytelabs.orm.api.Field;
import org.brytelabs.orm.api.OnBuilder;
import org.brytelabs.orm.api.Query;
import org.brytelabs.orm.api.WhereExpressionBuilder;
import org.brytelabs.orm.core.domain.JoinCondition;

public record OnBuilderImpl(Query query, JoinCondition condition) implements OnBuilder {

  @Override
  public WhereExpressionBuilder where(String field) {
    return WhereExpressionBuilderImpl.of(query, Field.with(field));
  }

  @Override
  public Query build() {
    return new QueryImpl(query.selectBuilder(), query.whereBuilder(), query.joinBuilder(),
        query.orderByBuilder(), query.groupByBuilder(), this, query.limitBuilder(),
        query.offsetBuilder(), query.subQuery());
  }
}
