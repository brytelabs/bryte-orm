package org.brytelabs.orm.core.builders;

import org.brytelabs.orm.api.Field;
import org.brytelabs.orm.api.OnBuilder;
import org.brytelabs.orm.api.Query;
import org.brytelabs.orm.api.WhereBuilder;
import org.brytelabs.orm.core.domain.JoinCondition;

public record OnBuilderImpl(Query query, JoinCondition condition) implements OnBuilder {

  @Override
  public WhereBuilder where(String field) {
    return WhereBuilderImpl.of(query, Field.with(field));
  }

  @Override
  public Query build() {
    return new QueryImpl(query.selectBuilder(), query.whereBuilder(), query.joinBuilder(),
        query.orderByBuilder(), query.groupByBuilder(), this, query.limitBuilder(),
        query.offsetBuilder(), query.subQuery());
  }
}
