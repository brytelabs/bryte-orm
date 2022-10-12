package org.brytelabs.orm.core.builders;

import org.brytelabs.orm.api.LimitBuilder;
import org.brytelabs.orm.api.OffsetBuilder;
import org.brytelabs.orm.api.Query;

public class LimitBuilderImpl implements LimitBuilder {
  private final int limit;
  private final Query query;

  public LimitBuilderImpl(int limit, Query query) {
    this.limit = limit;
    this.query = new QueryImpl(query.selectBuilder(), query.whereBuilder(), query.joinBuilder(),
                               query.orderByBuilder(), query.groupByBuilder(), query.onBuilder(), this,
                               query.offsetBuilder(), query.subQuery());
  }

  @Override
  public OffsetBuilder offset(int offset) {
    return new OffsetBuilderImpl(offset, query);
  }

  @Override
  public Query build() {
    return query;
  }

  public int getLimit() {
    return limit;
  }
}
