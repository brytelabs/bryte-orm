package org.brytelabs.orm.core.builders;

import org.brytelabs.orm.api.OnBuilder;
import org.brytelabs.orm.api.Query;
import org.brytelabs.orm.api.WhereBuilder;
import org.brytelabs.orm.core.domain.JoinCondition;

public final class OnBuilderImpl implements OnBuilder {
  private final JoinCondition condition;
  private final Query query;

  public OnBuilderImpl(JoinCondition condition, Query query) {
    this.condition = condition;
    this.query = new QueryImpl(query.selectBuilder(), query.whereBuilder(), query.joinBuilder(),
                               query.orderByBuilder(), query.groupByBuilder(), this, query.limitBuilder(),
                               query.offsetBuilder(), query.subQuery());
  }

  @Override
  public WhereBuilder where(String field) {
    return new WhereBuilderImpl(field, query);
  }

  public JoinCondition getCondition() {
    return condition;
  }

  @Override
  public Query build() {
    return query;
  }
}
