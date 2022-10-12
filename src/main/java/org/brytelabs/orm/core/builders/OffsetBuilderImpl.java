package org.brytelabs.orm.core.builders;

import org.brytelabs.orm.api.LimitBuilder;
import org.brytelabs.orm.api.OffsetBuilder;
import org.brytelabs.orm.api.Query;

public class OffsetBuilderImpl implements OffsetBuilder {
  private final int offset;
  private final Query query;

  public OffsetBuilderImpl(int offset, Query query) {
    this.offset = offset;
    this.query = new QueryImpl(query.selectBuilder(), query.whereBuilder(), query.joinBuilder(),
                               query.orderByBuilder(), query.groupByBuilder(), query.onBuilder(), query.limitBuilder(),
                               this, query.subQuery());
  }

  @Override
  public LimitBuilder limit(int limit) {
    return new LimitBuilderImpl(limit, query);
  }

  @Override
  public Query build() {
    return query;
  }

  public int getOffset() {
    return offset;
  }
}
